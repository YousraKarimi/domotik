import pika

from applogger import logger
from notificationmanager.message_utils import read_log, build_error
from notificationmanager.models import RtmqParams
from notificationmanager.redis_utils import write_to_redis


def setup_rabbitmq(host, credentials):
    connection = pika.BlockingConnection(pika.ConnectionParameters(host=host, credentials=credentials))
    return connection

def setup_channel(channel, exchange, queue, key):
    channel.exchange_declare(exchange=exchange, exchange_type='topic', durable=True, auto_delete=False)
    channel.queue_declare(queue=queue, durable=True)
    channel.queue_bind(exchange=exchange, queue=queue, routing_key=key)

def rabbitmq_consume(params : RtmqParams, redis, postgres):
    logger.info("Starting rabbit consumer")
    setup_channel(channel=params.channel, exchange=params.exchange, queue=params.queue, key=params.key)
    def callback(ch, method, properties, body):
        message = body.decode()
        logger.info(f"Consumed from RabbitMQ: {message}")
        mac, mess = read_log(message)
        user, error = build_error(connection=postgres, message=mess, mac=mac)
        logger.info(f"User: {user} Error: {error}")
        write_to_redis(params=redis, key=user, value=error)

    params.channel.basic_consume(queue=params.queue, on_message_callback=callback, auto_ack=True)
    params.channel.start_consuming()