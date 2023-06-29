FROM java:8
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo 'Asia/Shanghai' >/etc/timezone \
    && mkdir /app
WORKDIR /app
COPY ./target/iot-service-1.0-SNAPSHOT.jar /app/iot-service-1.0-SNAPSHOT.jar
EXPOSE 15401
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app/iot-service-1.0-SNAPSHOT.jar
