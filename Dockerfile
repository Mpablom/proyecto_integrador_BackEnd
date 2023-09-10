FROM amazoncorretto:11-alpine-jdk

MAINTAINER Mpablom

COPY . .

EXPOSE 8080

ENTRYPOINT ["java","-jar","target/ap-0.0.1-SNAPSHOT.jar"]