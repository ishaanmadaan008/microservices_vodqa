# microservices_vodqa
### Microservices Testing

**Flight Reservation Demo**

Project contains following 3 services:

* Flightdetail service
* UserDetail service
* Reservation Service

Clone this Application
<br></br>
#### Execution Steps
~/microservices_vodqa: `docker network create abc`

~/microservices_vodqa/userdetails: `./run dev`

~/microservices_vodqa/flightdetails: `./run dev`

~/microservices_vodqa/reservation: `./run dev`


**Access all 3 services using below web endpoints after ~4-5 minutes:**

* <http://localhost:7070/userdetails/swagger-ui.html#/user-controller>

* <http://localhost:6060/reservation/swagger-ui.html#/reservation-controller>

* <http://localhost:9090/flightdetails/swagger-ui.html>

