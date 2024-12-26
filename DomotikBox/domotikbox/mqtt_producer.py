import paho.mqtt.client as mqtt
import time

# Configuration MQTT
broker = 'localhost'
port = 1883
topic = 'mqtt/log-topic'
username = 'domotik'
password = 'toto'

# Callback lorsque la connexion au broker est établie
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Connected to MQTT Broker!")
    else:
        print(f"Failed to connect, return code {rc}")

# Callback lorsque le message est publié
def on_publish(client, userdata, mid):
    print(f"Message published with mid {mid}")

# Crée une nouvelle instance de client
client = mqtt.Client()

# Configurer les informations d'authentification
client.username_pw_set(username, password)

# Attacher les callbacks
client.on_connect = on_connect
client.on_publish = on_publish

# Se connecter au broker
client.connect(broker, port, 60)

# Envoie des messages au broker de façon répétée
def publish_messages():
    while True:
        message = "Hello, MQTT!"
        result = client.publish(topic, message)
        status = result[0]
        if status == 0:
            print(f"Sent '{message}' to topic '{topic}'")
        else:
            print(f"Failed to send message to topic {topic}")
        time.sleep(5)  # Envoyer un message toutes les 5 secondes

if __name__ == "__main__":
    # Boucle infinie pour publier des messages
    client.loop_start()
    try:
        publish_messages()
    except KeyboardInterrupt:
        print("Interrupted by user")
        client.loop_stop()
        client.disconnect()
