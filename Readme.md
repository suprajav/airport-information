## Airport Information Application
The Airport information application is a REST API that retrieves the runways details of airports that are located in various countries.
We can also query this API to retrieve the top "n" countries with highest number of airports.

### Techologies used
- Java 17, Spring Boot
- Maven 3
- H2 In-memory database

### Airport data
The application expects the airport data in csv files in its root folder.
The csv data is read and loaded into an in-memory H2 database.

### Building and running the application
Run the following commands on the root directory of the application.
- mvn clean install
- mvn spring-boot:run

You can also run this application in an IDE like IntelliJ.

### Endpoints

##### Get All Runways When Country Code or Country Name Is Provided

Examples:
- GET: http://hostname:port/runways?countryCode=AT
- GET: http://hostname:port/runways?countryName=Austria
- GET: http://hostname:port/runways?countryName=Aus (Fuzzy search)

##### Get "n" countries that have the highest number of airports
Examples:
- GET: http://hostname:port/countries?topAirports=10

### Testing
Import the postman collection and run the collection.

