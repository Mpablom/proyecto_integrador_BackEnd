FROM amazoncorretto:8-alpine-jdk
MAINTAINER PabloMoya
COPY target/ap-0.0.1-SNAPSHOT.jar ap-app.jar
ENTRYPOINT ["java","-jar","/ap-app.jar"]