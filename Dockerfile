FROM amazoncorretto:11-alpine-jdk
MAINTAINER Mpablom
COPY target/ap-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]