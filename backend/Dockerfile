## Image with Java 17
#FROM eclipse-temurin:17-jdk-alpine
#
## 2. Creamos una carpeta dentro del contenedor para nuestra app
##Create inside the container a folder
#WORKDIR /app
#
#
## Copy Maven file into the container
#COPY target/*.jar app.jar
#
## This container use port 8080
#EXPOSE 8080
#
## Command to run the app.
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]