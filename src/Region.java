import java.util.*;

/*-----------------------------------------------------------------------------------------------	
|	Region Interface:   											
|  	Contains methods common to all types of Regions			  										
-------------------------------------------------------------------------------------------------*/

public abstract class Region {
	//Can have multiple Meeples (maybe one yours and one for opposing player)
	List<Meeple> placedMeeples;
    int type;
    int scoreVar;

    public List<Meeple> getMeeples()
    {
        return placedMeeples;
    }

    public int getType() {
        return type;
    }
    
    public boolean equals(Region r){
    	return this.type == r.getType();
    }
    
    public abstract int score();
    public abstract void merge(Region r);
}
