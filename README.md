# Tesler Simple Project
[Live Demo](http://demo.tesler.io/)
login: `demo`, password: `demo`

### Prerequisites:
#####
* java 8+
* maven 3.6+
* node.js 14.4+
* npm 6.14+
* git
* docker
* docker-compose

## Getting started with docker (3 commands)
* install dependencies and build front-end
```
mvn clean install -PUI
```
* install dependencies and build back-end
```
mvn clean install
```
* start environment in one command and see http://localhost (or separately node 1 http://localhost:8081 and node 2 http://localhost:8082)
```
docker-compose up --build --force-recreate -d
```
* (optional) for remote debug ([IntelliJ](https://www.jetbrains.com/help/idea/tutorial-remote-debug.html), [Eclipse](https://www.eclipse.org/jetty/documentation/current/enable-remote-debugging.html)) use
##### node 1
```
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8981
```
##### node 2
```
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8982
```


## Second and further starts with docker
Fast apply changes to backend.
Let us assume we changed only backend (so we'll skip mvn clean install -PUI)
```
docker-compose stop tesler-demo-node-1
docker-compose stop tesler-demo-node-2
docker-compose rm -sfv tesler-demo-node-1
docker-compose rm -sfv tesler-demo-node-2
mvn clean install -U && docker-compose build && docker-compose up -d tesler-demo-node-1 && docker-compose up -d tesler-demo-node-2
docker-compose ps
```

Full clean restart. For local development only!
```
docker-compose stop
docker-compose rm -sfv
docker system prune
docker volume prune
mvn clean install -PUI -U && mvn clean install -U && docker-compose up --build -d
docker-compose ps
```