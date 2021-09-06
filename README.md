# CryptoCurrencyService
# Instruction to run the project
  - Install JDK 8 or higher on your machine 
  - Install Maven on your machine
  - Install Redis Server
  - Install any IDE Tool ( IntelliJ) 
  - Install MySql DB and MySql workbench to view table 
 
 # Steps to run the service 
  - create schema with name (cryptoservice) on your local mysql 
  - change username and password of the datasource by your Mysql username and password 
  - start redis server on your machine 
  - apply command (maven clean install)
  - Start the server from the IDE , Server will be up and running on port 9090  , you can view the swagger through this URL   
              http://localhost:9090/swagger-ui/index.html#/  
              
  # What is Purpose of This service 
  
  This is service is mainly integrating with https://alternative.me/crypto/api/ that provide data on crypto currencies.
  It also provide Registeration API and ticker API which will be secured and need username and password ( Basic Authentication )
  
  # Technology used 
  
  - Spring Boot version 2.3.7.RELEASE
  - Swageer Documentation 
  - Spring Data JPA 
  - Mysql Connector 
  - Spring Data Redis 
  - Lombok
  - Spring boot test
  - MapStruct 
  - jacoco for test coverage
  - spring cloud openfiegn for client integration 
