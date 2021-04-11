# Code-A-Mon
*Gotta Code 'Em All!*

I love Pokemon. I've loved Pokemon ever since it first released on the Game Boy. after I took my first introductory
programming course in high school, the first program I ever made was a text-based Pokemon game in C++ . It was a very
crude and simple version of what I will doing here. Years ago, I had an idea for a Pokemon ROM Hack (a mod for an
existing Pokemon game) and worked on it on and off for a few years. I have so many ideas for this project, the only
limitation is time.

The goal of this program is to create a simulation of a Trainer who begins with one ~~Pokemon~~ Code-A-Mon, and will
run until a TBD event has triggered. Possibilities for such an event are battling a certain rival trainer, catching a
specified number of Code-A-Mon, or after a certain number of days have passed. The simulation will mimic many of the
actual mechanics from Pokemon, because many of the mechanics used in the Mascotmon program and suggested in the
assignment document are not how they actually work in the mainline games, and that annoyed me to no end.

# Builder Pattern
The first design pattern I intend to use is the Builder Pattern. The Builder Pattern will be used to build Trainers by
setting attributes such as their name and the Code-A-Mon on their team. 

# State Pattern
The second design pattern I've chosen is the State Pattern. A State for Night and a State for Day can be used to
determine what the list of possible events that can trigger are, and control the transition between the two states
after those events have concluded.

# Strategy Pattern
The third design pattern I've chosen is the Strategy Pattern. A Battle between two trainers and a battle between a
trainer and a wild Cod-A-Mon are similar, but there will be some things that can only happen in one compared to the
other, such as the option to catch the wild Cod-A-Mon. These different options and event flows can be stored in
separate Strategies.

# Other Design Patterns
While the above three design patterns will be the ones I focus on, if there is time I may use some others. One idea for
another design pattern is an Observer Pattern used to update a CodeDex, a tool used by trainers to track how many
Code-A-Mon they've seen and caught. When a Code-A-Mon is encountered for the first time, the CodeDex will be notified,
and the Code-A-Mon will be flagged as seen. Capturing a Code-A-Mon will similarly notify the CodeDex Tracking seen and
caught Code-A-Mon in this manner would allow for the simulation to choose not to catch a Code-A-Mon the trainer has
already caught before. Another design pattern I'm considering is a Factory Method for creating Code-A-Mon. There will
be separate classes for Code-A-Mon of each type to store that specific Code-A-Mon's data, and a Factory could be used
to make the required type of Code-A-Mon.