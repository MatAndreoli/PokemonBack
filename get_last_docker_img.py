import requests
import json

url = "https://hub.docker.com/v2/repositories/matandreoli/pokemon-api/tags"

response = requests.request("GET", url)
json_response = json.loads(response.text)

current_version = json_response.get("results")[1].get("name").split('.')

current_version[1] = str(int(current_version[1]) + 1)

next_version = ".".join(current_version)

with open(".env", "w") as f:
    f.write("NEXT_VERSION=" + next_version)
    f.close
