FROM java:8
EXPOSE 8080
VOLUME /tmp
ADD target/perseus-user-service-0.0.1-SNAPSHOT.jar perseus-user-service.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /perseus-user-service.jar" ]
