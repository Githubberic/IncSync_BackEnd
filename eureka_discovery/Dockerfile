FROM openjdk:17-oracle
COPY build/libs/eureka_discovery-0.0.1-SNAPSHOT.jar /app/eureka_discovery-0.0.1-SNAPSHOT.jar
RUN chmod 755 /app/eureka_discovery-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app/eureka_discovery-0.0.1-SNAPSHOT.jar"]
