FROM maven:latest AS build
WORKDIR /app-culturecanvas
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:latest
EXPOSE 9005
WORKDIR /app-culturecanvas
COPY --from=build /app-culturecanvas/target/culturecanvas-app.jar .
CMD ["java", "-jar", "culturecanvas-app.jar"]