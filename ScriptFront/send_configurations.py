import requests

# URL de base de l'API
BASE_URL = "http://localhost:8080/api/configurations/add"

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

if __name__ == "__main__":
    
    # Configuration pour le Living Room Light
     configuration_data = {
         "configurationName": "Living Room Light Configuration",
         "mode": "Automatic",
         "schedule": "18:00 - 06:00",
         "energySaving": True
     }
     add_configuration(1, configuration_data)
    #
    # # Configuration pour le Living Room Window
    #  configuration_data = {
    #      "configurationName": "Living Room Window Configuration",
    #      "mode": "Manual",
    #      "schedule": "Always",
    #      "energySaving": False
    #  }
    #  add_configuration(2, configuration_data)
    #
    # # Configuration pour le Garage Door
    #  configuration_data = {
    #      "configurationName": "Garage Door Configuration",
    #      "mode": "Manual",
    #      "schedule": "Always",
    #      "energySaving": False
    #  }
    #  add_configuration(3, configuration_data)
    #
    # # Configuration pour le Living Room AC
    #  configuration_data = {
    #      "configurationName": "Living Room AC Configuration",
    #      "mode": "Automatic",
    #      "schedule": "18:00 - 06:00",
    #      "energySaving": True
    #  }
    #  add_configuration(4, configuration_data)
    #
    # # Configuration 5 : Security Camera
    # configuration_data = {
    #     "configurationName": "Security Camera Configuration",
    #     "mode": "Automatic",
    #     "schedule": "Always",
    #     "energySaving": False
    # }
    # add_configuration(5, configuration_data)
    #
    # # Configuration 6 : Thermostat
    # configuration_data = {
    #     "configurationName": "Thermostat Configuration",
    #     "mode": "Automatic",
    #     "schedule": "Always",
    #     "energySaving": True
    # }
    #  add_configuration(6, configuration_data)
    #
    # # Configuration 7 : Bathroom Heater
    # configuration_data = {
    #     "configurationName": "Bathroom Heater Configuration",
    #     "mode": "Manual",
    #     "schedule": "18:00 - 06:00",
    #     "energySaving": True
    # }
    #  add_configuration(7, configuration_data)
    #
    # # Configuration 8 : Garden Sprinkler
    # configuration_data = {
    #     "configurationName": "Garden Sprinkler Configuration",
    #     "mode": "Manual",
    #     "schedule": "06:00 - 08:00",
    #     "energySaving": True
    # }
    #  add_configuration(8, configuration_data)
    #
    # # Configuration 9 : Kitchen Fridge
    # configuration_data = {
    #     "configurationName": "Kitchen Fridge Configuration",
    #     "mode": "Automatic",
    #     "schedule": "Always",
    #     "energySaving": False
    # }
    #  add_configuration(9, configuration_data)
    #
    # # Configuration 10 : Living Room TV
    # configuration_data = {
    #     "configurationName": "Living Room TV Configuration",
    #     "mode": "Manual",
    #     "schedule": "18:00 - 23:00",
    #     "energySaving": False
    # }
    #  add_configuration(10, configuration_data)
    #
    # # Configuration 11 : Bedroom Fan
    # configuration_data = {
    #     "configurationName": "Bedroom Fan Configuration",
    #     "mode": "Automatic",
    #     "schedule": "21:00 - 06:00",
    #     "energySaving": True
    # }
    #  add_configuration(11, configuration_data)
    #
    # # Configuration 12 : Front Door Lock
    # configuration_data = {
    #     "configurationName": "Front Door Lock Configuration",
    #     "mode": "Automatic",
    #     "schedule": "Always",
    #     "energySaving": False
    # }
    #  add_configuration(12, configuration_data)
    #
    # # Configuration 13 : Bedroom Blinds
    # configuration_data = {
    #     "configurationName": "Bedroom Blinds Configuration",
    #     "mode": "Manual",
    #     "schedule": "06:00 - 22:00",
    #     "energySaving": False
    # }
    #  add_configuration(13, configuration_data)
    #
    # # Configuration 14 : Laundry Washer
    # configuration_data = {
    #     "configurationName": "Laundry Washer Configuration",
    #     "mode": "Manual",
    #     "schedule": "09:00 - 12:00",
    #     "energySaving": True
    # }
    #  add_configuration(14, configuration_data)
    #
    # # Configuration 15 : Kitchen Oven
    # configuration_data = {
    #     "configurationName": "Kitchen Oven Configuration",
    #     "mode": "Manual",
    #     "schedule": "12:00 - 14:00",
    #     "energySaving": True
    # }
    #  add_configuration(15, configuration_data)
    #
    # # Configuration 16 : Home Alarm
    # configuration_data = {
    #     "configurationName": "Home Alarm Configuration",
    #     "mode": "Automatic",
    #     "schedule": "Always",
    #     "energySaving": False
    # }
    #  add_configuration(16, configuration_data)
    #
    # # Configuration 17 : Garage Light
    # configuration_data = {
    #     "configurationName": "Garage Light Configuration",
    #     "mode": "Automatic",
    #     "schedule": "18:00 - 06:00",
    #     "energySaving": True
    # }
    #  add_configuration(17, configuration_data)
    #
    # # Configuration 18 : Vacuum Cleaner
    # configuration_data = {
    #     "configurationName": "Vacuum Cleaner Configuration",
    #     "mode": "Manual",
    #     "schedule": "10:00 - 11:00",
    #     "energySaving": True
    # }
    #  add_configuration(18, configuration_data)
    #
    # # Configuration 19 : Smart Speaker
    # configuration_data = {
    #     "configurationName": "Smart Speaker Configuration",
    #     "mode": "Automatic",
    #     "schedule": "Always",
    #     "energySaving": False
    # }
    #  add_configuration(19, configuration_data)
    #
    # # Configuration 20 : Swimming Pool Heater
    # configuration_data = {
    #     "configurationName": "Swimming Pool Heater Configuration",
    #     "mode": "Manual",
    #     "schedule": "06:00 - 10:00",
    #     "energySaving": True
    # }
    #  add_configuration(20, configuration_data)
