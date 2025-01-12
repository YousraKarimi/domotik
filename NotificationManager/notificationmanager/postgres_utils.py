from applogger import logger
import json


def fetch_one(connection, query, params):
    with connection.cursor() as cursor:
        cursor.execute(query, params)
        result = cursor.fetchone()  # Récupère la première ligne
    return result

def get_device_by_mac(connection, mac_address):
    query = """
        SELECT d.device_name AS device_name, u.id AS user_id
        FROM device d
        JOIN "users" u ON d.user_id = u.id
        WHERE d.id = %s;
    """
    params = (mac_address,)
    result = fetch_one(connection, query, params)
    if result:
        return result
    return json.dumps({"error": "Device not found"})