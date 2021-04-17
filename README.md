The only special instruction needed to run my program is creating a logFiles directory, you will see I added this during one of my commits. 
It does contain json log files, which were uploaded to demonstrate this. This directory is required as I added it for ease of storing 
the log files. It is not a hard coded pathway, but rather coded through the project directory.

Initial I had used the standard JSON library since I had used it during SWEN 225 and could use my own code as an example, with greater understanding.
However, whilst completing this assignment I soon discovered its shortcomings. It is a very basic library with little functionality and online
support when compared to GSON. So I switched halfway through. I did this initial as most online forums such as stack overflow referenced/used GSON.
There is very little support/examples online for the JSON library, and it lacked some functionality of other libraries such as GSONs' setPrettyPrinting().
GSON has increased functionality, is open-sourced, and is only dependent on its own library. The switch was relatively easy as there are numerous guides/examples online.