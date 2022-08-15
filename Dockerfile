FROM adoptopenjdk/openjdk8:x86_64-ubuntu-jre8u322-b06
RUN apt-get update -y && apt-get install -y fontconfig libfreetype6
WORKDIR /app
COPY target/MSSC*.war /app/msscmsvc.war
EXPOSE 8080
CMD java -DServer.port=8080 -Dspring.config.additional-location=/config/application-env.properties -jar /app/msscmsvc.war
