# encrypted-chat
Encrypted messaging app
# Run the application
```
mvn clean package -Dmaven.test.skip=true && docker-compose build && docker-compose up
```
# Ports
* Gateway: 8761
* Authorization Server: 8081
* Chatrooms: 8080
* Redis: 6379
