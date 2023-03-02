FROM amazoncorretto:11

WORKDIR /opt/app

COPY book.cmd/target/book.cmd.jar /opt/app/book.cmd.jar
COPY book.query/target/book.query.jar /opt/app/book.query.jar

ENTRYPOINT ["/usr/bin/java"]
CMD ["-Dspring.profiles.active=dev", "-Dorg.apache.catalina.STRICT_SERVLET_COMPLIANCE=true", "-jar", "/opt/app/book.cmd.jar", "/opt/app/book.query.jar"]
