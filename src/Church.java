import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Church class implements Region.java:
|  	Contains methods specific to Church Region Type
-------------------------------------------------------------------------------------------------*/

public class Church implements Region {

    List<Meeple> placedMeeples;
    int type;

    public Church() 
    {
        type = GameInfo.CHURCH;
    }

    public List<Meeple> getMeeples()
    {
        return placedMeeples;
    }

    public int getType() {
        return type;
    }
}