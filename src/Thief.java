/*-----------------------------------------------------------------------------------------------
|	Thief class extends Meeple.java:
|  	Contains methods specific to Thief Meeple type
-------------------------------------------------------------------------------------------------*/

public class Thief extends Meeple {

    public Thief() {
        type = GameInfo.ROAD;
        score = 0;
        isAvailable = true;
    }

    public int getScore() {
        return score;
    }
}
