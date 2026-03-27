FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -q -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/untitled-1.0-SNAPSHOT.jar app.jar
EXPOSE 8000
CMD ["java", "-jar", "app.jar"]
