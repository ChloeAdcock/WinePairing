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
## Back-end Test Coverage

![](https://raw.githubusercontent.com/ChloeAdcock/WinePairing/master/Documentation/Coverage.png)

<a name="future"></a>
## Future Improvements

* Add a users table to develop login and signup features
* Add entire menu to the database, including beers and gins
* Develop an ordering feature
