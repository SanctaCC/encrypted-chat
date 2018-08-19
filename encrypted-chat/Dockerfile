FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/encrypted-chat-0.0.1-SNAPSHOT-exec.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.redis.host=172.17.0.1","","--eureka.client.service-url.defaultZone=http://172.17.0.1:8761/eureka"]