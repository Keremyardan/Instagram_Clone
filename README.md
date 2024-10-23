This project simulates Instagram backend service which has been created with Java spring boot and relational database service (Mybatis) has been used.

1- Change directory to Instagram-Clone/backend/

2- Create the database

<div>
CREATE DATABASE `instagramDB`;
USE `instagramDB`;
</div>

3- Use .sql file provided in backend/sql to generate tables

4- Configure backend dependencies using pom.xml

5- Configure /backend/src/main/resources/application.properties change the values if your configs are different

<div>

# DB Username
spring.datasource.username=root
# DB Password
spring.datasource.password=123456
# JDBC Driver
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# JDBC URL
spring.datasource.url=jdbc:mysql://localhost:3306/instagramDB?serverTimezone=UTC

mybatis.typeAliasesPackage=ca.uottawa.ins.model
mybatis.mapperLocations=classpath:mapper/*.xml
mybatis.configuration.map-underscore-to-camel-case=true
  </pre>
</div>

6- Run backendApplication.java
