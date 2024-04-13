FROM openjdk:17-oracle

COPY serviceAccountKey.json /app/serviceAccountKey.json

COPY build/libs/whiteboard-0.0.1-SNAPSHOT.jar /app/whiteboard-0.0.1-SNAPSHOT.jar

ENV GOOGLE_APPLICATION_CREDENTIALS=/app/serviceAccountKey.json

RUN chmod 755 /app/whiteboard-0.0.1-SNAPSHOT.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/whiteboard-0.0.1-SNAPSHOT.jar"]