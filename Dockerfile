# syntax = docker/dockerfile:experimental
FROM openjdk:11-jre-slim

ARG applicationName
ARG bootCommand=bootJar
ARG jarExt=jar
ARG $APP_VERSION=1.0.0

ENV JAR_NAME="app.$jarExt"

WORKDIR /workspace/
COPY $applicationName/build/libs/*.$jarExt $JAR_NAME

ENV DEBUG_OPT="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
ENV JAVA_OPTS=""
ENV ENV_NAME="local"
EXPOSE 8080 5005
ENTRYPOINT ["sh", "-c", "java $DEBUG_OPT $JAVA_OPTS -Dspring.profiles.active=$ENV_NAME -Dinfo.app.env=$ENV_NAME -jar $JAR_NAME"]
