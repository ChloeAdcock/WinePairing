#!/bin/bash
sudo apt update
sudo apt install maven
sudo apt install awscli
sudo apt install git
sudo apt install mysql-client-core-5.7 -y
curl https://get.docker.com | sudo bash
sudo usermod -aG docker $(whoami)
