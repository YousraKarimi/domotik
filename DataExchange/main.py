import threading

import pika

from dataexchange.models import MqttParams, RtmqParams
from dataexchange.mongo import setup_mongo
from dataexchange.mqtt import mqtt_consume
from dataexchange.rabbitmq import rabbitmq_consume, setup_rabbitmq

# Configuration Mosquitto
MQTT_BROKER = 'mosquitto'
MQTT_PORT = 1883
MQTT_CONS_TOPIC = 'mqtt/log-topic'
MQTT_PROD_TOPIC = "mqtt/config-topic"
MQTT_USER = 'exchange'
MQTT_PASS = 'toto'

# Configuration RabbitMQ
RABBITMQ_HOST = 'rabbitmq'
RABBITMQ_CONF_EXCHANGE = 'config_exchange'
RABBITMQ_CONF_QUEUE = 'config_queue'
RABBITMQ_CONF_KEY = 'config_routing_key'

RABBITMQ_LOG_QUEUE = 'log_queue'
RABBITMQ_LOG_EXCHANGE = 'log_exchange'
RABBITMQ_LOG_KEY = 'log_routing_key'

RABBITMQ_USER = 'ing3'
RABBITMQ_PASS = 'paas'

# Configuration Mongo
MONGODB_HOST = 'mongodb'
MONGODB_PORT = 27017


credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
connection = setup_rabbitmq(host=RABBITMQ_HOST, credentials=credentials)
cons_channel = connection.channel()
prod_channel = connection.channel()



def rtmq_consumer():
    mqtt_prod_params = MqttParams(broker=MQTT_BROKER, port=MQTT_PORT, topic=MQTT_PROD_TOPIC, user=MQTT_USER, pwd=MQTT_PASS)
    rtmq_cons_params = RtmqParams(channel=cons_channel, exchange=RABBITMQ_CONF_EXCHANGE, queue=RABBITMQ_CONF_QUEUE, key=RABBITMQ_CONF_KEY)
    rabbitmq_consume(params= rtmq_cons_params, mqtt=mqtt_prod_params)

def mqtt_consumer():
    collection = setup_mongo(host=MONGODB_HOST, port=MONGODB_PORT)
    mqtt_cons_params = MqttParams(broker=MQTT_BROKER, port=MQTT_PORT, topic=MQTT_CONS_TOPIC, user=MQTT_USER, pwd=MQTT_PASS)
    rtmq_prod_params = RtmqParams(channel=prod_channel, exchange=RABBITMQ_LOG_EXCHANGE, queue=RABBITMQ_LOG_QUEUE, key=RABBITMQ_LOG_KEY)
    mqtt_consume(params=mqtt_cons_params, rtmq=rtmq_prod_params, collection=collection)

if __name__ == "__main__":
    threading.Thread(target=rtmq_consumer).start()
    threading.Thread(target=mqtt_consumer).start()