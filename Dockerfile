FROM openjdk:8
ADD target/tesler-demo-exec.jar /app/tesler-demo.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "tesler-demo.jar"]