FROM maven:3.8.1-jdk-11 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

FROM openjdk:11-jdk-slim
MAINTAINER Mpablom
COPY --from=build /target/ap-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]