This project simulates Instagram backend service which has been created with Java spring boot and relational database service (Mybatis) has been used.

1- Change directory to Instagram-Clone/backend/

2- Create the database

CREATE DATABASE `instagramDB`;
USE `instagramDB`;

<div>
  <button onclick="copyCode()">Copy Code</button>
  <pre id="code-block">
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

<script>
  function copyCode() {
    var codeBlock = document.getElementById("code-block").innerText;
    navigator.clipboard.writeText(codeBlock).then(function() {
      alert("Code copied to clipboard!");
    }, function() {
      alert("Failed to copy code.");
    });
  }
</script>

6- Run backendApplication.java
