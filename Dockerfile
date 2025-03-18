FROM openjdk:21-jdk-oraclelinux8 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/websockets-0.0.1-SNAPSHOT.jar websockets.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","websockets.jar"]