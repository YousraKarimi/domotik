import threading

import pika
import redis

from applogger import logger
from notificationmanager.models import RtmqParams, RedisParams
from notificationmanager.rabbitmq import rabbitmq_consume, setup_rabbitmq

# Configuration RabbitMQ
RABBITMQ_HOST = 'rabbitmq'

RABBITMQ_LOG_QUEUE = 'log_queue'
RABBITMQ_LOG_EXCHANGE = 'log_exchange'
RABBITMQ_LOG_KEY = 'log_routing_key'

RABBITMQ_USER = 'ing3'
RABBITMQ_PASS = 'paas'

# Configuration Redis
REDIS_HOST = 'redis'
REDIS_PORT = 6379
REDIS_PASS=redis


credentials = pika.PlainCredentials(RABBITMQ_USER, RABBITMQ_PASS)
connection = setup_rabbitmq(host=RABBITMQ_HOST, credentials=credentials)
cons_channel = connection.channel()


def rtmq_consumer():
    redis_params = RedisParams(host=REDIS_HOST, port=6379)
    rtmq_cons_params = RtmqParams(channel=cons_channel, exchange=RABBITMQ_LOG_EXCHANGE, queue=RABBITMQ_LOG_QUEUE, key=RABBITMQ_LOG_KEY)
    rabbitmq_consume(params= rtmq_cons_params, redis=redis_params)

if __name__ == "__main__":
    threading.Thread(target=rtmq_consumer).start()