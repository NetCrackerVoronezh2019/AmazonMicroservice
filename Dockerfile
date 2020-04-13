FROM openjdk:13
ADD target/amazon-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","app.jar"]