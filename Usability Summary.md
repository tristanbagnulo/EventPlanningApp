# Usabilitiy Summary

This Usability Summary explains and shows how our application applies design princicples from 
Nielsen's (1994) 10 heuristics for software design.

| Number | Heuristic | How we apply this |
------ | --------- | -----------
| 1 | Visibility of system status | We gives users feedback about what is happening in the background of our program in a number of places. These are: <ul><li>When creating a guest the Admin is shown the guest's access code which is automatically generated.</li><li>When creating/deleting a guest, the Admin is able to see the new entry in the database immediately in the table below.</li><li>When creating an event, the new event is immediately displayed on the events table</li></ul> |
| 2 | Match between system and real world | There is only a limited amount of match between our system and the real world. This is because the only *real* entities we need to model are `Guest`s and `Adminstrator`s. Our entities, `Event`, `RSVP`, `Invitation` are all displayed as line entries rather than as a physical document as would be the case of a real-life event invitation and an RSVP letter. We are careful to avoid Skeuomorphism by excessivels matching real forms. |
| 3 | User control and freedom | We permit out users to make mistakes e.g. adding event details incorrectly or adding names incorrectly. We allow them to reverse their mistakes with functions such as "Delete Event", "Edit Event", "Delete Guest etc. |
| 4 | Error prevention | We do not incorporate much error prevention as we do not perform any checks to cofirming whether users want to commit to a given action) to ensure user. The reason for this is that we foresee no functions on our application causing much real harm if they are incorrect. |
| 5 | Aesthetic and minimalist desing | Our widgets are simple and easy to access and our datatables clearly present necessary information upfront and without needing users to navigate through too many windows. |
| 6 | Consistency and stardards | We use common words used in many applications for completing given actions such as New, Edit, Create, Help and Back. |
| 7 | Recognition rather than recall | The functions on our application are quite intuitive and can easily be seen. There are only a few bottons (no more than 4) and a few fields for the user to interact with on each page. The buttons have titles that are intuitive and the fields have descriptions to tell the user what to place within them. |
| 8 | Flexibility and efficiency of use | This is demonstrated in our app where an Administrator can send invitation to multiple guests at once. |
| 9 | Help and documentation | We make our copyright information readily available. It can be accessed from the Dashboards of our users in Help > About. |
| 10 | Help users recognize, diagnose and recover from errors | When a guest or admin tries to login with invalid credentials they receive the error message below. ![](adminLogin_UsernameOrPasswordInvalid.JPG) |
	