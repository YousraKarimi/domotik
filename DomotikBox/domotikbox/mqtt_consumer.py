import paho.mqtt.client as mqtt

broker = '172.31.249.41'
port = 1883
topic = 'mqtt/config-topic/1/config'
username = 'domotik'
password = 'toto'

def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print(f"Connected with result code {rc}")
        client.subscribe(topic)
    else:
        print(f"Failed to connect, return code {rc}")

def on_message(client, userdata, msg):
    print(f"Received message '{msg.payload.decode()}' from topic '{msg.topic}'")

client = mqtt.Client()
client.username_pw_set(username, password)
client.on_connect = on_connect
client.on_message = on_message
client.connect(broker, port, 60)

try:
    client.loop_forever()
except KeyboardInterrupt:
    print("Interrupted by user, disconnecting...")
    client.disconnect()
