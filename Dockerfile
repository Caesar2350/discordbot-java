# Build stage
# Stage 1 - Build
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2 - Run
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/discord-bot-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
