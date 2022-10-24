# Drones Assignment

This is a sample Java / Maven / Spring Boot(version 2.7.5) For creating a Drones Medication traditional transportation infrastructure.


All the configuration is done in the **docker-compose.yaml** file.

## How to?
In order to start the process, all you need to do is:
- Open your command-line terminal and browse to the root directory of this repository where **deploy.bat** file is located. (I am assuming you have cloned the repository already!)
- Execute the **deploy.sh** inside your terminal.

## The Application
Now that your database has been setup and the application has been startd, by the docker-compose (deploy.bat did the job), how do you access it?

So the application is hosted on a docker container which is accessible at the **http://localhost/** on port **8087**.

I have created an endpoint that allows you to test the endpoint by sending a message using a HTTP **POST** call on this endpoint: **http://localhost:8087/?message=Hello-From-Server**

You can use this cURL request to test the endpoint:

```
curl -X POST 'http://localhost:8087/?message=Hello-From-Server'
```

## The Database
In order to access the database, you can connect to it using the following details:
- Hostname: localhost
- Port: 3301
- Username: root
- Password: 123

A database, **drones_db** has been created, and you can find for example battery level historical data using this SQL query:
```
SELECT * FROM `drone_aud`;
```

## TODO
- add spring state machine for Drone States
- complete test cases
- add jacoco and test coverage


## Documentation
[Swagger UI](http://localhost:8087/swagger-ui/index.html)
[API Documentation](http://localhost:8087/api-docs)