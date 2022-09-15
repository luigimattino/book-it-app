# book-it-app
RESTful web application with Spring Boot.

# Description
Management of activities in recreational spaces: To manage the temporal allocation of resources, in general I defined the concepts of physical and temporal space as "slot" ("time_slot" and "space_slot"). From this I implemented with spring boot make use of the database in memory for demo. -
Gestione di attività in spazi ricreativi: Per gestire  l'allocamento temporale di risorse, in modo generale ho definito i concetti di spazio fisico e temporale come "slot" ("time_slot" e "space_slot"). Da ciò ho implementato il contesto spring boot con l'uso del database in memoria per uso di prova.

## Built With
*   [git](https://git-scm.com/) - Free and Open-Source distributed version control system.
*   [Maven](https://maven.apache.org/) - Dependency Management.
*   [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit.
*   [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications.
*   [Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
*   [Bootstrap](https://getbootstrap.com/) - Is the most popular HTML, CSS, and JavaScript framework for developing responsive, mobile-first websites.
*   [JQuery](https://jquery.com/) - JavaScript library that makes things like HTML document traversal and manipulation, event handling, animation, and Ajax much simpler with an easy-to-use API that works across a multitude of browsers.
*   [timetable.js](http://timetablejs.org/) - For the purpose of display a timeline that represents the resource.  


## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `it.lmattino.book.it.app.BookItApp` class from your IDE.

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open Command Prompt and Change directory (cd) to folder containing pom.xml
- Open NetBeans
  - File -> Open Project -> Navigate to the folder where you unzipped the zip
  - Select the project and then click Open Project
- Otherwise open Eclipse 
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the project
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
*This way application will run in embedded tomcat server using embedded `H2` database.*

Open URL `http://localhost:8080/home` in the browser.

### URLS
 * Home -> `http://localhost:8080/home`
 * Timeline page where to book resources -> `http://localhost:8080/timeline`
 * Space slots list page where insert & edit resources -> `http://localhost:8080/space-slots`
