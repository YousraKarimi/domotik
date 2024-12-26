import pika

# Configuration RabbitMQ
rabbitmq_host = 'localhost'
exchange = 'config_exchange'
queue = 'config_queue'
routing_key = 'config_routing_key'
RABBITMQ_USER = 'ing3'
RABBITMQ_PASS = 'paas'

credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
connection = pika.BlockingConnection(pika.ConnectionParameters(host=rabbitmq_host, credentials=credentials))
channel = connection.channel()

# Déclarer l'exchange
channel.exchange_declare(exchange=exchange, exchange_type='direct')

# Déclarer la queue
channel.queue_declare(queue=queue)

# Lier la queue à l'exchange
channel.queue_bind(exchange=exchange, queue=queue, routing_key=routing_key)

def publish_messages():
    message = "Hello from RabbitMQ Config Producer!"
    channel.basic_publish(exchange=exchange, routing_key=routing_key, body=message)
    print(f"Sent '{message}' to exchange '{exchange}' with routing key '{routing_key}'")

if __name__ == "__main__":
    publish_messages()
    connection.close()