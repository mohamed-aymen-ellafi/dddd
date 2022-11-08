FROM maven:3.8.2-jdk-8
RUN apt-get install curl
RUN curl -u admin:1234 -o achat.jar "http://172.10.0.140:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar" -L
ENTRYPOINT ["java","-jar","/achat.jar"]
EXPOSE 8082
