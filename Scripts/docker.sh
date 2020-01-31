#!/bin/bash
./delete.sh
sudo apt update
git clone https://github.com/ChloeAdcock/WinePairing.git 
cd WinePairing
git checkout Containerised 
docker build -t wines .
docker run -dit --restart unless-stopped -d -p 9090:8081 --name wine-app wines:latest
