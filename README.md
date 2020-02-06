# WinePairing

## Contents

* [Project overview](#overview)
    * [The Brief](#brief)
    * [The concept](#concept) 
* [Design](#design)
* [Structure](#structure)
    * [Entity relationship diagram](#ERD)
    * [API structure](#API)
* [Technology](#technology)
* [Testing](#testing)
    * [Coverage](#coverage)
* [CI Pipeline](#pipeline)
    * [Guide](#guide)
    * [Pipeline Structure](#pipelinestructure)
* [Future improvements](#future)

<a name="overview"></a>
## Project Overview

<a name="brief"></a>
### The Brief

Create an OOP based application with utilisation of supporting tools, methodologies, 
technologies that encapsulate all core modules covered during training.

<a name="concept"></a>
### The Concept

An application that allows a restaurant to add a wine list and food menu to a database. Meals are assigned a wine pairing 
and all this information can be viewed by customers. Items in the database can be liked and deleted.

<a name="design"></a>
## Design

I used Trello for project planning and tracking. The board includes wireframes, user stories and acceptance criterea.  
[Trello Board](https://trello.com/b/rKU7R5e4/wine-pairing-application)

<a name="structure"></a>
## Structure

<a name="ERD"></a>
### Entity Relationship Diagram

![](https://raw.githubusercontent.com/ChloeAdcock/WinePairing/master/Documentation/ERD.png)

<a name="API"></a>
### API Structure

![](https://raw.githubusercontent.com/ChloeAdcock/WinePairing/master/Documentation/API%20structure.png)

<a name="technology"></a>
## Technology

* Kanban board - Trello
* Database - H2
* IDE - Eclipse and Visual Studio Code
* Version control - GitHub
* CI Server - Jenkins
* Build tool - Maven
* Automated testing - JUnit, Mockito and Selenium
* Live and testing environment - Amazon Web Services

![](https://raw.githubusercontent.com/ChloeAdcock/WinePairing/master/Documentation/CI%20Pipeline.png)

<a name="testing"></a>
## Testing

JUnit and Mockito were used to test the back-end and Selenium to test the front-end.  

A static report was generated using Sonarqube.    
[Static report](https://github.com/ChloeAdcock/WinePairing/blob/master/Documentation/Static%20report.png)  

A Surefire report was generated.    
[Surefire report](https://github.com/ChloeAdcock/WinePairing/blob/master/Documentation/Surefire%20Report.pdf)


<a name="coverage"></a>
### Back-end Test Coverage

![](https://raw.githubusercontent.com/ChloeAdcock/WinePairing/master/Documentation/Coverage.png)

<a name="pipeline"></a>
## CI Pipeline 

<a name="guide"></a>
### Guide

1. Run Jenkins inside an EC2 instance
    * Create a Jenkinsfile in the github branch
    * Create a Jenkins pipeline job using the Jenkinsfile in the branch
2. Run nexus inside an EC2 instance
    * Create a hosted and proxy repository in nexus
    * Add nexus dependencies to the pom.xml with the URL of the hosted repository
    * Create a settings.xml inside the .m2 directory with the URL of the proxy repository
3. Create a security group for the back-end EC2 instance, front-end EC2 instance and RDS
4. Create an RDS database
    * Use database security group
5. Create an EC2 instance for the back-end
    * Use IAM role with full RDS access and use the back-end security group
    * Change datasource URL in application.properties to the RDS endpoint
    * Install maven, MySql and docker in the back-end instance 
    * Create a [Dockerfile](https://github.com/ChloeAdcock/WinePairing/blob/deployable-dev/Scripts/Dockerfile) and [docker.sh](https://github.com/ChloeAdcock/WinePairing/blob/deployable-dev/Scripts/docker.sh) script
    * Connect to the RDS database and create a database for the project
6. Create an EC2 instance for the front-end
    * Use front-end security group
    * Change the proxy path in nginx.conf to the back-end instance IP
    * Install maven, docker and chrome in the front-end instance
    * Create a [dockerfe.sh](https://github.com/ChloeAdcock/WinePairingFE/blob/dev/Scripts/dockerfe.sh) and [selenium.sh](https://github.com/ChloeAdcock/WinePairingFE/blob/dev/Scripts/selenium.sh) script
7. Build the pipeline in Jenkins

<a name="pipelinestructure"></a>
### Pipeline Structure

![](https://raw.githubusercontent.com/ChloeAdcock/WinePairing/master/Documentation/Architecture.jpg)

<a name="future"></a>
## Future Improvements

* Add a users table to develop login and signup features
* Add entire menu to the database, including beers and gins
* Develop an ordering feature
