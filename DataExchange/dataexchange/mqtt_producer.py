import paho.mqtt.client as mqtt

from applogger import logger
from dataexchange.models import MqttParams


def mqtt_producer(params : MqttParams, message : str, topic : str):
    client = mqtt.Client()
    client.username_pw_set(params.user, params.pwd)
    client.connect(params.broker, params.port)
    result = client.publish(topic, message)
    status = result[0]
    if status == 0:
        logger.info(f"Produced to MQTT: {message} to topic {topic}")
    else:
        logger.error(f"Failed to send message to topic {topic}")