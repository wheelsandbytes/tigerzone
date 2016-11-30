import java.util.*;

/*-----------------------------------------------------------------------------------------------	
|	Region Interface:   											
|  	Contains methods common to all types of Regions			  										
-------------------------------------------------------------------------------------------------*/

public abstract class Region {
	//Can have multiple Meeples (maybe one yours and one for opposing player)
	protected List<Tiger> placedMeeples = new ArrayList<Tiger>();
    protected int id;
    protected int type;    
    
    public int getID(){
    	return id;
    }
    
    public void setID(int i){
    	id = i;
    }
    
    public List<Tiger> getMeeples(){
        return placedMeeples;
    }
    
    public void setMeeple(Tiger t){
    	placedMeeples.add(t);
    }
    

    public int getType() {
        return type;
    }
    
    public boolean equals(Region r){
    	return this.type == r.getType();
    }
    
    public String toString(){
    	return id + " ";
    }

	public void removeMeeple(Tiger t) {
		placedMeeples.remove(placedMeeples.indexOf(t));
	}  
	    
}
