import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Field class implements Region.java:
|  	Contains methods specific to Field Region Type
-------------------------------------------------------------------------------------------------*/

public class Field implements Region {

    List<Meeple> placedMeeples;
    int type;

    public Field() 
    {
        type = GameInfo.FIELD;
    }

    public List<Meeple> getMeeples()
    {
        return placedMeeples;
    }

    public int getType() {
        return type;
    }
}