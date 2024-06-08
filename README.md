After cloning the application you can run it by running the class TechnicalChallengeApplication (benjones\src\main\java\com\ldms\benjones).

Once it is running it exposes a number of APIs, I recommend using Postman to use them.

- POST API
URL: http://localhost:8080/api/v1/schedule
You also need to provide a JSON request body representing the schedule. For example:
{
  "amountBorrowed": 25000.00,
  "yearlyInterest": 0.075,
  "deposit": 5000.00,
  "numberOfRepayments": 12
  } 
OR
  {
  "amountBorrowed": 25000.00,
  "yearlyInterest": 0.075,
  "deposit": 5000.00,
  "balloonPayment": 1000,
  "numberOfRepayments": 12
  }
This will save your schedule to the database, generate an amortisation schedule for it and save that too.

- GET API
URL: http://localhost:8080/api/v1/schedules
Returns a list of the previously created schedules with the following details:
    - Details used to generate it
    - Monthly repayment amount
    - Total interest due
    - Total payments due

- GET API
URL: http://localhost:8080/api/v1/schedules/{id}
Where ID is the ID of the schedule you want information on.
Returns the details from the previous API for this specific schedule, as well as the amortisation schedule generated for it
