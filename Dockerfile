#Downloads the jdk8 docker image.
FROM adoptopenjdk/openjdk8:ubi 
# / is the new working directory
WORKDIR /
# in working directory / adding jar 
ADD glpg-service.jar glpg-service.jar
# Switching swing framework to run on docker
ENV SPRING_PROFILE_ACTIVE=docker
#Command to excecute jar file
ENTRYPOINT ["java", "-jar", "glpg-service.jar"]
#Expose 8081 port
EXPOSE 8081