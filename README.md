# Cards

Cards application

## Running the application

The default api port is 9090

   


## Api Documentation
1.Adding a User  role ADMIN for admin or Role MEMBER
```http
POST /api/auth/signup
```
Request
```javascript
{
    "email":"test@email.com",
    "password":"test",
    "role":"ADMIN"
}
```
Response
```javascript
{
    "message": "user registered successfully!"
}
```

2.User sign in .
   Use this endpoint to update Survivor details including the last_location .
   last_location[lat,long]
  
```http
POST /api/auth/signin
```
Request
```javascript
{
    "username":"test@email.com",
    "password":"test"
}
```
Response
```javascript
{
    "username": "test@email.com",
    "email": "test@email.com",
    "roles": [
        "ADMIN"
    ],
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtYWlsLmNvbSxBRE1JTiIsImlhdCI6MTY5MzQxOTcyMSwiZXhwIjoxNjkzNTA2MTIxfQ.--9iss2ZPWRrUwsRONeOBpIKmviI3dZQE4XHPtJlA-0",
    "tokenType": "Bearer"
}
```

3.Create a Card 

```http
POST /api/card/create
```
Request
```javascript
{
    "taskName":"testTask",
    "taskDescription":"testdescription",
    "color":"#FFFF00"
}
```
Response
```javascript
{
    "taskId": 1,
    "userId": 1,
    "taskName": "testTask",
    "taskDescription": "testdescription",
    "taskStatus": "To Do",
    "taskCreatedDate": "2023-08-30T22:36:34.525+00:00",
    "color": "#FFFF00"
}
```

4.Update Task
```http
PUT /api/card/{taskId}
```
Request
```javascript
{
    "taskName": "updateTask",
    "taskDescription": "updatedescription",
    "taskStatus": "Done",
    "color": "#FFFFF0"
}
```
Response
```javascript
{
  "message":"task updated" 
}
```

5.Delete Task
```http
DELETE /api/card/{taskId}
```
Request
```javascript
{
 
}
```
Response
```javascript
{
  "message":"task deleted " 
}
```


5.Fetch Task by ID
```http
GET /api/card/{taskId}
```
Response
```javascript
{
    "taskId": 8,
    "userId": 1,
    "taskName": "testTask4",
    "taskDescription": "testdescription4",
    "taskStatus": "To Do",
    "taskCreatedDate": "2023-08-30T22:36:34.525+00:00",
    "color": "#FFFF00"
}
```

6.Fetch user Tasks
```http
GET /api/card/list
```
Response
```javascript
[
    {
        "taskId": 4,
        "userId": 1,
        "taskName": "testTask",
        "taskDescription": "testdescription",
        "taskStatus": "To Do",
        "taskCreatedDate": "2023-08-30T18:53:12.125+00:00",
        "color": "colorString"
    },
    {
        "taskId": 5,
        "userId": 1,
        "taskName": "testTask2",
        "taskDescription": "testdescription2",
        "taskStatus": "To Do",
        "taskCreatedDate": "2023-08-30T19:25:48.200+00:00",
        "color": "colorString2"
    }
]  
```


   


