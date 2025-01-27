from time import sleep
import requests
import argparse

BASE_URL = "http://172.31.250.73:8080/notifications"


def get_user_notifications(user_id):
    url = f"{BASE_URL}/all/{user_id}"
    try:
        response = requests.get(url)
        if response.status_code == 200:
            notifications = response.json()
            for val in notifications:
                print(val)
        else:
            print(f"Erreur {response.status_code} pour l'utilisateur {user_id}: {response.json()}")
    except requests.exceptions.RequestException as e:
        print(f"Erreur lors de la récupération des notifications pour l'utilisateur {user_id}: {e}")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Script pour récupérer les notifications des utilisateurs.")
    parser.add_argument("user_id", type=str, help="ID de l'utilisateur pour lequel récupérer les notifications.")
    args = parser.parse_args()

    while True:
        get_user_notifications(args.user_id)
        sleep(5)
