

# CityParking
A web application for managing the city parking spaces.

Used:
Java ,
Spring boot 

Driver model : Id, vehicleNumber, registrationDate, vip.

Get:
/drivers/{id} -> Get driver info by Id <br/>
/drivers/{id}/check  -> Check how much driver have to pay at the moment<br/>
/drivers/{id}/stop -> Stop parking and pay<br/>


Post:

/drivers   -> add Driver , start parking

Sample request: 

curl -i -H "Content-Type: application/json" -X POST -d'{ <br/>
"vehicleNumber": "WW12345", <br/>
"vip" : "false" <br/>
 }' localhost:8080/drivers <br/>
 
 Sample response:
 
 {"id":1,"vehicleNumber":"WW12345", <br/>
 "registrationDate":"2018-05-15T11:54:05.3599625", <br/>
 "vip":false, <br/>
 "parked":true}  <br/>
 
 
