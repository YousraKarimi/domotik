from applogger import logger
import json

def fetch_one(connection, query, params):
    with connection.cursor() as cursor:
        cursor.execute(query, params)
        result = cursor.fetchone()  # Récupère la première ligne
    return result

def get_configuration_by_id(connection, config_id):
    query = """
        SELECT c.device_id, c.mode, c.schedule, u.id AS user_id
        FROM configuration c
        JOIN device d ON c.device_id = d.id
        JOIN users u ON d.user_id = u.id
        WHERE c.id = %s;
    """
    params = (config_id,)
    result = fetch_one(connection, query, params)
    if result:
        device_id = result[0]
        device_mode = result[1]
        device_schedule = result[2]
        user_id = result[3]
        return user_id, json.dumps({"device_id": device_id, "device_mode": device_mode, "device_schedule": device_schedule})
    return json.dumps({"error": "Configuration not found"})