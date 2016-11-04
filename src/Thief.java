/*-----------------------------------------------------------------------------------------------
|	Thief class implements Meeple.java:
|  	Contains methods specific to Thief Meeple type
-------------------------------------------------------------------------------------------------*/

public class Thief implements Meeple {

    int type;
    int score;
    boolean isAvailable;
    Region region;

    public Thief() {
        type = GameInfo.ROAD;
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
    
    @Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return isAvailable;
	}
}
