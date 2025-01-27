from time import sleep

import requests

BASE_URL = "http://172.31.250.73:8080/notifications"

def get_user_notifications(user_id):
    url = f"{BASE_URL}/all/{user_id}"
    try:
        response = requests.get(url)
        if response.status_code == 200:
            notifications = response.json()
            for val in notifications:
                print(val)
    except requests.exceptions.RequestException as e:
        print(f"Erreur lors de la récupération des notifications pour l'utilisateur {user_id}: {e}")

if __name__ == "__main__":
    user_id = "1"
    while True:
        get_user_notifications(user_id)
        sleep(5)

    # user_id = "2"
    # get_user_notifications(user_id)
