/*-----------------------------------------------------------------------------------------------
|	Farmer class implements Meeple.java:
|  	Contains methods specific to Farmer Meeple type
-------------------------------------------------------------------------------------------------*/

public class Farmer implements Meeple {

    int type;
    int score;
    boolean isAvailable;
    Region region;

    public Farmer() {
        type = GameInfo.FIELD;
        score = 0;
        isAvailable = true;
    }

    public int getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public Region getRegion() {
        return region;
    }
}
