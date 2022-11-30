# estatsdata
---
#### made by niklas brandtner
first draft for a data generator for estats austria.
generates data for a mysql database and saves them into the database

## to recreate project:
### prerequisites
you need to install docker and have a ide for developing java (intellij works best)
then create a docker network using 
```bash
docker network create networkName
```
next you want to create two docker container,
the first one is a mysql container, the second one a phpmyadmin (phpmyadmin isn't needed but can give a good GUI to work with, and it's easy to set up, if you don't want phpmyadmin you also don't need the network)
```bash
docker run -d --name mysqlContainer --network networkName -e MYSQL_ROOT_PASSWORD="yourPassword" -v C:/path/where/you/want/the/container:/var/lib/mysql -p 3306:3306 mysql
docker run -d --name phpmyadminContainer --network asgard -e PMA_HOST=mysqlContainer -p 8080:80 phpmyadmin
```
you can access the phpmyadmin gui at localhost:8080 now and login with you password and the username 'root'
next you only need to create a new database and run the generator.sql file located in the sql folder (the estats.sql file in the sql folder is a testfile you can use to see if ur database works it contains INSERT operations with testdata)
now you can simply clone the git repository on your pc and execute the MySQL.java file (you might have to change line 16 depending on the name of your container)
