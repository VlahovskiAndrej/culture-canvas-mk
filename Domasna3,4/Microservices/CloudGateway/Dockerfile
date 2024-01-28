FROM maven:latest AS build
WORKDIR /app-api
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:latest
EXPOSE 9191
WORKDIR /app-api
COPY --from=build /app-api/target/api-gate.jar .
CMD ["java", "-jar", "api-gate.jar"]