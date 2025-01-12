import threading
import argparse
import psycopg2
import pika
from dataexchange.models import MqttParams, RtmqParams
from dataexchange.mongo import setup_mongo
from dataexchange.mqtt import mqtt_consume
from dataexchange.rabbitmq import rabbitmq_consume, setup_rabbitmq

def parse_arguments():
    parser = argparse.ArgumentParser(description="Configure Mosquitto, RabbitMQ, and MongoDB parameters.")

    # Mosquitto arguments
    parser.add_argument('--mqtt-broker', default='mosquitto', help='Mosquitto broker')
    parser.add_argument('--mqtt-port', type=int, default=1883, help='Mosquitto port')
    parser.add_argument('--mqtt-cons-topic', default='mqtt/log-topic', help='MQTT consume topic')
    parser.add_argument('--mqtt-prod-topic', default='mqtt/config-topic', help='MQTT produce topic')
    parser.add_argument('--mqtt-user', default='exchange', help='MQTT user')
    parser.add_argument('--mqtt-pass', default='toto', help='MQTT password')

    # RabbitMQ arguments
    parser.add_argument('--rabbitmq-host', default='rabbitmq', help='RabbitMQ host')
    parser.add_argument('--rabbitmq-conf-exchange', default='config_exchange', help='RabbitMQ config exchange')
    parser.add_argument('--rabbitmq-conf-queue', default='config_queue', help='RabbitMQ config queue')
    parser.add_argument('--rabbitmq-conf-key', default='config_routing_key', help='RabbitMQ config routing key')
    parser.add_argument('--rabbitmq-log-exchange', default='log_exchange', help='RabbitMQ log exchange')
    parser.add_argument('--rabbitmq-log-queue', default='log_queue', help='RabbitMQ log queue')
    parser.add_argument('--rabbitmq-log-key', default='log_routing_key', help='RabbitMQ log routing key')
    parser.add_argument('--rabbitmq-user', default='ing3', help='RabbitMQ user')
    parser.add_argument('--rabbitmq-pass', default='paas', help='RabbitMQ password')

    # MongoDB arguments
    parser.add_argument('--mongodb-host', default='mongodb', help='MongoDB host')
    parser.add_argument('--mongodb-port', type=int, default=27017, help='MongoDB port')

    # Postgres arguments (if needed in future)
    parser.add_argument('--postgres-host', default='postgres', help='Postgres host')
    parser.add_argument('--postgres-db', default='house_config', help='Postgres database')
    parser.add_argument('--postgres-user', default='postgres', help='Postgres user')
    parser.add_argument('--postgres-pass', default='admin', help='Postgres password')
    parser.add_argument('--postgres-port', type=int, default=5432, help='Postgres port')

    return parser.parse_args()

def rtmq_consumer(rabbitmq_host, rabbitmq_conf_exchange, rabbitmq_conf_queue, rabbitmq_conf_key, rabbitmq_user, rabbitmq_pass, mqtt_broker, mqtt_port, mqtt_prod_topic, mqtt_user, mqtt_pass):
    credentials = pika.PlainCredentials(rabbitmq_user, rabbitmq_pass)
    cons_connection = setup_rabbitmq(host=rabbitmq_host, credentials=credentials)
    cons_channel = cons_connection.channel()
    postgres_connection = psycopg2.connect(dbname=args.postgres_db, user=args.postgres_user, password=args.postgres_pass, host=args.postgres_host, port=args.postgres_port)
    
    mqtt_prod_params = MqttParams(broker=mqtt_broker, port=mqtt_port, topic=mqtt_prod_topic, user=mqtt_user, pwd=mqtt_pass)
    rtmq_cons_params = RtmqParams(channel=cons_channel, exchange=rabbitmq_conf_exchange, queue=rabbitmq_conf_queue, key=rabbitmq_conf_key)
    rabbitmq_consume(params=rtmq_cons_params, mqtt=mqtt_prod_params, connection=postgres_connection)

def mqtt_consumer(mongodb_host, mongodb_port, mqtt_broker, mqtt_port, mqtt_cons_topic, mqtt_user, mqtt_pass, rabbitmq_host, rabbitmq_log_exchange, rabbitmq_log_queue, rabbitmq_log_key, rabbitmq_user, rabbitmq_pass):
    collection = setup_mongo(host=mongodb_host, port=mongodb_port)
    credentials = pika.PlainCredentials(rabbitmq_user, rabbitmq_pass)
    prod_connection = setup_rabbitmq(host=rabbitmq_host, credentials=credentials)
    prod_channel = prod_connection.channel()

    mqtt_cons_params = MqttParams(broker=mqtt_broker, port=mqtt_port, topic=mqtt_cons_topic, user=mqtt_user, pwd=mqtt_pass)
    rtmq_prod_params = RtmqParams(channel=prod_channel, exchange=rabbitmq_log_exchange, queue=rabbitmq_log_queue, key=rabbitmq_log_key)
    mqtt_consume(params=mqtt_cons_params, rtmq=rtmq_prod_params, collection=collection)

if __name__ == "__main__":
    args = parse_arguments()

    threading.Thread(target=rtmq_consumer, args=(
        args.rabbitmq_host, args.rabbitmq_conf_exchange, args.rabbitmq_conf_queue,
        args.rabbitmq_conf_key, args.rabbitmq_user, args.rabbitmq_pass,
        args.mqtt_broker, args.mqtt_port, args.mqtt_prod_topic,
        args.mqtt_user, args.mqtt_pass
    )).start()

    threading.Thread(target=mqtt_consumer, args=(
        args.mongodb_host, args.mongodb_port,
        args.mqtt_broker, args.mqtt_port, args.mqtt_cons_topic,
        args.mqtt_user, args.mqtt_pass,
        args.rabbitmq_host, args.rabbitmq_log_exchange, args.rabbitmq_log_queue,
        args.rabbitmq_log_key, args.rabbitmq_user, args.rabbitmq_pass
    )).start()
