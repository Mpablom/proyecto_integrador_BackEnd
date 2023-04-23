FROM amazoncorretto:8
MAINTAINER PABLO
COPY target/ap-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080