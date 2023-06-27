const airports = [
  {
    "id": 1,
    "name": "Pearson International",
    "code": "YYZ",
  },
  {
    "id": 2,
    "name": "Los Angeles International Airport",
    "code": "LAX",
  },
  {
    "id": 3,
    "name": "Hollywood Burbank Airport",
    "code": "BUR",
  },
  {
    "id": 4,
    "name": "Long Beach Airport",
    "code": "LGB",
  },
  {
    "id": 5,
    "name": "Miami International Airpor",
    "code": "MIA",
  },
  {
    "id": 6,
    "name": "Ontario International Airport",
    "code": "ONT",
  },
  {
    "id": 7,
    "name": "Miami-Opa-locka Executive Airport",
    "code": "OPF",
  },
  {
    "id": 8,
    "name": "Miami Executive Airport",
    "code": "TMB",
  },
  {
    "id": 9,
    "name": "John F. Kennedy International Airport",
    "code": "JFK",
  },
  {
    "id": 10,
    "name": "LaGuardia Airport",
    "code": "LGA",
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