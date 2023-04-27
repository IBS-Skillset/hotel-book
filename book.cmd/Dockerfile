FROM public.ecr.aws/amazoncorretto/amazoncorretto:11

WORKDIR /opt/app

COPY target/book.cmd.jar /opt/app/book.cmd.jar

ENTRYPOINT ["java", "-Dorg.apache.catalina.STRICT_SERVLET_COMPLIANCE=true", "-jar", "/opt/app/book.cmd.jar"]
