FROM maven:latest

COPY . /build

WORKDIR /build

RUN mvn clean package 

FROM java:8

WORKDIR /opt/WinePairing

COPY --from=0 /build/target/WinePairing.jar app.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "app.jar"]


