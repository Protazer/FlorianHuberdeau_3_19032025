# ChâTop Back-End Project

![ChâTop Logo](src/main/resources/static/images/chatop.png)

This Back-End project is dedicated to ChâTop. ChâTop wants to develop an online
portal to allow potential tenants to contact the owners of the different
properties they wish to rent.

[![forthebadge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://forthebadge.com)
[![forthebadge](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://forthebadge.com)

## Configuration

Generate RSA key to resources project folder for JWT Security.

```
cd src/main/resources
openssl genrsa > private.key
openssl rsa -in private.key -pubout -out public.key 
```

Create a `.env` file (in project root directory), containing variables according
to
`.env.schema`.

```
#API PORT
    TOMCATS_PORT=myPort

#DATABASE
    DATABASE_URL=myUrl
    DATABASE_NAME=myDatabaseName
    DATABASE_USERNAME=myDatabaseUsername
    DATABASE_PASSWORD=myDatabasePassword

#CLOUDINARY
CLOUDINARY_URL=cloudinary://<your_api_key>:<your_api_secret>@<your_cloud_name>

```

Create a new database for your application and add all the tables to your
database:

```sql
CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

ALTER TABLE `RENTALS` ADD FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`);

```

## Installation Procedure

**Cloning the project:**

1. Clone this repository from GitHub:
   `git clone https://github.com/Protazer/FlorianHuberdeau_3_19032025`
2. install dependencies with `mvn install` command.
3. Generate RSA key to resources project folder.
4. Create and Configure the app in `.env` file.

5. Run the application using your IDE or by running `mvn spring-boot:run` in the
   project directory.
6. You can also use Postman to test API calls.

## API Documentation

The API is documented using Swagger. You can access the API documentation by
navigating to the Swagger URL after running the server
`http://localhost:3001/swagger-ui/index.html`.

