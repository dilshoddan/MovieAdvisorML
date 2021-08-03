# MovieAdvisor
## Spring Boot MySQL Base Project

This application was developed to demonstrate Spring Boot with MySQL with simple API.

Technologies Used

- Spring Boot 2.4.4
- Spring Data JPA
- Lombok
- Docker MySQL 8.0

How to Run this application

Uncomment ONLY ONCE **src/main/resources/data.sql** so that the data could be created automatically

Next run COMMENT THE data.sql file so that there are no issues with dublicate data.

Clean build the project

```shell
$ ./gradlew clean build
```

Collect and build docker dependencies:

```shell
$ docker-compose build
```

Run the docker dependencies together with MovieAdvisor

```shell
$ docker-compose up
```

### Related Articles

- [Docker Compose For Spring Boot with MySQL](https://javatodev.com/docker-compose-for-spring-boot-with-mysql/)
- [Docker Compose For Spring Boot with MariaDB](https://javatodev.com/docker-compose-for-spring-boot-with-mariadb/)
