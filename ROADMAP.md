In future I would like to:
- Persist data through refreshes, perhaps by moving to MySQL or Oracle
- 
- Add other CRUD APIs for the schedules
- Add input validation
- Add more thorough testing including non-happy path scenarios
- Add rounding to 2dp when displaying the numerical values to the user
- Change ScheduleInfo to an Entity, linking to Schedule and removing the fields about how the schedule was generated. Also, check in the database if a ScheduleInfo exists before creating one.