const aircrafts = [
  {
    "id": 8,
    "tailNumber": "AF-12344444",
    "type": "Passenger",
    "brand": "Reet2",
    "model": "7573"
  },
  {
    "id": 9,
    "tailNumber": "BF-56788888",
    "type": "Passenger",
    "brand": "Reet3",
    "model": "781"
  },
  {
    "id": 10,
    "tailNumber": "CF-56788888",
    "type": "Passenger",
    "brand": "Reet4",
    "model": "782"
  },
  {
    "id": 11,
    "tailNumber": "BJ-56788888",
    "type": "Passenger",
    "brand": "Reet5",
    "model": "783"
  },
  {
    "id": 12,
    "tailNumber": "BYF-56788888",
    "type": "Passenger",
    "brand": "Reet6",
    "model": "784"
  },
  {
    "id": 13,
    "tailNumber": "BFYN-56788888",
    "type": "Passenger",
    "brand": "Reet7",
    "model": "785"
  },
  {
    "id": 14,
    "tailNumber": "BFJK-56788888",
    "type": "Passenger",
    "brand": "Reet8",
    "model": "786"
  },
  {
    "id": 15,
    "tailNumber": "BFWE-56788888",
    "type": "Passenger",
    "brand": "Reet9",
    "model": "789"
  },
  {
    "id": 16,
    "tailNumber": "BFFG-56788888",
    "type": "Passenger",
    "brand": "Reet31",
    "model": "12787"
  },
  {
    "id": 17,
    "tailNumber": "OPF-56788888",
    "type": "Passenger",
    "brand": "Reet36",
    "model": "45787"
  },
  {
    "id": 18,
    "tailNumber": "LL-56788888",
    "type": "Passenger",
    "brand": "Reet30",
    "model": "89787"
  },
  {
    "id": 19,
    "tailNumber": "PPP-56788888",
    "type": "Passenger",
    "brand": "Reet316",
    "model": "56787"
  },
  {
    "id": 20,
    "tailNumber": "DD-56788888",
    "type": "Passenger",
    "brand": "Reet344",
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