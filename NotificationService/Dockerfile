FROM maven:3-eclipse-temurin-21 AS compile
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21.0.4_7-jre-noble
WORKDIR /app
COPY --from=compile /app/target/Notification-service-0.0.1-SNAPSHOT.jar ./
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "Notification-service-0.0.1-SNAPSHOT.jar"]