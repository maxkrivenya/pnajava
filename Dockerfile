FROM openjdk
ENV TZ="Europe/Minsk"

RUN groupadd myra && useradd myra -g myra
RUN install -d -m 0755 -o myra -g myra /apihell/service


#COPY . .
#RUN maven package

COPY target/Pnajava.jar /apihell/service/

WORKDIR /apihell/service

USER myra

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/apihell/service/Pnajava.jar"]
