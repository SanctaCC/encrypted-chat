FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/auth-server-0.0.1-SNAPSHOT-exec.jar auth.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/auth.jar","--server.port=8081","--eureka.client.service-url.defaultZone=http://zuul:8761/eureka","--spring.redis.host=redis"]