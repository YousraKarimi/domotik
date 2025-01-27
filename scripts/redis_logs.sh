#!/bin/sh
# Connexion à la VM intermédiaire
ssh -t discover@172.31.250.73 << 'ENDSSH'

# Connexion à la VM finale depuis la VM intermédiaire
ssh -t discover@192.168.25.4 << 'ENDSSHFINAL'

# Exécution des commandes Docker pour se connecter à Redis et exécuter des commandes redis-cli
docker exec -t redis redis-cli <<EOF
INFO keyspace

SELECT 0

KEYS *

LRANGE 1 0 -1

EOF

ENDSSHFINAL
ENDSSH
