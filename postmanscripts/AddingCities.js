const cities = [
  {
    "id": 1,
    "state": "NL",
    "population": 150000,
    "name": "St. John's"

  },
  {
    "id": 2,
    "state": "NL",
    "population": 11000,
    "name": "Gander"
  },
  {
    "id": 3,
    "state": "CA",
    "population": 3849000,
    "name": "Los Angeles"
  },
  {
    "id": 4,
    "state": "FL",
    "population": 439000,
    "name": "Miami"
  },
  {
    "id": 5,
    "state": "NY",
    "population": 8468000,
    "name": "New York "
  },
  {
    "id": 6,
    "state": "NL",
    "population": 5316,
    "name": "Marystown"
  },
  {
    "id": 7,
    "state": "ON",
    "population": 1500000,
    "name": "Toronto"
  },
  {
    "id": 8,
    "state": "ON",
    "population": 1000000,
    "name": "Ottawa"
  },
  {
    "id": 9,
    "state": "NS",
    "population": 200000,
    "name": "Halifax"
  },
  {
    "id": 10,
    "state": "AB",
    "population": 1500000,
    "name": "Calgary"
  },
  {
    "id": 11,
    "state": "AB",
    "population": 1400000,
    "name": "Edmonton"
  },
  {
    "id": 12,
    "state": "BC",
    "population": 2500000,
    "name": "Vancouver"
  },
  {
    "id": 13,
    "state": "YT",
    "population": 30000,
    "name": "Whitehorse"
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