import java.util.*;

/*-----------------------------------------------------------------------------------------------	
|	Region Interface:   											
|  	Contains methods common to all types of Regions			  										
-------------------------------------------------------------------------------------------------*/

public abstract class Region {
	//Can have multiple Meeples (maybe one yours and one for opposing player)
	Meeple placedMeeples;
    int prey;
    int id;
    int type;
    int regionID;  //To take care of merging?
    
    List<Coor> tiles;
    //List of coordinates?  Useful when merging two regions/keeping score.
    //Can be passed to the graph to "merge" regions?
    
    public int getID(){
    	return id;
    }
    
    public void setID(int i){
    	id = i;
    }
    
    public Meeple getMeeples()
    {
        return placedMeeples;
    }
    
    public List<Coor> getCoors(){
    	return tiles;
    }

    public int getType() {
        return type;
    }
    
    public boolean equals(Region r){
    	return this.type == r.getType();
    }
    
    public void expand(Coor c){
    	if(!tiles.contains(c)) tiles.add(c);
    	//Can be overridden to also change possible score if we decide to score by region.
    }
    
    //Union of the two sets of Region coordinates
    public void merge(Region r){
    	for(Coor c : r.getCoors()){
    		expand(c);
    	}
    }
    
    public abstract int score();
}
