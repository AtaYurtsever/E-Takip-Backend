# E-Takip-Backend
This program acts as a postgreSQL backend API for the E-Takip system. Available controls are documented below.
- @Post /events\\
Posts a new event that is sent in the body
- @Post /events/{eventNO}\\
Applies the user in the body to the event
- @Post /events/signup
Creates a new user
- @Patch /events/{eventNO}
Updates the given event must 
- @Post /events/{eventNO}/applicants
Gets all the applicants to the event
- @Delete /events/{eventNO}
Deletes event
- @Get/events/{organizer}
Gets specific events of the organizer
- @Get /events/all
Gets all the events
- @Get /events/{eventNO}
Gets the specified event
- @Get /events
Returns all events that start after now
