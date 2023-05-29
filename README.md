Simple to-do list webservice
=========================
This is a simple to-do list backend using REST calls.

## Architectural decision

### Blocking vs NonBlocking HTTP
Using Reactive HTTP calls frameworks like Spring WebFlux and Project Reactor, that implement non-blocking, asynchronous
processing of requests, can be beneficial in scenarios where we expect a high volume of concurrent requests or when we
want to optimize resource utilization and responsiveness due to handling more requests with fewer threads.

Although in case of this application where the expected load and concurrency might not be extremely high,
and the processing time for each request might be relatively short, blocking HTTP calls can be sufficient and easier to
implement. That is why I have decided to implement this application using spring blocking rest API. 

### Relational vs Non-Relational Database
Usually when focus is on scalability and flexibility, a non-relational database can be a good choice as they  handle
large amounts of unstructured data for example JSON-like documents.

On the other hand, Relational databases offer strong data consistency and are well-suited for applications with complex
relationships and data integrity constraints.
I have assumed that application requires complex querying, such as retrieving tasks based on various criteria or
performing aggregations. that is why a relational database might be more suitable as also using H2 is one of the
Non-functional requirements for this challenge. 

### Contract first api design 
using this will help the process of documentation and development in realworld projects. that is why 
I used open api specification version 3.1 and generated the dtos and controller interface using the gradle plugin. 
I also added the generated codes to git repo for reference. 

### Containerization
I chose google jib as it abstracts away the complexities of Dockerfile. although having a docker file could give more 
flexibility, jib provides a simpler way that does not need docker daemon to build the container. 
although, since I am not using a remote docker repository, for building and running this project the docker daemon 
needs to be running.
please note that since the database is on memory, I did not use test container to run the integration tests on docker container.


### I18n support 
to support I18n, the field "message" in [SuccessResponseDTO.java] and [ErrorResponseDTO.java] could be a key that 
will be handled by client to extract the corresponding translation. since it is not recommended that server handles 
the translation, for now we just send an english message.


### Assumptions
1. due date can not be in past 
2. due date can be null
3. the automatic service for updating the past due items will update only the items in not done status
4. the automatic service for updating the items is implemented with a scheduler, but it in terms of performance would be 
better to do it using an event listener coming from for example a refresh page from user. 

### How to run
```
git clone git@github.com:mehrdadfk/todo-list.git
cd to todo-list


#to build and test
./gradlew build

#to creat the container on local repo 
./gradlew jibDockerBuild

#to run
docker run -p 8082:8082 todo-list:0.0.1-SNAPSHOT

```

### Possible improvement in case of having more time
1. usually having id as type long is not a good practice as it make cause problem in data migrations and communication with other systems.
that is why depending on the application id can be string or UUID.
2. I would like to add more tests specially running some performance tests and some automatic scenario based tests using 
libraries like cucumber. 
3. in terms of functional requirments would be nice to have some more features like category of to do items 


