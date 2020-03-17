FROM openjdk:8

EXPOSE 8761

MAINTAINER Zemtsov Alexey Vladimirovich <zemtsov1997@yandex.ru>

ENV DEBUG_LEVEL INFO

WORKDIR /opt/eureka-server

ADD target/eureka-server-*.jar eureka-server.jar

ENTRYPOINT java -jar -DDEBUG_LEVEL=$DEBUG_LEVEL eureka-server.jar