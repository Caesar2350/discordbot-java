# Maven build stage
FROM maven:3.9.6-openjdk-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:21-jre-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY .env .env
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
