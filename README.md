# microservices_vodqa
Microservices Testing

Flight Reservation demo.

Project Contains Three Services

Flightdetail service
user detail service
Reservation Service

Clone this Application

Run command : docker network create abc

under /userdetails folder
Execute Command : ./run dev

under /flightdetails folder
Execute Command : ./run dev

under /reservation folder
Execute Command : ./run dev

under /flightdetails-mocks folder
Execute Command : ./run dev

under /flightdetails-mocks/proxy-mode folder
Execute Command : ./run dev

under /flightdetails-mocks folder Execute Command :
./run dev

After 5 minutes

Access the 5 services using below URLS

http://localhost:7070/userdetails/swagger-ui.html#/user-controller

http://localhost:6060/reservation/swagger-ui.html#/reservation-controller

http://localhost:9090/flightdetails/swagger-ui.html

http://localhost:4222/flightdetails/findFlight/1

http://localhost:4222/flightdetails/findFlight/1



