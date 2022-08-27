## spring-boot-rest-api

### Steps to use

1. Clone code
```
https://github.com/kapil019/spring-boot-rest-api
```

2. Crete Database 
```
CREATE database test;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);
```

3. Rename property file
```
mv src/main/resources/application.properties.sample src/main/resources/application.properties
```
4. Update db details in property file src/main/resources/application.properties

5. Run project


### API's

1. Add new user
```
POST http://localhost:8080/user/add

Request Body
{
    "name": "Kapil",
    "email": "kapil@yahoo.com",
    "phone": "999999999"
}
Response
{
    "data": null,
    "error": null,
    "message": "Data Saved Successfully"
}
```

2. Update user 

```
PUT: http://localhost:8080/user/3

Request Body
{
    "name": "Kapil 2",
    "email": "kapil2@yahoo.com",
    "phone": "999999992"
}
Response
{
    "data": null,
    "error": null,
    "message": "User details updated Successfully"
}
```

3. Fetch all users
```
GET: http://localhost:8080/user/all

Response
{
	"data": [{
			"id": 1,
			"name": "Kapil",
			"email": "test@gmail.com",
			"phone": "8882192757"
		},
		{
			"id": 2,
			"name": "Kapil 2",
			"email": "test2@gmail.com",
			"phone": "8882192752"
		}
	],
	"error": null
}
```

4. Fetch user by ID
```
GET: http://localhost:8080/user/1

Response
{
	"data": {
		"id": 4,
		"name": "Kapil K",
		"email": "test.k@gmail.com",
		"phone": "8882192732"
	},
	"error": null,
	"message": null
}


```

4. Delete user by ID
```
DELETE: http://localhost:8080/user/1

Response
{
    "data": null,
    "error": null,
    "message": "User deleted Successfully"
}
```