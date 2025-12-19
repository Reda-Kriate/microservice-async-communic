FROM eclipse-temurin:21-jre
WORKDIR /app

ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=0 app/dependencies/ ./
COPY --from=0 app/snapshot-dependencies/ ./
COPY --from=0 app/spring-boot-loader/ ./
COPY --from=0 app/application/ ./

EXPOSE 8080

ENTRYPOINT ["java”,“org.springframework.boot.loader.launch.JarLauncher"]
