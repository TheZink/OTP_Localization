pipeline {
    agent any

    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        
        // Define Docker Hub credentials ID
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        // Define Docker Hub repository name
        DOCKERHUB_REPO = 'ilkkasin/otp_localization'
        // Define Docker image tag
        DOCKER_IMAGE_TAG = 'latest'
    }
tools{
    maven 'MAVEN_HOME' // MAVEN_HOME is the name of *your own* defined maven name in Jenkins.
}
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url:'https://github.com/TheZink/OTP_localization.git'
            }
        }
        stage('Build') {
            steps {
                dir('demo') {
                    bat 'mvn clean install'
                }
            }
        }
        stage('Test') {
            steps {
                dir('demo') {
                    bat 'mvn test'
                }
            }
        }
        stage('Code Coverage') {
            steps {
                dir('demo') {
                    bat 'mvn jacoco:report'
                }
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Build Docker Image') {
            steps {
                bat 'docker build -f dockerfile -t %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG% .'
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat '''
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker push %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG%
                    '''
                }
            }
        }
    }
}