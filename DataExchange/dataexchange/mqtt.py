import paho.mqtt.client as mqtt

from applogger import logger
from dataexchange.models import MqttParams, RtmqParams
from dataexchange.rtmq_producer import rabbitmq_producer


def mqtt_consume(params : MqttParams, rtmq : RtmqParams, collection):
    logger.info("Starting mqtt consumer")
    def on_message(client, userdata, msg):
        message = msg.payload.decode()
        logger.info(f"Consumed from MQTT: {message}")
        # Sauvegarde dans MongoDB
        collection.insert_one({"message": message})
        logger.info(f"Saved to MongoDB: {message}")
        # Production dans RabbitMQ
        rabbitmq_producer(params=rtmq, message=message)
    client = mqtt.Client()
    client.username_pw_set(params.user, params.pwd)
    client.on_message = on_message
    client.connect(params.broker, params.port)
    client.subscribe(params.topic)
    client.loop_forever()