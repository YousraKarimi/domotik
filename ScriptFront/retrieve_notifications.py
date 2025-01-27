import requests

BASE_URL = "http://localhost:8080/notifications"

def get_user_notifications(user_id):
    url = f"{BASE_URL}/all/{user_id}"
    try:
        response = requests.get(url)
        if response.status_code == 200:
            notifications = response.json()
            print(f"Notifications pour l'utilisateur {user_id}: {notifications}")
        else:
            print(f"Erreur {response.status_code}: {response.json()}")
    except requests.exceptions.RequestException as e:
        print(f"Erreur lors de la récupération des notifications pour l'utilisateur {user_id}: {e}")

if __name__ == "__main__":
    user_id = "1"  
    get_user_notifications(user_id)

    # user_id = "2"
    # get_user_notifications(user_id)
