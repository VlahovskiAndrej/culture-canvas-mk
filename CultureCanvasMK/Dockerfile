FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:latest
EXPOSE 9004
WORKDIR /app
COPY --from=build /app/target/web-app.jar .
CMD ["java", "-jar", "web-app.jar"]