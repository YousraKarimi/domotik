#!/bin/sh
ssh -t discover@172.31.250.73 << 'ENDSSH'
ssh -t discover@192.168.25.5 << 'ENDSSHFINAL'
./cleanup_docker.sh
cd domotik/z-deploy/
git pull
docker compose -f docker-compose-middleware.yaml up -d --build
ENDSSHFINAL
ENDSSH