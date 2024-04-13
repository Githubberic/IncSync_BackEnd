FROM openjdk:17-oracle
COPY build/libs/websocket-0.0.1-SNAPSHOT.jar /app/websocket-0.0.1-SNAPSHOT.jar

RUN chmod 755 /app/websocket-0.0.1-SNAPSHOT.jar

EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/websocket-0.0.1-SNAPSHOT.jar"]