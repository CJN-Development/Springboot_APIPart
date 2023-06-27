const aircrafts = [
  {
    "id": 1,
    "tailNumber": "AF-12344444",
    "type": "Passenger",
    "brand": "Boeing",
    "model": "7573"
  },
  {
    "id": 2,
    "tailNumber": "BF-56788888",
    "type": "Passenger",
    "brand": "Airbus",
    "model": "7574"
  },
  {
    "id": 3,
    "tailNumber": "CF-56788888",
    "type": "Passenger",
    "brand": "Boeing",
    "model": "7575"
  },
  {
    "id": 4,
    "tailNumber": "BJ-56788888",
    "type": "Passenger",
    "brand": "Airbus",
    "model": "7576"
  },
  {
    "id": 5,
    "tailNumber": "BYF-56788888",
    "type": "Passenger",
    "brand": "Boeing",
    "model": "7577"
  },
  {
    "id": 6,
    "tailNumber": "BFYN-56788888",
    "type": "Passenger",
    "brand": "Boeing",
    "model": "7578"
  },
  {
    "id": 7,
    "tailNumber": "BFJK-56788888",
    "type": "Passenger",
    "brand": "Boeing",
    "model": "7579"
  },
  {
    "id": 8,
    "tailNumber": "BFWE-56788888",
    "type": "Passenger",
    "brand": "Boeing",
    "model": "789"
  },
  {
    "id": 9,
    "tailNumber": "BFFG-56788888",
    "type": "Passenger",
    "brand": "Airbus",
    "model": "12787"
  },
  {
    "id": 10,
    "tailNumber": "OPF-56788888",
    "type": "Passenger",
    "brand": "Airbus",
    "model": "45787"
  },
  {
    "id": 11,
    "tailNumber": "LL-56788888",
    "type": "Passenger",
    "brand": "Airbus",
    "model": "89787"
  },
  {
    "id": 12,
    "tailNumber": "PPP-56788888",
    "type": "Passenger",
    "brand": "Airbus",
    "model": "56787"
  },
  {
    "id": 13,
    "tailNumber": "DD-56788888",
    "type": "Passenger",
    "brand": "Airbus",
    "model": "99787"
  },

];

for (let i = 0; i < aircrafts.length; i++) {
  const aircraft = aircrafts[i];
  pm.sendRequest({
    url: 'http://localhost:8080/aircraft/createAircraft',
    method: 'POST',
    header: 'Content-Type:application/json',
    body: JSON.stringify(aircraft)
  }, function (err, response) {
    if (err) {
      console.error(err);
      return;
    }

    console.log(response.body);
  });
}