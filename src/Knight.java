/*-----------------------------------------------------------------------------------------------
|	Knight class implements Meeple.java:
|  	Contains methods specific to Knight Meeple type
-------------------------------------------------------------------------------------------------*/

public class Knight implements Meeple {

    int type;
    int score;
    boolean isAvailable;
    Region region;

    public Knight() {
        type = GameInfo.CITY;
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
