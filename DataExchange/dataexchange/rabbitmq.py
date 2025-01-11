import pika

from applogger import logger
from dataexchange.models import RtmqParams, MqttParams
from dataexchange.mqtt_producer import mqtt_producer


def setup_rabbitmq(host, credentials):
    connection = pika.BlockingConnection(pika.ConnectionParameters(host=host, credentials=credentials))
    return connection

def setup_channel(channel, exchange, queue, key):
    channel.exchange_declare(exchange=exchange, exchange_type='direct')
    channel.queue_declare(queue=queue)
    channel.queue_bind(exchange=exchange, queue=queue, routing_key=key)

def rabbitmq_consume(params : RtmqParams, mqtt : MqttParams, connection):
    logger.info("Starting rabbit consumer")
    setup_channel(channel=params.channel, exchange=params.exchange, queue=params.queue, key=params.key)
    def callback(ch, method, properties, body):
        message = body.decode()
        logger.info(f"Consumed from RabbitMQ: {message}")
        conf = read_message(message)
        stopic, config = build_message(connection, mqtt.topic, conf)
        logger.info(f"Topic: {stopic}  Config: {config}")
        mqtt.topic = stopic
        mqtt_producer(params=mqtt, message=config)

    params.channel.basic_consume(queue=params.queue, on_message_callback=callback, auto_ack=True)
    params.channel.start_consuming()