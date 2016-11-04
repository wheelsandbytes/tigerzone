/*-----------------------------------------------------------------------------------------------
|	City class implements Region.java:
|  	Contains methods specific to City Region Type
-------------------------------------------------------------------------------------------------*/

public class City implements Region {

    List<Meeple> placedMeeples;
    int type;

    public City() 
    {
        type = GameInfo.CITY;
    }

    public List<Meeple> getMeeples()
    {
        return placedMeeples;
    }

    public int getType() {
        return type;
    }
}