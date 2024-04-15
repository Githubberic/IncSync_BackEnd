FROM openjdk:17-oracle
COPY build/libs/gateway-0.0.1-SNAPSHOT.jar /app/gateway-0.0.1-SNAPSHOT.jar
RUN chmod 755 /app/gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/gateway-0.0.1-SNAPSHOT.jar"]
