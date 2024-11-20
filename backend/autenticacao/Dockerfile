FROM maven:3.9.5-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .mvn ./.mvn
COPY mvnw ./mvnw
RUN ./mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

COPY private_key.pem /app/private_key.pem
COPY public_key.pem /app/public_key.pem

EXPOSE ${server_port}

ENTRYPOINT ["java", "-jar", "app.jar"]