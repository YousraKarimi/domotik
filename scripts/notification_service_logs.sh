#!/bin/sh
# Connexion au conteneur MongoDB et surveillance des changements
ssh -t discover@172.31.250.73 << 'ENDSSH'
ssh -t discover@192.168.25.4 << 'ENDSSHFINAL'
docker logs -f notification_service

ENDSSHFINAL
ENDSSH
