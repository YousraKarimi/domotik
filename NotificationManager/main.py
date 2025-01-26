import argparse
import os
import threading
import pika
import psycopg2
import redis
from notificationmanager.models import RtmqParams, RedisParams
from notificationmanager.rabbitmq import rabbit_log_consumer, setup_rabbitmq


def parse_arguments():
    parser = argparse.ArgumentParser(description="Configure RabbitMQ, Redis, and Postgres parameters.")

    # RabbitMQ arguments
    parser.add_argument('--rabbitmq-host', default=os.getenv('RABBITMQ_HOST', 'rabbitmq'), help='RabbitMQ host')
    parser.add_argument('--rabbitmq-log-queue', default=os.getenv('RABBITMQ_LOG_QUEUE', 'log_queue'), help='RabbitMQ log queue')
    parser.add_argument('--rabbitmq-log-exchange', default=os.getenv('RABBITMQ_LOG_EXCHANGE', 'log_exchange'), help='RabbitMQ log exchange')
    parser.add_argument('--rabbitmq-log-key', default=os.getenv('RABBITMQ_LOG_KEY', 'log_routing_key'), help='RabbitMQ log routing key')
    parser.add_argument('--rabbitmq-user', default=os.getenv('RABBITMQ_USER', 'ing3'), help='RabbitMQ user')
    parser.add_argument('--rabbitmq-pass', default=os.getenv('RABBITMQ_PASS', 'paas'), help='RabbitMQ password')

    # Redis arguments
    parser.add_argument('--redis-host', default=os.getenv('REDIS_HOST', 'redis'), help='Redis host')
    parser.add_argument('--redis-port', type=int, default=int(os.getenv('REDIS_PORT', 6379)), help='Redis port')
    parser.add_argument('--redis-pass', default=os.getenv('REDIS_PASS', ''), help='Redis password')

    # Postgres arguments (if needed in future)
    parser.add_argument('--postgres-host', default=os.getenv('POSTGRES_HOST', 'postgres'), help='Postgres host')
    parser.add_argument('--postgres-db', default=os.getenv('POSTGRES_DB', 'house_config'), help='Postgres database')
    parser.add_argument('--postgres-user', default=os.getenv('POSTGRES_USER', 'postgres'), help='Postgres user')
    parser.add_argument('--postgres-pass', default=os.getenv('POSTGRES_PASSWORD', 'admin'), help='Postgres password')
    parser.add_argument('--postgres-port', type=int, default=int(os.getenv('POSTGRES_PORT', 5432)), help='Postgres port')

    return parser.parse_args()


def consume_rabbit_log(rabbitmq_host, rabbitmq_log_exchange, rabbitmq_log_queue, rabbitmq_log_key, rabbitmq_user,
                       rabbitmq_pass, redis_host, redis_port):
    credentials = pika.PlainCredentials(rabbitmq_user, rabbitmq_pass)
    connection = setup_rabbitmq(host=rabbitmq_host, credentials=credentials)
    cons_channel = connection.channel()
    postgres_connection = psycopg2.connect(dbname=args.postgres_db, user=args.postgres_user,
                                           password=args.postgres_pass, host=args.postgres_host,
                                           port=args.postgres_port)

    redis_params = RedisParams(host=redis_host, port=redis_port)
    log_cons_params = RtmqParams(channel=cons_channel, exchange=rabbitmq_log_exchange, queue=rabbitmq_log_queue,
                                  key=rabbitmq_log_key)
    rabbit_log_consumer(params=log_cons_params, redis=redis_params, postgres=postgres_connection)


if __name__ == "__main__":
    args = parse_arguments()

    threading.Thread(target=consume_rabbit_log, args=(
        args.rabbitmq_host, args.rabbitmq_log_exchange, args.rabbitmq_log_queue,
        args.rabbitmq_log_key, args.rabbitmq_user, args.rabbitmq_pass,
        args.redis_host, args.redis_port
    )).start()
