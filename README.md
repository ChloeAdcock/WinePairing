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
* [Cloud-based Architecture](#cloud)
    * [Guide](#guide)
    * [Cloud Structure](#cloudstructure)
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

<a name="cloud"></a>
## Cloud-Based Architecture 

<a name="guide"></a>
### Guide

1. Create RDS database
    * Use latest version of MySQL
    * Enable authentication with password and IAM role
2. Create an Ubuntu EC2 instance for back-end
    * Assign instance an IAM role with RDS full access
3. SSH into the virtual machine
    * Create [install.sh](https://github.com/ChloeAdcock/WinePairing/blob/Containerised/Scripts/install.sh), [delete.sh](https://github.com/ChloeAdcock/WinePairing/blob/Containerised/Scripts/delete.sh) and [docker.sh](https://github.com/ChloeAdcock/WinePairing/blob/Containerised/Scripts/docker.sh) scripts
    * Make the scripts executable: chmod 700 install.sh delete.sh docker.sh
    * Run install.sh
    * Exit then login again
    * Access the RDS database: mysql -h *RDS endpoint* -P 3306 -u admin -p
    * Create the database in the MySQL console: CREATE DATABASE wine_pairing_db;
    * Exit the MySql console: exit
    * Change the datasource URL in application.properties to the RDS endpoint
    * Run docker.sh
4. Create snapshot of the back-end instance
5. Create an AMI of the snapshot
6. Create a launch configuration from the back-end AMI
7. Create an auto-scaling group from the launch configuration
    * Set the initial number of instances 
    * Set the minimum number of instances to 1 and the maximum to 2
    * Set the target CPU utilisation to 50 with 120 seconds to warm up after scaling
    * Disable scale-in
8. Create a target group for the back-end auto-scaling group
    * Set the HTTP protocol to port 9090
9. Create a load-balancer for the back-end instances
    * Set the listener to port 9090
    * Select all availability zones
    * Select the default security group which allows TCP access from anywhere
10. Edit the back-end auto-scaling group so that the target group is the back-end target group

<a name="cloudstructure"></a>
### Cloud Structure

![](https://raw.githubusercontent.com/ChloeAdcock/WinePairing/master/Documentation/Architecture.jpg)

<a name="future"></a>
## Future Improvements

* Add a users table to develop login and signup features
* Add entire menu to the database, including beers and gins
* Develop an ordering feature
