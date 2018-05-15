IN PROGRESS

# CityParking
A web application for managing the city parking spaces.

Used:
Java ,
Spring boot 

Driver model : Id, vehicleNumber, registrationDate, vip.

Get:
/drivers/{id} -> Get driver info by Id
/drivers/{id}/check  -> Check how much driver have to pay at the moment
/drivers/{id}/stop -> Stop parking and pay


Post:

/drivers   -> add Driver , start parking

Sample request: 

curl -i -H "Content-Type: application/json" -X POST -d'{
"vehicleNumber": "WW12345",
"vip" : "false"
 }' localhost:8080/drivers
 
 Sample response:
 
 {"id":1,"vehicleNumber":"WW12345",
 "registrationDate":"2018-05-15T11:54:05.3599625",
 "vip":false,
 "parked":true}  
 
 
