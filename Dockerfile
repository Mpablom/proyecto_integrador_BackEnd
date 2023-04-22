FROM amazoncorretto: 11-alpine-jdk                     //de que imagen de java partimos
MAINTAINER PabloMoya                                   //quien es el dueño
COPY target/ap-0.0.1-SNAPSHOT.jar  ap-app.jar          //copia el empaquetado y lo sube a github
ENTRYPOINT ["java", "-jar","/ap-app.jar"]              //es la instruccion que se va a ejecutar primero