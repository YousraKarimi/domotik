from time import sleep

import paho.mqtt.client as mqtt
from datetime import datetime

broker = '172.31.249.41'
port = 1883
topic = 'mqtt/log-topic'
username = 'domotik'
password = 'toto'

def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Connected to MQTT Broker!")
    else:
        print(f"Failed to connect, return code {rc}")

def on_publish(client, userdata, mid):
    print(f"Message published with mid {mid}")

client = mqtt.Client()

# Configurer les informations d'authentification
client.username_pw_set(username, password)

# Attacher les callbacks
client.on_connect = on_connect
client.on_publish = on_publish

# Se connecter au broker
client.connect(broker, port, 60)

maintenant = datetime.now()
date_formated = maintenant.strftime("%d-%m-%Y %H:%M:%S")

# Envoie des messages au broker de façon répétée
def publish_messages():
    while True:
        message = f'{{"mac" : 4, "message" : "{date_formated} It is very hot here!!!"}}'
        result = client.publish(topic, message)
        status = result[0]
        if status == 0:
            print(f"Sent '{message}' to topic '{topic}'")
        else:
            print(f"Failed to send message to topic {topic}")
        sleep(20)

if __name__ == "__main__":
    client.loop_start()
    try:
        publish_messages()
    except KeyboardInterrupt:
        print("Interrupted by user")
        client.loop_stop()
        client.disconnect()
