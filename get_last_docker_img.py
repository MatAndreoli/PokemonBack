import requests
import json

url = "https://hub.docker.com/v2/repositories/matandreoli/pokemon-api/tags"

payload = ""
response = requests.request("GET", url, data=payload)
json_response = json.loads(response.text)

first = json_response["results"][1]["name"].split('.')[0]
second = int(json_response["results"][1]["name"].split('.')[1]) + 1
next_version = f'{first}.{second}'

with open(".env", "w") as f:
    f.write("NEXT_VERSION=" + next_version)
    f.close
