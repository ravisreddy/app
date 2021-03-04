FROM openjdk:8-jdk-alpine
MAINTAINER ravisreddy
COPY target/api-service-0.0.1-SNAPSHOT.jar api-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/api-service-0.0.1-SNAPSHOT.jar"]