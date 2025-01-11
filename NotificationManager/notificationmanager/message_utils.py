import json

from notificationmanager.postgres_utils import get_device_by_mac

def read_log(json_string):
    data = json.loads(json_string)
    mac = data["mac"]
    message = data["message"]
    return mac, message

def build_error(connection, message, mac):
    device = get_device_by_mac(connection, mac)
    erreur = device[0] + " : " + message
    return device[1], erreur