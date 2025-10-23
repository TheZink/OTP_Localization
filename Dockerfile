FROM maven:latest
LABEL authors="ilkkasin"

# Set working directory
WORKDIR /app

COPY demo/pom.xml ./
COPY demo/src ./src

RUN mvn package

CMD [ "java", "-jar", "target/demo-1.0-SNAPSHOT.jar" ]