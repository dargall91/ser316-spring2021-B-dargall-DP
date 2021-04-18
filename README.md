# Code-A-Mon
### *"Gotta Code 'Em All!"*

The Codeamon program is a simulation for Codeamon Trainers to battle and recruit Wild Codeamon before competing against
each other in a single elimination Tournament. The Simulation begins in the Day. During the Day, every Trainer will
battle against a single random Wild Codeamon. If they defeat it, it will join their party. Once all Trainers have
completed their Wild Battles, the simulation will switch to Night. At Night, each Trainer's Codeamon recover all lost
Hit Points to prepare them for the next Day's battles. Once all Trainers Codeamon have recovered this way, it becomes
Day once again. This pattern continues until each Trainer has battled a specified number of Wild Codeamon, which
increase in level by 1 each day. On the Day after the last Wild Codeamon battles have concluded, the Tournament begins.
While the Tournament is ongoing, the Day and Night cycle will continue, except that a single round of the Tournament
will replace Wild Battles during the day. Trainers who lose their battles during a round are eliminated from the
Tournament. The Tournament concludes when only a single Trainer remains, at which point the simulation is over.

There are three different simulations that can be run:

### Default Simulation
This is the simulation that is run if neither of the json files are passed to the .jar as command line arguments. This
simulation consists of 4 Codeamon Trainers who each begin with a single level 20 Codeamon. Trainers will battle against
10 Wild Codeamon, which will begin at level 16, before competing in the Tournament.

### simulation_one.json
This simulation consists of 4 Codeamon Trainers who each begin with 3 level 10 Codeamon. Trainers will battle against
5 Wild Codeamon, which will begin at level 10, before competing in the Tournament.

### simulation_two.json
This simulation consists of 8 Codeamon Trainers who each begin with 6 level 100 Codeamon. Trainers will not battle
against any Wild Codeamon in this simulation, they will instead immediately start competing in the Tournament.

## Design Patterns

### Builder Pattern
The Builder Pattern is used in two separate case. The first is build a Codeamon Trainer. The TrainerBuilder is used to
specify the beginning state of a Codeamon Trainer, that is their name, how much CodeaDollars (¢) they have, and their
initial Codeamon Party composition. The Trainer Builder ended up not being as useful as I wanted it to be in the end,
because the way I set it up didn't really allow for adding an unknown number of Codeamon to a Trainer like I needed
when reading the json files. The second case of the builder, which is used to construct Attacks, was on the other hand
very useful. The Attack Builder allows for nearly fully customizable attacks. An Attack's name and Type must be set with
the builder, but beyond that anything is possible. An Attack can be set to deal damage, heal the user, raise or lower
the user's stats, or raise or lower the user's opponent's stats. With the Builder, an Attack can even be set to do any
combination of these things except affect the stats of both the user and its opponent, or it can even do nothing at all. 

### State Pattern
The State Pattern will be used in the Day and Night Cycle. It is used to determine what events happen during each
of these times. During the Day, battles occur. Trainers will first battle one random Wild Codeamon a day until a
specified number of battles has occurred. After each Day, it will become Night, and each Trainer's Codeamon party will
rest and fully recover all lost Hit Points. After all the Wild Battles have concluded, the Day and Night cycle will
continue, with a single round of the Tournament replacing Wild Battles during the day, until only one Trainer remains.

## Factory Method
The Factory Method is used for the creation of a Codeamon. There are 18 different types of Codeamon, and there is one
species of Codeamon of each type. All Codeamon of the same species have the same base stats and attacks, though the
attacks vary by the Codeamon's initial level. A Codeamon Factory is used to determine what species to create based on a
Type parameter. The Factory is also capable of creating a random type of Codeamon. The Codeamon Factory is also used in
conjunction with a Stats Factory, which gets the base stat values for a given Type, and determines its current stats
based on it's level.