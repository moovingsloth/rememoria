FROM amd64/amazoncorretto:21
WORKDIR /app
COPY ./build/libs/rememoria-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-Dspring.profiles.active=dev", "-jar", "app.jar"]