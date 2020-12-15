#Project title
###Retrieve JSON data

#Libraries used
>Spring Boot Intializr
Custom: Exceptions, 
Rest Authentication
Spring Boot Test,
JSON Library(input.json)
Jackson Mapper,CRUD repository,lombok

#Project
1.Head over to Spring intializr project configuring with JDK 1.8.
2.Enter Artifact as "jsondb"
3.Change Package name to "com.thebrgtest.jsondb"
4.Select Web,H2,JPA repository,Lombok.
5.Generate Project and work on the features.

#Features
These services can perform:
1. Imports JSON data into relational database(H2). 
2. Defining the Entities, Service, and Repository.
3. A Restful API that fetches details by querying in multiple ways likely by passing path variable & Request parameter.
5. Used QueryAnnotation to fetch over the details from database.

#Installing
1. Installed Java & JDK 1.8
2. Installed Intellij IDEA edition and configured necessary plugins.
3.Configure Database and Log levels
   
#Versions
1.0-SNAPSHOT: Contains import JSON data, Retrieve data

#Developers
Lahari Dintakurthi(laharidin93@gmail.com)

#Relational Database
http://localhost:8080/h2-console
>JDBC URL : jdbc:h2:mem:input
> Username: sa
> NO PWD

#Example links
http://localhost:8080/input/list (Whole list displayed with pagination)

http://localhost:8080/api/details/11
(Particular ProductId details)

http://localhost:8080/api/showSeatsAvailable?productId=11&typeId=12
(Seat Available data with ProductId and TypeId)

http://localhost:8080/api/showSeatsAvailable/time?startDate=2020-12-09&endDate=2020-12-10
(Seat Available data with Time period)

http://localhost:8080/api/showSeatsAvailable/all?productId=214&typeId=218&startDate=2020-12-11&endDate=2020-12-19
(Seat Available data with all parameters)

/***select distinct seats_available from input i, input_properties ip, property  p where product_id = 11
and i.product_id = ip.input_product_id and start >= '2020-12-09' AND end <= '2020-12-10'
and type_id = properties_type_id and type_id = 12***/