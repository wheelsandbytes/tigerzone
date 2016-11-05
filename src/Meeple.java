/*-----------------------------------------------------------------------------------------------
|	Meeple abstract class
|  	Contains methods specific to Meeples
-------------------------------------------------------------------------------------------------*/

public abstract class Meeple {

    int type;
    int score;
    boolean isAvailable;
    Region region;

	public abstract int getScore();

    public int getType() { return type; }

    public Region getRegion() { return region; }

	public boolean isAvailable() { return isAvailable; }

}
