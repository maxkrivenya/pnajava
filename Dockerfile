FROM openjdk
ENV TZ="Europe/Minsk"

RUN groupadd myra && useradd myra -g myra
RUN install -d -m 0755 -o myra -g myra /apihell/service

COPY ./target/Pnajava.jar /apihell/service
USER myra
WORKDIR /apihell/service

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/apihell/service/Pnajava.jar"]
