from applogger import logger
from dataexchange.models import RtmqParams
from dataexchange.rabbitmq import setup_channel


def rabbitmq_producer(params : RtmqParams, message):
    params.channel.basic_publish(exchange=params.exchange, routing_key=params.key, body=message)
    logger.debug(f"Produced to RabbitMQ: {message}")