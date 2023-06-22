const airports = [
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  }


];

for (let i = 0; i < airports.length; i++) {
  const airport = airports [i];
  pm.sendRequest({
    url: 'http://localhost:8080/airport/createAirport',
    method: 'POST',
    header: 'Content-Type:application/json',
    body: JSON.stringify(airport)
  }, function (err, response) {
    if (err) {
      console.error(err);
      return;
    }

    console.log(response.body);
  });
}