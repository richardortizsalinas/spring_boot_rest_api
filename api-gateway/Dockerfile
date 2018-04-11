FROM openjdk:8-jdk-alpine
ADD target/api-gateway-0.0.1-SNAPSHOT.jar apigateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/apigateway.jar"]

