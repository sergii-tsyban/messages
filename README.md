
### BUILD AND RUN
`$ mvn package`

`$ java -jar target/messages-1.0-SNAPSHOT.jar`

### TESTING
#### post new messages and ctreate users
POST
Content-Type: application/json
http://localhost:8080/api/messages/post
{
	"user" : "terry",
    "message" : "I am terry"
}
Empty response 200OK expected

POST
Content-Type: application/json
http://localhost:8080/api/messages/post
{
	"user" : "bill",
    "message" : "I am bill"
}
Empty response 200OK expected

POST
Content-Type: application/json
http://localhost:8080/api/messages/post
{
	"user" : "garry",
    "message" : "I am garry"
}
Empty response 200OK expected


#### make bill follow others
POST
Content-Type: application/json
http://localhost:8080/api/users/bill/follow
{
	"users" : [ "terry", "garry" ]
}
Empty response 200OK expected

#### check terry folloed by bill
GET
http://localhost:8080/api/users/terry/followers
Expected:
200OK
{
"users": [
  "bill"
],
}

#### show bill's wall
GET
http://localhost:8080/api/messages/wall/bill
Expected:
200OK
{
"user": "bill",
"message": "I am bill",
"postedDate": 1502208171007
}

#### show bill's timeline
GET
http://localhost:8080/api/messages/timeline/bill
Expected:
200OK
{
"user": "garry",
"message": "I am garry",
"postedDate": 1502208176420
},
  {
"user": "terry",
"message": "I am terry",
"postedDate": 1502208162617
}

