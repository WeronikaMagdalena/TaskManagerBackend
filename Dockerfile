FROM amazoncorretto:21

WORKDIR /usr/local/bin

COPY target/taskmanager-0.0.1-SNAPSHOT.jar ./taskManager.jar

CMD ["java", "-jar", "taskManager.jar"]