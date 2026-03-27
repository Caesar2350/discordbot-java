FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copy ANY JAR that exists (shaded OR regular)
COPY --from=builder /app/target/ app/
WORKDIR /app/target
ENTRYPOINT ["java", "-cp", "*:", "org.example.App"]
