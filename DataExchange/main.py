import os
import threading
import argparse
import psycopg2
import pika
from dataexchange.models import MqttParams, RtmqParams
from dataexchange.mongo import setup_mongo
from dataexchange.mqtt import mqtt_log_consumer
from dataexchange.rabbitmq import rabbit_config_consumer, setup_rabbitmq

def parse_arguments():
    parser = argparse.ArgumentParser(description="Configure Mosquitto, RabbitMQ, and MongoDB parameters.")

    # Mosquitto arguments
    parser.add_argument('--mqtt-broker', default=os.getenv('MQTT_BROKER', 'mosquitto'), help='Mosquitto broker')
    parser.add_argument('--mqtt-port', type=int, default=int(os.getenv('MQTT_PORT', 1883)), help='Mosquitto port')
    parser.add_argument('--mqtt-cons-topic', default=os.getenv('MQTT_CONS_TOPIC', 'mqtt/log-topic'), help='MQTT consume topic')
    parser.add_argument('--mqtt-prod-topic', default=os.getenv('MQTT_PROD_TOPIC', 'mqtt/config-topic'), help='MQTT produce topic')
    parser.add_argument('--mqtt-user', default=os.getenv('MQTT_USER', 'exchange'), help='MQTT user')
    parser.add_argument('--mqtt-pass', default=os.getenv('MQTT_PASS', 'toto'), help='MQTT password')

    # RabbitMQ arguments
    parser.add_argument('--rabbitmq-host', default=os.getenv('RABBITMQ_HOST', 'rabbitmq'), help='RabbitMQ host')
    parser.add_argument('--rabbitmq-conf-exchange', default=os.getenv('RABBITMQ_CONF_EXCHANGE', 'config_exchange'), help='RabbitMQ config exchange')
    parser.add_argument('--rabbitmq-conf-queue', default=os.getenv('RABBITMQ_CONF_QUEUE', 'config_queue'), help='RabbitMQ config queue')
    parser.add_argument('--rabbitmq-conf-key', default=os.getenv('RABBITMQ_CONF_KEY', 'config_routing_key'), help='RabbitMQ config routing key')
    parser.add_argument('--rabbitmq-log-exchange', default=os.getenv('RABBITMQ_LOG_EXCHANGE', 'log_exchange'), help='RabbitMQ log exchange')
    parser.add_argument('--rabbitmq-log-queue', default=os.getenv('RABBITMQ_LOG_QUEUE', 'log_queue'), help='RabbitMQ log queue')
    parser.add_argument('--rabbitmq-log-key', default=os.getenv('RABBITMQ_LOG_KEY', 'log_routing_key'), help='RabbitMQ log routing key')
    parser.add_argument('--rabbitmq-user', default=os.getenv('RABBITMQ_USER', 'ing3'), help='RabbitMQ user')
    parser.add_argument('--rabbitmq-pass', default=os.getenv('RABBITMQ_PASS', 'paas'), help='RabbitMQ password')

    # MongoDB arguments
    parser.add_argument('--mongodb-host', default=os.getenv('MONGODB_HOST', 'mongodb'), help='MongoDB host')
    parser.add_argument('--mongodb-port', type=int, default=int(os.getenv('MONGODB_PORT', 27017)), help='MongoDB port')

    # Postgres arguments
    parser.add_argument('--postgres-host', default=os.getenv('POSTGRES_HOST', 'postgres'), help='Postgres host')
    parser.add_argument('--postgres-db', default=os.getenv('POSTGRES_DB', 'house_config'), help='Postgres database')
    parser.add_argument('--postgres-user', default=os.getenv('POSTGRES_USER', 'postgres'), help='Postgres user')
    parser.add_argument('--postgres-pass', default=os.getenv('POSTGRES_PASSWORD', 'admin'), help='Postgres password')
    parser.add_argument('--postgres-port', type=int, default=int(os.getenv('POSTGRES_PORT', 5432)), help='Postgres port')

    return parser.parse_args()
def consume_rabbit_config(rabbitmq_conf_exchange, rabbitmq_conf_queue, rabbitmq_conf_key, mqtt_prod_topic):
    rabbit_config_params = RtmqParams(channel=config_cons_channel, exchange=rabbitmq_conf_exchange, queue=rabbitmq_conf_queue, key=rabbitmq_conf_key)
    rabbit_config_consumer(rabbit_params=rabbit_config_params, mqtt_params=mqtt_params, mqtt_topic=mqtt_prod_topic, connection=postgres_connection)

def consume_mqtt_logs(mqtt_cons_topic, rabbitmq_log_exchange, rabbitmq_log_queue, rabbitmq_log_key):
    rabbit_log_params = RtmqParams(channel=log_prod_channel, exchange=rabbitmq_log_exchange, queue=rabbitmq_log_queue, key=rabbitmq_log_key)
    mqtt_log_consumer(mqtt_params=mqtt_params, rabbit_params=rabbit_log_params, mqtt_topic=mqtt_cons_topic, collection=mongo_collection)

if __name__ == "__main__":
    args = parse_arguments()

    rabbit_credentials = pika.PlainCredentials(args.rabbitmq_user, args.rabbitmq_pass)
    rabbit_cons_connection = setup_rabbitmq(host=args.rabbitmq_host, credentials=rabbit_credentials)
    config_cons_channel = rabbit_cons_connection.channel()
    rabbit_prod_connection = setup_rabbitmq(host=args.rabbitmq_host, credentials=rabbit_credentials)
    log_prod_channel = rabbit_prod_connection.channel()

    mqtt_params = MqttParams(broker=args.mqtt_broker, port=args.mqtt_port, user=args.mqtt_user, pwd=args.mqtt_pass)

    postgres_connection = psycopg2.connect(dbname=args.postgres_db, user=args.postgres_user,
                                           password=args.postgres_pass, host=args.postgres_host,
                                           port=args.postgres_port)
    mongo_collection = setup_mongo(host=args.mongodb_host, port=args.mongodb_port)

    threading.Thread(target=consume_rabbit_config,
                     args=(args.rabbitmq_conf_exchange, args.rabbitmq_conf_queue, args.rabbitmq_conf_key, args.mqtt_prod_topic)
                     ).start()

    threading.Thread(target=consume_mqtt_logs,
                     args=(args.mqtt_cons_topic, args.rabbitmq_log_exchange, args.rabbitmq_log_queue, args.rabbitmq_log_key)
                     ).start()
