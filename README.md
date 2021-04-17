# Code-A-Mon
*"Gotta Code 'Em All!"*

The Codeamon program is a simulation for Codeamon Trainers where they will first have a chance to recruit some Wild
Codeamon into their party, then compete in a single elimination tournament. Each Day, for any number of specified days,
Trainers will battle a single Wild Codeamon. If they defeat it, and have an opening in their Codeamon party, the Wild
Codeamon will join their party. If they lose, the Codeamon will not join their party. Once all Trainers have completed
the specified number of Wild Codeamon Battles, they will compete in the tournament. One round of the tournament will
take place each day until only one trainer remains and is declared the winner. After each daytime event, it will become
night. During the night, each Trainer's Codeamon party will rest and fully recover.

## Design Patterns

### Builder Pattern
The Builder Pattern is used in two separate case. The first is build a Codeamon Trainer. The TrainerBuilder is used to
specify the beginning state of a Codeamon Trainer, that is their name, how much CodeaDollars (¢) they have, and their
initial Codeamon Party composition. The second case is for defining the Attacks a Codeamon can use. An Attack's name and
Type must be set with the builder, but beyond that the builder allows for complete customization. An Attack can be set
to deal damage, heal the user, raise or lower the user's stats, or raise or lower the user's opponent's stats. Using the
Builder, an Attack can even be set to do any combination of these things except affect the stats of both the user and
its opponent, or it can even do nothing at all. 

### State Pattern
The State Pattern will be used in the Day and Night Cycle. It will be used to determine what events happen during each
of these times. During the Day, battles can occur. A counter will be used to track the number of Wild Codeamon that have
been battled. Once the counter reaches a specified target, it will switch to running Trainer Battles for the tournament
instead. After each Trainer has completed their Daytime event, the state will switch to Night. During the Night,
Trainer's Codeamon will simply rest and recover Hit Points, then the state will switch back to Day.

## Factory Method
The Factory Method will be used for the creation of a Codeamon. There are 18 different types of Codeamon, and there is
one species of Codeamon of each type. All Codeamon of the same species have the same base stats and attacks, though
the attacks vary by the Codeamon's initial level. A Codeamon Factory will be used to determine what species to create
and what attacks it should know based on a given type and level, and then a Stats Factory will be used to get the base
stats for a Codeamon of a given Type.

## Other Design Patterns
While the above three design patterns will be the ones I focus on, if there is time I may use some others. One idea for
another design pattern is an Observer Pattern used to update a CodeaDex, a tool used by Trainers to track how many
Code-A-Mon they've seen and caught. When a Code-A-Mon is encountered for the first time, the CodeaDex will be notified,
and the Code-A-Mon will be flagged as seen. Capturing a Code-A-Mon will similarly notify the CodeaDex Tracking seen and
caught Code-A-Mon in this manner would allow for the simulation to choose not to catch a Code-A-Mon the trainer has
already caught before. Another potential design pattern is the Strategy Pattern. A Battle between two trainers and a
battle between a trainer and a wild Cod-A-Mon are similar, but there will be some things that can only happen in one
compared to the other, such as the option to catch the wild Cod-A-Mon. These different options and event flows can be
stored in separate Strategies. These two patterns will only be implemented if there is time to implement items,
specifically CodeaBalls which are used to capture Wild Codeamon with.