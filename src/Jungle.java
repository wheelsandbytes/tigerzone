import java.util.ArrayList;
import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Field class implements Region.java:
|  	Contains methods specific to Field Region Type
-------------------------------------------------------------------------------------------------*/

public class Jungle extends Region {
	
	List<Lake> adjLakes;
	Den den;
	JungleComposite myComp = null;

    public Jungle() 
    {
        type = GameInfo.FIELD;
        id = -1;
        adjLakes = new ArrayList<Lake>();
    }
    
    public Jungle(Den d) 
    {
        type = GameInfo.FIELD;
        id = -1;
        adjLakes = new ArrayList<Lake>();
        den = d;
    }
    
    public Jungle(Lake... lakes)
    {
        type = GameInfo.FIELD;
        id = -1;
        adjLakes = new ArrayList<Lake>();
        
        for(Lake l : lakes){
        	adjLakes.add(l);
        }
    }
    
    public Jungle(Den d, Lake... lakes)
    {
        type = GameInfo.FIELD;
        id = -1;
        
        den = d;
        
        for(Lake l : lakes){
        	adjLakes.add(l);
        }
    }
    
    public List<Lake> getLakes(){
    	return adjLakes;
    }
    
    public JungleComposite getComp(){
    	return myComp;
    }
    
    public void setComp(JungleComposite j){
    	myComp = j;
    }
	
	public String toString(){
		return "F" + super.toString();
	}

	public Den getDen() {
		return den;
	}
}