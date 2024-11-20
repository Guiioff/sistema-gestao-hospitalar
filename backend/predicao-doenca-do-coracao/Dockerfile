FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

COPY modelo_heart_disease.pmml ./modelo_heart_disease.pmml

EXPOSE 8091

ENV caminho_arquivo_modelo=/app/modelo_heart_disease.pmml
ENV server_port=8091

ENTRYPOINT ["java", "-jar", "app.jar"]