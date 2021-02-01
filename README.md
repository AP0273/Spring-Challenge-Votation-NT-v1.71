# VotatioNT-v1.71
 
 ## Api rest for Management of votation sessions
 ### Functions
 * Post people to vote
 * Post a voting session with a certain duration
 * Post a vote
 * Vote Counting and percentage of approval
 * Closing of the voting session at the time defined in its creation

 
 
 # Prerequisites
 Java Runtime Environment **(JRE) 11**

# Built With

[Maven](https://maven.apache.org/) - Dependency Management\
[Spring Boot](https://spring.io/) - Framework used\
[Swagger](https://swagger.io/) - API documentation

# Usage

After starting the service you can use swagger on [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to send requests,
or [http://localhost:8080/h2/](http://localhost:8080/h2/) to acess database on a test scenario.

## Requests Requirements 

### Insert Person Requirements
* Valid international Phone syntax
* Unique Phone (Not Already taken)
* Valid Email syntax
* Unique Email (Not Already Taken)
* Password Strength Required (should be at least 8-character long containing upper and lower case letters,numbers and symbols.)
* Valid Cpf

### Insert Schedule
* Registred Person Cpf and Password

### Insert Vote
* Existent Schedule id, Schedule State Ongoing
* Registred Person Cpf and Password, Cpf able to vote on the response of heroku -> https://user-info.herokuapp.com/users/(cpf person without paretesis)
* The person must not have already voted on the target schedule

### Data Update or Delete
* Requires Authentication with Cpf and Password from from the data submitter
