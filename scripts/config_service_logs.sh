#!/bin/sh
ssh -t discover@172.31.250.73 << 'ENDSSH'
ssh -t discover@192.168.25.2 << 'ENDSSHFINAL'
docker logs -f config_service
ENDSSHFINAL
ENDSSH
