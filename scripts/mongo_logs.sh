#!/bin/sh
# Connexion à la VM intermédiaire
ssh -t discover@172.31.250.73 << 'ENDSSH'

# Connexion à la VM finale depuis la VM intermédiaire
ssh -t discover@192.168.25.4 << 'ENDSSHFINAL'

# Exécution des commandes Docker pour se connecter à MongoDB et exécuter les commandes mongosh
docker exec -t mongodb mongosh --eval "
  db = db.getSiblingDB('domotik_db');
  db.messages.find().pretty();
"

ENDSSHFINAL
ENDSSH
