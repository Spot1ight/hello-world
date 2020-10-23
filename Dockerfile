FROM openjdk:8

WORKDIR /app

COPY target/hello-world.jar /hello-world.jar

CMD ["java", "-jar", "/hello-world.jar"]

