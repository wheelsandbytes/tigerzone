import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Road class implements Region.java:
|  	Contains methods specific to Road Region Type
-------------------------------------------------------------------------------------------------*/

public class Road implements Region {

    List<Meeple> placedMeeples;
    int type;

    public Road() 
    {
        type = GameInfo.ROAD;
    }

    public List<Meeple> getMeeples()
    {
        return placedMeeples;
    }

    public int getType() {
        return type;
    }
}