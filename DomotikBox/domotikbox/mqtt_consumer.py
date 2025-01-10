import paho.mqtt.client as mqtt

# Configuration MQTT
broker = 'localhost'
port = 1883
topic = 'mqtt/config-topic/1/config'
username = 'domotik'
password = 'toto'

# Callback lorsque la connexion au broker est établie
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print(f"Connected with result code {rc}")
        client.subscribe(topic)
    else:
        print(f"Failed to connect, return code {rc}")

# Callback lorsque un message est reçu
def on_message(client, userdata, msg):
    print(f"Received message '{msg.payload.decode()}' from topic '{msg.topic}'")

# Crée une nouvelle instance de client
client = mqtt.Client()

# Configurer les informations d'authentification
client.username_pw_set(username, password)

# Attacher les callbacks
client.on_connect = on_connect
client.on_message = on_message

# Se connecter au broker
client.connect(broker, port, 60)

# Boucle pour écouter les messages
try:
    client.loop_forever()
except KeyboardInterrupt:
    print("Interrupted by user, disconnecting...")
    client.disconnect()
