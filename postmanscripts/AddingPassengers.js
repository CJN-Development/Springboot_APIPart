const passengers = [
  {
    "id": 3,
    "firstName": "John",
    "lastName": "Cena",
    "phoNum": 1231234
  },
  {
    "id": 3,
    "firstName": "John",
    "lastName": "Cena",
    "phoNum": 1231234
  },
  {
    "id": 3,
    "firstName": "John",
    "lastName": "Cena",
    "phoNum": 1231234
  },
  {
    "id": 3,
    "firstName": "John",
    "lastName": "Cena",
    "phoNum": 1231234
  },
  {
    "id": 3,
    "firstName": "John",
    "lastName": "Cena",
    "phoNum": 1231234
  },
  {
    "id": 3,
    "firstName": "John",
    "lastName": "Cena",
    "phoNum": 1231234
  },
  {
    "id": 3,
    "firstName": "John",
    "lastName": "Cena",
    "phoNum": 1231234
  },
  {
    "id": 3,
    "firstName": "John",
    "lastName": "Cena",
    "phoNum": 1231234
  },
  {
    "id": 3,
    "firstName": "John",
    "lastName": "Cena",
    "phoNum": 1231234
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