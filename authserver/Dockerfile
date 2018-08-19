FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/auth-server-0.0.1-SNAPSHOT-exec.jar auth.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/auth.jar","--server.port=8081","--eureka.client.service-url.defaultZone=http://172.17.0.1:8761/eureka","--spring.redis.host=172.17.0.1"]