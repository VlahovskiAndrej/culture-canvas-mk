FROM maven:latest AS build
WORKDIR /app-email
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:latest
EXPOSE 9001
WORKDIR /app-email
COPY --from=build /app-email/target/email-conformation.jar .
CMD ["java", "-jar", "email-conformation.jar"]