#FROM openjdk:21
#
#WORKDIR /TransferApp
#
#COPY target/*.jar app.jar
#
#EXPOSE 8080
#
#CMD ["java" ,"-jar" ,"app.jar"]




# Stage 1: Build the application
FROM maven:3.3.1-openjdk-21-slim AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

