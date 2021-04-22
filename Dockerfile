FROM openjdk:8-jdk-alpine
LABEL maintainer="ezequiassam@hotmail.com"

VOLUME /main-app

### Install timezone data
#RUN apk add --no-cache tzdata
### Default to TimeZone
#ENV TZ America/Bahia
### Default to UTF-8 file.encoding
#ENV LANG C.UTF-8

ADD target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar","/app.jar"]