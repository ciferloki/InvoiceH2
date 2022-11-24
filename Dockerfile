FROM amazoncorretto:17-al2-jdk

ARG JAR_FILE=target/InvoiceManagement-0.0.1-SNAPSHOT.war

WORKDIR /opt/app

COPY ${JAR_FILE} app.war

ENTRYPOINT ["java","-jar","app.war"]