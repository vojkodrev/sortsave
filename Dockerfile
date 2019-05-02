FROM ubuntu

WORKDIR /usr/src/app

RUN apt-get install maven jdk

COPY . .

RUN ls -al

RUN mvn clean package

EXPOSE 8080

CMD ["java", "target/sort-save-0.0.1-SNAPSHOT.jar"]