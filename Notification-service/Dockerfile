FROM eclipse-temurin:21-jre

WORKDIR /app

ADD target/Notification-service-0.0.1-SNAPSHOT.jar Notification-service-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Notification-service-0.0.1-SNAPSHOT.jar"]
