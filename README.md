This project simulates Instagram backend service which has been created with Java spring boot and relational database service (Mybatis) has been used.

1- Change directory to Instagram-Clone/backend/

2- Create the database

<div>
<pre id="code-block">
CREATE DATABASE `instagramDB`;
USE `instagramDB`;
  </pre>
</div>


3- Use .sql file provided in backend/sql to generate tables

4- Configure backend dependencies using pom.xml

5- Configure /backend/src/main/resources/application.properties change the values if your configs are different

<div>

  <pre id="code-block">
    
spring.application.name=backend

spring.datasource.url=jdbc:postgresql://localhost:5432/instagramDB
spring.datasource.username=postgres
spring.datasource.password=Postgre
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=true



mybatis.typeAliasesPackage= com.instagram.backend.model
mybatis.mapperLocations=classpath:mapper/*.xml
mybatis.configuration.map-underscore-to-camel-case=true

logging.level.com.instagram.backend=DEBUG
logging.level.org.mybatis=DEBUG

  </pre>
</div>

6- Run BackendApplication.java
