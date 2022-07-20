FROM amazoncorretto:11-alpine
ARG JAR_FILE=target/spring-boot-web.jar

ENV API_CURRENCY_URL http://172.17.0.3:8080/fixer/latest

WORKDIR /opt/app
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]