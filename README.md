# 40forty - A ~~Social Media~~

## Project Description

This is a social media application created with Spring and Angular. Users can post on a global feed, react on other posts, send messages in a global chatroom, create a profile, and view other profiles. Through an AWS Lambda function, all posts, comments, media, and other public data is deleted from the database every 40 days. Originally created by Joe Rice, Brandon Dcruz, Devin Kadrie, and Luis Mendoza as Project 2 for the Revature training program in April 2021.

## Technologies Used

- Spring
- Angular - version 8
- Java - version 1.8
- PostgrSQL - version 13.2
- HTML - version HTML5
- CSS - version CSS3
- JavaScript - ES6
- JUnit
- Log4J
- JDBC
- AWS RDS
- EC2
- Jenkins
- S3

## Features

- Register an account and login.
- Create posts and react to other posts.
- Search for users.
- View posts made by others in a public global feed.
- Create and edit personal profile information (bio, profile pic, email, password, etc).

### To-do list:

- Finish web socket chatroom feature
- Add comments
- Refactor with Spring Boot and OAuth with Spring Security
- Add email service

## Getting Started
   
> Clone this repository
```
git clone https://github.com/JericRice/social-media-site.git
```

> Download Tomcat
```
https://tomcat.apache.org/
```

> npm install in angular project folder
```
npm i 40forty/fortyforty
```

## **Usage**


> Run with Tomcat configuration in IDE

> Run angular project
```
cd 40forty/fortyforty
npm i
```

> Visit localhost in web browser
```
http://localhost:40forty
```

## **License**

This project uses the following license: [<The MIT License>](https://www.mit.edu/~amini/LICENSE.md).
