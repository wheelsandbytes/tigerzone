import java.util.*;

/*-----------------------------------------------------------------------------------------------	
|	Region Interface:   											
|  	Contains methods common to all types of Regions			  										
-------------------------------------------------------------------------------------------------*/

public abstract class Region {
	//Can have multiple Meeples (maybe one yours and one for opposing player)
	Tiger placedMeeples;
    int id;
    int type;    
    
    public int getID(){
    	return id;
    }
    
    public void setID(int i){
    	id = i;
    }
    
    public Tiger getMeeples()
    {
        return placedMeeples;
    }

    public int getType() {
        return type;
    }
    
    public boolean equals(Region r){
    	//System.out.println(r.getType() +" " + this.getType());
    	return this.type == r.getType();
    }
    
    public String toString(){
    	return id + " ";
    }    
    
}
