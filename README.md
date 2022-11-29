# estatsdata
#### made by niklas brandtner
first draft for a data generator for estats austria.
generates data for a mysql database and saves them into the database

## to recreate project:
### prerequisites
you need to install docker
then create a docker network using `docker network create networkName`
next you want to create two docker container,
the first one is a mysql container, the second one a phpmyadmin (phpmyadmin isnt needed but can give a good GUI to work with and its easy to setup, if you dont want phpmyadmin you also dont need the network)
```
docker run -d --name mysqlContainer --network networkName -e MYSQL_ROOT_PASSWORD="yourPassword" -v C:/path/where/you/want/the/container:/var/lib/mysql -p 3306:3306 mysql
docker run -d --name phpmyadminContainer --network asgard -e PMA_HOST=mysqlContainer -p 8080:80 phpmyadmin
```
you can access the phpmyadmin gui at localhost:8080 now and login with you password and the username 'root'
next you only need to create a new database and run the generator.sql
