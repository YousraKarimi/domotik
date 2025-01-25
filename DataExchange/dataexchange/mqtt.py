import paho.mqtt.client as mqtt

from applogger import logger
from dataexchange.models import MqttParams, RtmqParams
from dataexchange.rtmq_producer import rabbitmq_producer


def mqtt_log_consumer(mqtt_params : MqttParams, mqtt_topic, rabbit_params : RtmqParams, collection):
    logger.info("Starting mqtt logs consumer!")
    def on_message(client, userdata, msg):
        message = msg.payload.decode()
        logger.debug(f"Consumed from MQTT: {message} .")
        # Sauvegarde dans MongoDB
        collection.insert_one({"message": message})
        logger.debug(f"Saved to MongoDB: {message} .")
        # Production dans RabbitMQ
        rabbitmq_producer(params=rabbit_params, message=message)
    client = mqtt.Client()
    client.username_pw_set(mqtt_params.user, mqtt_params.pwd)
    client.on_message = on_message
    client.connect(mqtt_params.broker, mqtt_params.port)
    client.subscribe(mqtt_topic)
    client.loop_forever()