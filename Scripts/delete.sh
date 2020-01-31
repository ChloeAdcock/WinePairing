#!/bin/bash
rm -rf WinePairing
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker network prune
