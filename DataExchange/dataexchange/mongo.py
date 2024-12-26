from pymongo import MongoClient

def setup_mongo(host, port):
    client = MongoClient(host, port)
    db = client['domotik_db']
    return db['messages']