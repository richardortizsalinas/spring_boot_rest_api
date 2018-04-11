FROM openjdk:8-jdk-alpine
ADD target/creditcard-service-0.0.1-SNAPSHOT.jar creditcard_service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/creditcard_service.jar"]

