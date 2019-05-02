FROM ubuntu

WORKDIR /usr/src/app

RUN apt-get update
RUN apt-get install -y maven default-jre

COPY . .

RUN ls -al

RUN mvn clean package

EXPOSE 8080

CMD ["java", "-jar", "target/sort-save-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]
