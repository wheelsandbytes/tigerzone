/*-----------------------------------------------------------------------------------------------
|	Monk class extends Meeple.java:
|  	Contains methods specific to Monk Meeple type
-------------------------------------------------------------------------------------------------*/

public class Monk extends Meeple {

    public Monk() {
        type = GameInfo.CHURCH;
        score = 0;
        isAvailable = true;
    }

    public int getScore() {
        return score;
    }
}
