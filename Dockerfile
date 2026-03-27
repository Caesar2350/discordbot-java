FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
# Force SHADER as FINAL JAR
RUN mvn clean package -DskipTests && \
    mv target/discord-bot-1.0-SNAPSHOT-shaded.jar target/app.jar

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
