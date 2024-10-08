FROM maven:3.9.8-amazoncorretto-21-al2023 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:24-jdk-slim-bullseye
WORKDIR /app
COPY --from=build /app/target/ezbooking-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]