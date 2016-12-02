# Tiger Zone

## Team M – CEN3031

### Members:


* Pei Hui Zheng - SAOxAce
* Hannah Kuehn - hkuehn
* Saulo De La Rosa - sadelarosa
* Alejandro Chavez - alechavez93
* Zac Rolston - zrolston
* Artem Iryshkov - wheelsandbytes

---

### Compilation/Execution Instuctions (Ecplise IDE)

* Clone the repository
    *  `git clone https://github.com/wheelsandbytes/tigerzone.git`
* Create a new project in Eclipse
    * File -> New -> Java project
    * Project Name: `tigerzone`
    * Location: `/.../tigerzone` _(cloned tigerzone repo)_
    * Select _Next_
    * In the list of source folders, right-click `images` and select `User as Source Folder`
    * Select _Libraries_ tab
    * `Add Library` -> `JUnit`
    * Select _Next_
    * Select `JUnit 4`
    * Select _Finish_
    * Select _Finish_ again
* Menu bar -> _Run_ -> _Run Configurations_
    * right-click `Java Application` -> `New`
    * _Main class:_ -> _Search_ -> `MainAvP - MainSimulations` -> _Ok_
    * _Run_



---
### [Architectural Design](https://www.gliffy.com/go/share/s8yo6dxqp3tha72iwy0p)

### [UML Diagram](https://www.gliffy.com/go/share/satac75pd0xs1h24vyih)

---
### Acceptance Testing

The majority of our acceptance testing is done through a graphical representation of our game board and the Tigers/Crocodiles placed upon it.  This board has been tested and accurately represents the current state of our game.  Tiles are represernted using the provided images from the Game Rules, tigers are represented by yellow squares, and crocodiles by green squares.  The tigers are placed accurately according to the 1-9 "Tiger Zone" representation, understand that this representation can sometimes lead to a misleading image (an example being a tiger placed at the 7 zone of a 0 rotation JLJL- tile can appear to be placed on the Lake but is  actually placed on the correct zone to denote a tiger on the bottom Jungle) but this is just how the "Tiger Zone" system is implemented. Tigers are removed from a completeable feature when it is completed.  Note that if a tiger is placed onto a feature as it was being completed that turn the tiger will never appear but the game will score for the player who placed that tiger.

This GUI is accessible for acceptance testing throught the MainTvT and MainAvP classes, simply run them.

Main TvT is a "Tester vs Tester" application where human players follow console instruction to place tiles and tigers.

Main AvP is an AI test Arena where two instances of our AI play each other, for testing and readability purposes after each AI makes a move the human tester must enter something into the console Java Scanner to move the game along, this allows one to examine the game move by move to ensure validity.

In both of these classes after every move a string representation of our game state is displayed on the console.  This includes:
* The score of both players next to their username.
* A representation of our region composites as the game tracks them
* Current scores and information for all features
* What Zone the AI placed its tiger '(For the purpose of ensuring minimum Zone placement)'

The representation of the region composite is somewhat complex but breaks down into this:
* Each composite has an ID, the number next to it
* Each composite is a conglomerate of individual region objects contained within tiles
* These region objects are added as they merge when tiles are placed.
* Each Region Composite, if it has any tigers in it, will display the name of the player'(s)' who placed the tiger and the number of tigers that player has placed on that composite '(accounting for a merger)'

An example of this region map representation would be as follows for a placement of a TLTJD tile at 0, 0 and a TTTT- tile at 0, -1 with a tiger placed at Zone 2 of TTTT- by the player named AI.

'{0=Lakes:
C0 {}}
{0=Jungles:
F0 F0 {}, 1=Jungles:
F1 F1 {}, 2=Jungles:
F2 {}, 3=Jungles:
F3 {}}
{0=Trails:
R0 R0 {AI=1}, 1=Trails:
R1 {}, 2=Trails:
R2 {}, 3=Trails:
R3 {}}'

The scoring representation is relatively straightforwrd.  A list of Lakes, Jungles, and Trails '(Specifically their composites as we track them above)'.  On each line of the list is the ID of the composite.  The following number is the current possible score of that object as if game were over that turn.  In the case of Lakes and Trails a third column exists, either true or false whether that composite is complete or not.

---
### Update from class 11/9/16

Things you'll know and need to tell:

* your turn to place a tile and what that tile is
* Your move must be received within 1 second
    * Where, orientation, and location of meeple if meeple placed
* Your move will be confirmed [sent to both players]
    * Valid
    * Invalid: illegal tile placement => forfeit
    * Invalid: illegal meeple placement => forfeit
    * Invalid: timeout => forfeit
* If a move caused a scoring event [sent to both players]
    * Score each player earned
    * Number of meeples each player gets back
* If end of game
    * For each unsecured feature, report score
    * Report winner and final scores

Tiger Zone theme:

* Jungles == farms
* Lakes == cities
* Game trails == roads
* Dens == monastery

Tiles will be square
77 tiles total

---
### Update from class 11/2/16

* Dave will not be in class Friday 11/4
* One week sprints

    * deliverables will be due Monday

* System Design artifacts

    * BCE/"Clean"/Hexagonal Architecture

* Development Planning artifacts
* Testing artifacts (unit, integration, and end-to-end/acceptance)

    * FIT, Fitnesse
    * Cucumber
    * Other acceptance testing tools

* We are playing by the _ORIGINAL_ rules. [Carcassonne - Wikipedia](https://en.wikipedia.org/wiki/Carcassonne).

* Question from class: "Do we have to check if the opponent's move is valid?"

    * _Dave: Check to make sure every move made on your own system is valid_

* Final reminders:

    * It's a two-player game
    * First game, we are the first player
    * Second game, we are the second player
    * The games are simultaneous, 1~2 minutes for each game


---

#### Class notes 10/31/16

* competition will run on a server (networking via TCP sockets)
* your AI will have a limited amount of total time for its turns (1-2 minutes)

    * out of time? Forfeit
    * illegal move? Forfeit

* will be playing two games simultaneously

    *  same stack of tiles
    * one game will be as the first player, the other as the second player
