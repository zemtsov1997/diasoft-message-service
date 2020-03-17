FROM openjdk:8

EXPOSE 8009

MAINTAINER Zemtsov Alexey Vladimirovich <zemtsov1997@yandex.ru>

ENV DEBUG_LEVEL INFO

WORKDIR /opt/message-service

ADD target/message-service-*.jar message-service.jar

ENTRYPOINT java -jar -DDEBUG_LEVEL=$DEBUG_LEVEL message-service.jar