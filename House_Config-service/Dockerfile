FROM eclipse-temurin:21-jre

WORKDIR /app

ADD target/House_Config-service-0.0.1-SNAPSHOT.jar House_Config-service-0.0.1-SNAPSHOT.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "House_Config-service-0.0.1-SNAPSHOT.jar"]
