FROM openjdk

WORKDIR /usr/src/app

COPY . .

RUN chmod +x mvnw

RUN ls -al

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "target/sort-save-0.0.1-SNAPSHOT.jar"]