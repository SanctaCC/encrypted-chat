FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/encrypted-chat-0.0.1-SNAPSHOT-exec.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.redis.host=redis","","--eureka.client.service-url.defaultZone=http://zuul:8761/eureka"]