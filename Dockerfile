FROM openjdk:17-alpine

WORKDIR /app

COPY ./target/result.jar ./backend.jar

CMD ["java", "-jar", "./backend.jar"]
