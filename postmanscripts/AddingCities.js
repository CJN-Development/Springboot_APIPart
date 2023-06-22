const cities = [
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"

  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"
  },

];

for (let i = 0; i < cities.length; i++) {
  const city = cities[i];
  pm.sendRequest({
    url: 'http://localhost:8080/cities/createCity',
    method: 'POST',
    header: 'Content-Type:application/json',
    body: JSON.stringify(city)
  }, function (err, response) {
    if (err) {
      console.error(err);
      return;
    }

    console.log(response.body);
  });
}