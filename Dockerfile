# FROM openjdk:8-jdk-alpine
# VOLUME /tmp
# EXPOSE 8080
# RUN mkdir -p /app/
# RUN mkdir -p /app/logs/
# ADD target/order-0.0.1-SNAPSHOT.jar /app/app.jar
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]

FROM openjdk:11.0.1-jre-slim-stretch
ARG APPLICATION_NAME
ARG APPLICATION_VERSION
ENV APPLICATION_NAME=${APPLICATION_NAME}
ENV APPLICATION_VERSION=${APPLICATION_VERSION}
ENV JAVA_OPTS -Xmx90m -Xms90m -XX:MaxMetaspaceSize=80m
COPY target/${APPLICATION_NAME}-${APPLICATION_VERSION}.jar ${APPLICATION_NAME}-${APPLICATION_VERSION}.jar
EXPOSE 80
CMD java ${JAVA_OPTS} -Dserver.port=80 -Dspring.config.additional-location=file:/config/ -Dlogger.encode=true -Dlogging.config=/logback.xml -jar ${APPLICATION_NAME}-${APPLICATION_VERSION}.jar
