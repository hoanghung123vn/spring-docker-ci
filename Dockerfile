FROM centos
# Upgrading system
RUN yum -y upgrade
# Downloading & Config Java 11
RUN yum -y install java-11-openjdk
EXPOSE 8080
# Coppy and run Application
VOLUME /tmp
COPY target/demo-0.0.1-SNAPSHOT.jar /demo.jar
RUN sh -c 'touch /demo.jar'
ENTRYPOINT ["java","-jar","/demo.jar"]
