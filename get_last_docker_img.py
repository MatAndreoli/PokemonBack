import requests
import json

url = "https://hub.docker.com/v2/repositories/matandreoli/pokemon-api/tags"

payload = ""
response = requests.request("GET", url, data=payload)
json_response = json.loads(response.text)

with open(".env", "x") as f:
    f.write("NEXT_VERSION=" + str(float(json_response["results"][1]["name"]) + 0.1))
    f.close
