const passengers = [
  {
    "id": 1,
    "firstName": "Devin",
    "lastName": "Augot",
    "phoNum": 1230000
  },
  {
    "id": 2,
    "firstName": "Jordan",
    "lastName": "Kelloway",
    "phoNum": 1230001
  },
  {
    "id": 3,
    "firstName": "Cameron",
    "lastName": "D'Amico",
    "phoNum": 1230002
  },
  {
    "id": 4,
    "firstName": "Nathan",
    "lastName": "Green",
    "phoNum": 1230003
  },
  {
    "id": 5,
    "firstName": "Luke",
    "lastName": "Jones",
    "phoNum": 1230004
  },
  {
    "id": 6,
    "firstName": "John",
    "lastName": "Doe",
    "phoNum": 1230005
  },
  {
    "id": 7,
    "firstName": "Jane",
    "lastName": "Doe",
    "phoNum": 1230006
  },
  {
    "id": 8,
    "firstName": "Alex",
    "lastName": "Russo",
    "phoNum": 1230007
  },
  {
    "id": 9,
    "firstName": "Trina",
    "lastName": "Cena",
    "phoNum": 1230008
  },

];

for (let i = 0; i < passengers.length; i++) {
  const passenger = passengers[i];
  pm.sendRequest({
    url: `http://localhost:8080/passenger/createPassenger`,
    method: 'POST',
    header: 'Content-Type: application/json',
    body: JSON.stringify(passenger)
  }, function (err, response) {
    if (err) {
      console.error(err);
      return;
    }
    console.log("Passengers Added!")

    console.log(response.body);
  });
}