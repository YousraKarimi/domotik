import json

from dataexchange.postgres_utils import get_configuration_by_id

def read_message(json_string):
    data = json.loads(json_string)
    conf = data["id"]
    return conf

def build_message(connection, topic, config_id):
    user, config = get_configuration_by_id(connection, config_id)
    stopic = f"{topic}/{user}/config"
    return stopic, config