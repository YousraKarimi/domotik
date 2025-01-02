import pika

from applogger import logger
from notificationmanager.models import RtmqParams
from notificationmanager.redis_utils import write_to_redis


def setup_rabbitmq(host, credentials):
    connection = pika.BlockingConnection(pika.ConnectionParameters(host=host, credentials=credentials))
    return connection

def setup_channel(channel, exchange, queue, key):
    channel.exchange_declare(exchange=exchange, exchange_type='direct')
    channel.queue_declare(queue=queue)
    channel.queue_bind(exchange=exchange, queue=queue, routing_key=key)

def rabbitmq_consume(params : RtmqParams, redis):
    logger.info("Starting rabbit consumer")
    setup_channel(channel=params.channel, exchange=params.exchange, queue=params.queue, key=params.key)
    def callback(ch, method, properties, body):
        message = body.decode()
        logger.info(f"Consumed from RabbitMQ: {message}")
        user = 'donatien'
        write_to_redis(params=redis, key=user, value=message)

    params.channel.basic_consume(queue=params.queue, on_message_callback=callback, auto_ack=True)
    params.channel.start_consuming()