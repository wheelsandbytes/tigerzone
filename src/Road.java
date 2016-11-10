import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Road class implements Region.java:
|  	Contains methods specific to Road Region Type
-------------------------------------------------------------------------------------------------*/

public class Road extends Region {
		
    public Road() 
    {
        type = GameInfo.ROAD;
    }

	@Override
	public int score() {
		return scoreVar;
	}

	@Override
	public void merge(Region r) {
		
	}
    
}