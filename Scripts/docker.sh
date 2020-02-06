#!/bin/bash
docker stop wine-app
docker rm wine-app
docker rmi wines
sudo rm wine.jar
wget --output-document wine.jar http://3.11.84.155:8081/service/rest/v1/search/assets/download?repository=wine-hosted\&group=com.qa.bae\&name=WinePairing\&0.0.1-SNAPSHOT\&sort=version\&maven.extension=jar
docker build -t wines .
docker run -dit --restart unless-stopped -d -p 9090:8081 --name wine-app wines:latest

