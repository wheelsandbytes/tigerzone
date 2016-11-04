/*-----------------------------------------------------------------------------------------------
|	Monk class implements Meeple.java:
|  	Contains methods specific to Monk Meeple type
-------------------------------------------------------------------------------------------------*/

public class Monk implements Meeple {

    int type;
    int score;
    boolean isAvailable;
    Region region;

    public Monk() {
        type = GameInfo.CHURCH;
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
