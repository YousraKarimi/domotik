class MqttParams:
    def __init__(self, broker, port, topic, user, pwd):
        self.broker = broker
        self.port = port
        self.topic = topic
        self.user = user
        self.pwd = pwd

class RtmqParams:
    def __init__(self, channel, exchange, queue, key):
        self.channel = channel
        self.exchange = exchange
        self.queue = queue
        self.key = key