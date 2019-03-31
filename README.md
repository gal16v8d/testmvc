# Test MVC

This project pretend to ilustrate how to work with some technologies and expose rest services

## Getting Started

### Prerequisites

* Git
* MVN
* Java 7 or upper

### Installing

Using this project on **Spring3XML or Spring4XML tag**
Works using PostgreSQL 10 + Jboss + Spring + JavaEE api + Java 7, when you start it
it will use C3P0 to connect and create/update DB schema. Also it will expose two services
You can check it using [Insomnia](https://insomnia.rest/),  you can import the calls using
the Json file that is on dev_tools folder.

## Deployment

For deploy you just need to mvn clean install the project, after that go to test-rest module and get 
the **test-rest.war** file, after that put it on deployment folder in a jboss-as-7.1.1.Final, probably
it can works in openliberty, glassfish or payara, but I don't test on that servers at the moment.

Also you can run the application as desktop java app by run the class -> test-util\src\main\java\co\com\gsdd\test\main\TestMain.java
It provides a basic crud test, that you can edit.

For rest service bypass authentication you can use any valid usuario on DB. By calling SecurityCheck service
using that user and password. The auth is given by a cookie, it will be received on header. So for the secured services
you must pass that cookie.

## Authors

* **Alex Galvis** - *Initial work* 


