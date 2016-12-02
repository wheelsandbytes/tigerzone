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
### [Architectural Design](https://www.gliffy.com/go/share/s8yo6dxqp3tha72iwy0p)

### [UML Diagram](https://www.gliffy.com/go/share/satac75pd0xs1h24vyih)


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
