import argparse
import requests

# URL de base de l'API
BASE_URL = "http://172.31.250.73:8080/api/configurations/add"


def add_configuration(device_id, configuration_data):
    url = f"{BASE_URL}/{device_id}"
    headers = {"Content-Type": "application/json"}

    try:
        response = requests.post(url, json=configuration_data, headers=headers)

        if response.status_code == 200:
            print(f"Configuration ajoutée avec succès pour l'appareil ID {device_id}.")
        else:
            print(f"Erreur {response.status_code} pour l'appareil ID {device_id}: {response.json()}")
    except requests.exceptions.RequestException as e:
        print(f"Erreur lors de l'envoi de la requête pour l'appareil ID {device_id}: {e}")


def get_configuration(config_number):
    configurations = {
        1: {"configurationName": "Living Room Light Configuration", "mode": "Automatic", "schedule": "18:00 - 06:00",
            "energySaving": True},
        2: {"configurationName": "Living Room Window Configuration", "mode": "Manual", "schedule": "Always",
            "energySaving": False},
        3: {"configurationName": "Garage Door Configuration", "mode": "Manual", "schedule": "Always",
            "energySaving": False},
        4: {"configurationName": "Living Room AC Configuration", "mode": "Automatic", "schedule": "18:00 - 06:00",
            "energySaving": True},
        5: {"configurationName": "Security Camera Configuration", "mode": "Automatic", "schedule": "Always",
            "energySaving": False},
        6: {"configurationName": "Thermostat Configuration", "mode": "Automatic", "schedule": "Always",
            "energySaving": True},
        7: {"configurationName": "Bathroom Heater Configuration", "mode": "Manual", "schedule": "18:00 - 06:00",
            "energySaving": True},
        8: {"configurationName": "Garden Sprinkler Configuration", "mode": "Manual", "schedule": "06:00 - 08:00",
            "energySaving": True},
        9: {"configurationName": "Kitchen Fridge Configuration", "mode": "Automatic", "schedule": "Always",
            "energySaving": False},
        10: {"configurationName": "Living Room TV Configuration", "mode": "Manual", "schedule": "18:00 - 23:00",
             "energySaving": False},
        11: {"configurationName": "Bedroom Fan Configuration", "mode": "Automatic", "schedule": "21:00 - 06:00",
             "energySaving": True},
        12: {"configurationName": "Front Door Lock Configuration", "mode": "Automatic", "schedule": "Always",
             "energySaving": False},
        13: {"configurationName": "Bedroom Blinds Configuration", "mode": "Manual", "schedule": "06:00 - 22:00",
             "energySaving": False},
        14: {"configurationName": "Laundry Washer Configuration", "mode": "Manual", "schedule": "09:00 - 12:00",
             "energySaving": True},
        15: {"configurationName": "Kitchen Oven Configuration", "mode": "Manual", "schedule": "12:00 - 14:00",
             "energySaving": True},
        16: {"configurationName": "Home Alarm Configuration", "mode": "Automatic", "schedule": "Always",
             "energySaving": False},
        17: {"configurationName": "Garage Light Configuration", "mode": "Automatic", "schedule": "18:00 - 06:00",
             "energySaving": True},
        18: {"configurationName": "Vacuum Cleaner Configuration", "mode": "Manual", "schedule": "10:00 - 11:00",
             "energySaving": True},
        19: {"configurationName": "Smart Speaker Configuration", "mode": "Automatic", "schedule": "Always",
             "energySaving": False},
        20: {"configurationName": "Swimming Pool Heater Configuration", "mode": "Manual", "schedule": "06:00 - 10:00",
             "energySaving": True},
    }

    return configurations.get(config_number, None)


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Script pour ajouter des configurations aux appareils.")
    parser.add_argument("config_number", type=int, help="Le numéro de la configuration à envoyer.")
    args = parser.parse_args()

    config_data = get_configuration(args.config_number)
    if config_data:
        add_configuration(args.config_number, config_data)
    else:
        print(f"Configuration numéro {args.config_number} non trouvée.")
