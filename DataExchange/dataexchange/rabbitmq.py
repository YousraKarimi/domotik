import pika

from applogger import logger
from dataexchange.models import RtmqParams, MqttParams
from dataexchange.mqtt_producer import mqtt_producer
from dataexchange.message_utils import build_message


def setup_rabbitmq(host, credentials):
    connection = pika.BlockingConnection(pika.ConnectionParameters(host=host, credentials=credentials))
    return connection

def setup_channel(channel, exchange, queue, key):
    channel.exchange_declare(exchange=exchange, exchange_type='topic', durable=True, auto_delete=False)
    channel.queue_declare(queue=queue, durable=True)
    channel.queue_bind(exchange=exchange, queue=queue, routing_key=key)

def rabbit_config_consumer(rabbit_params : RtmqParams, mqtt_params : MqttParams, mqtt_topic, connection):
    logger.info("Starting rabbit config consumer!")
    def callback(ch, method, properties, body):
        message = body.decode()
        logger.debug(f"Consumed from RabbitMQ: {message} .")
        topic, config = build_message(connection, mqtt_topic, message)
        mqtt_producer(params=mqtt_params, message=config, topic=topic)

    rabbit_params.channel.basic_consume(queue=rabbit_params.queue, on_message_callback=callback, auto_ack=True)
    rabbit_params.channel.start_consuming()