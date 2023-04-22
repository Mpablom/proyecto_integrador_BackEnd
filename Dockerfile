FROM amazoncorretto:8-alpine-jdk
MAINTAINER PabloMoya
COPY target/ap-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]