#!/bin/sh
ssh -t discover@172.31.250.73 << 'ENDSSH'
ssh -t discover@192.168.25.5 << 'ENDSSHFINAL'
docker logs -f notification_manager
ENDSSHFINAL
ENDSSH
