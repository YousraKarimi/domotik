
#deploy db
sudo docker compose -f docker-compose-db.yaml up -d

#deploy queues
sudo docker compose -f docker-compose-queues.yaml up -d

#deploy middleware
sudo docker compose -f docker-compose-middleware.yaml up -d

#deploy backend
sudo docker compose -f docker-compose-backend.yaml up -d

#deploy reverse proxy
sudo docker compose -f docker-compose-reverse-proxy.yaml up -d