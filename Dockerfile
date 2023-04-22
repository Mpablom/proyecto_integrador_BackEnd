FROM amazoncorretto:8-alpine-jdk
MAINTAINER PabloMoya
COPY target/ap-0.0.1-SNAPSHOT.jar /usr/share/app.jar
ENTRYPOINT ["/usr/bin/java","-jar","/usr/share/app.jar"]