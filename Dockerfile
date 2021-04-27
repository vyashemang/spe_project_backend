FROM openjdk:11
ADD target/spe_project-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]


# For mysql container: docker run -d -p 3308:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_DATABASE=mess" mysql:8.0.23

# To create continer of spring boot: docker run -t --link docker-mysql2:mysql -p 8082:8082 mess_backend