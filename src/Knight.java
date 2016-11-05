/*-----------------------------------------------------------------------------------------------
|	Knight class extends Meeple.java:
|  	Contains methods specific to Knight Meeple type
-------------------------------------------------------------------------------------------------*/

public class Knight extends Meeple {

    public Knight() {
        type = GameInfo.CITY;
        score = 0;
        isAvailable = true;
    }

    public int getScore() {
        return score;
    }
}
