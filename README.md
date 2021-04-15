# Code-A-Mon
*"Gotta Code 'Em All!"*

The Codeamon program is a simulation for 8 Codeamon Trainers. Each Codeamon Trainer will begin with a party of 1
Codeamon. Each Trainer will then battle against 5 Wild Codeamon during the day. If the Wild Codeamon is defeated, the
Codeamon will join the Trainer's party. Once all Trainers have completed 5 Wild Codeamon Battles, they will compete
against each other in an 8 man single elimination tournament bracket. Each round of the tournament will take place
during the day. The last trainer standing is the winner of the simulation. After each daytime event, it will become
night. During the night, each Trainer's Codeamon party will rest and fully recover.

## Design Patterns

### Builder Pattern
The Builder Pattern is used in two separate case. The first is build a Codeamon Trainer. The TrainerBuilder is used to
specify the beginning state of a Codeamon Trainer, that is their name, how much CodeaDollars (¢) they have, and their
initial Codeamon Party composition. The second case is for defining the Attacks a Codeamon can use. An Attack's name and
Type must be set with the builder, but beyond that the builder allows for complete customization. An Attack can be set
to deal damage, heal the user, raise or lower the user's stats, raise or lower the user's opponent's stats, or even do
nothing. Using the Builder, an Attack can even be set to do any combination of these things except affect the stats of
both the user and its opponent. 

### State Pattern
The State Pattern will be used in the Day and Night Cycle. It will be used to determine what events happen during each
of these times. During the Day, battles can occur. A counter will be used to track the number of Wild Codeamon that have
been battled. Once the counter reaches a specified target, it will switch to running Trainer Battles for the tournament
instead. After each Trainer has completed their Daytime event, the state will switch to Night. During the Night,
Trainer's Codeamon will simply rest and recover Hit Points, then the state will switch back to Day.

## Factory Method
The Factory Method will also be used for the creation of a Codeamon. There are 18 different types of Codeamon, and there
is one species of Codeamon of each type. All Codeamon of the same species have the same base stats. The Codeamon Factory
will be used to determine the species to create based on a Type input, and then a Stats Factory will be used to get the
base stats for a Codeamon of a specific type.

## Other Design Patterns
While the above three design patterns will be the ones I focus on, if there is time I may use some others. One idea for
another design pattern is an Observer Pattern used to update a CodeDex, a tool used by trainers to track how many
Code-A-Mon they've seen and caught. When a Code-A-Mon is encountered for the first time, the CodeDex will be notified,
and the Code-A-Mon will be flagged as seen. Capturing a Code-A-Mon will similarly notify the CodeDex Tracking seen and
caught Code-A-Mon in this manner would allow for the simulation to choose not to catch a Code-A-Mon the trainer has
already caught before. Another potential design pattern is the Strategy Pattern. A Battle between two trainers and a
battle between a trainer and a wild Cod-A-Mon are similar, but there will be some things that can only happen in one
compared to the other, such as the option to catch the wild Cod-A-Mon. These different options and event flows can be
stored in separate Strategies. These two patterns will only be implemented if there is time to implement items,
specifically CodeaBalls which are used to capture Wild Codeamon with.