FROM maven:3-jdk-8 as builder
LABEL maintainer "LibreHealth Infrastructure Team <infrastructure@librehealth.io>"
WORKDIR /build
COPY . .
RUN mvn clean package -U -Dmaven.test.skip -B


FROM registry.gitlab.com/librehealth/toolkit/lh-toolkit-docker:latest
LABEL maintainer "LibreHealth Infrastructure Team <infrastructure@librehealth.io>"
CMD ["dockerize","-wait","tcp://mysql:3306","-timeout","90s","catalina.sh","run"]
USER toolkit
COPY --chown=toolkit:toolkit --from=builder /build/omod/target/radiology*.omod /usr/local/tomcat/modules
