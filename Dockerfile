FROM openjdk:17

WORKDIR /usr/src/app

COPY target/rinhadebackend2024q1-0.0.1.jar .

CMD ["java", "-jar", "rinhadebackend2024q1-0.0.1.jar"]