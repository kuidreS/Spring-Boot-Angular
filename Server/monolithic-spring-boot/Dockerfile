FROM openjdk:8-jdk-alpine
MAINTAINER Vitalii Serciuk <vitaliy.serdiuk@gmail.com>
VOLUME /tmp
ADD target/monolithic-spring-boot-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENV TZ=Europe/Prague
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
EXPOSE 8888
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$profile -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]