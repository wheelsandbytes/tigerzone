/*-----------------------------------------------------------------------------------------------
|	Farmer class extends Meeple.java:
|  	Contains methods specific to Farmer Meeple type
-------------------------------------------------------------------------------------------------*/

public class Farmer extends Meeple {

    public Farmer() {
        type = GameInfo.FIELD;
        score = 0;
        isAvailable = true;
    }

    public int getScore() {
        return score;
    }
}
