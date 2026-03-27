FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# EXACT: Copy your shaded JAR → rename app.jar
COPY --from=builder /app/target/discord-bot-1.0-SNAPSHOT-shaded.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
