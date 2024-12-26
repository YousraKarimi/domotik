import pika

# Configuration RabbitMQ
rabbitmq_host = 'localhost'
exchange = 'log_exchange'
queue = 'log_queue'
routing_key = 'log_routing_key'
RABBITMQ_USER = 'ing3'
RABBITMQ_PASS = 'paas'

credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
connection = pika.BlockingConnection(pika.ConnectionParameters(host=rabbitmq_host, credentials=credentials))
channel = connection.channel()

# Déclarer l'exchange (assurez-vous que l'exchange est déjà déclaré par le producer)
channel.exchange_declare(exchange=exchange, exchange_type='direct')

# Déclarer la queue (assurez-vous que la queue est déjà déclarée par le producer)
channel.queue_declare(queue=queue)

# Lier la queue à l'exchange (assurez-vous que la liaison est déjà faite par le producer)
channel.queue_bind(exchange=exchange, queue=queue, routing_key=routing_key)

def callback(ch, method, properties, body):
    message = body.decode()
    print(f"Consumed message: {message}")

channel.basic_consume(queue=queue, on_message_callback=callback, auto_ack=True)

print('Waiting for messages. To exit press CTRL+C')
channel.start_consuming()
