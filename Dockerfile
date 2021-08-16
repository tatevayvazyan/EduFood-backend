FROM openjdk:8-jdk-alpine


FROM java:8-jdk
CMD ["/bin/bash"]

## Build stage
##
#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#
#RUN mvn -f /home/app/pom.xml clean package
#RUN mvn install install


ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
