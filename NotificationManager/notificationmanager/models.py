class RtmqParams:
    def __init__(self, channel, exchange, queue, key):
        self.channel = channel
        self.exchange = exchange
        self.queue = queue
        self.key = key

class RedisParams:
    def __init__(self, host, port):
        self.host = host
        self.port = port