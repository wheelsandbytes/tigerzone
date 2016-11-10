import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	City class implements Region.java:
|  	Contains methods specific to City Region Type
-------------------------------------------------------------------------------------------------*/

public class City extends Region {

    public City() 
    {
        type = GameInfo.CITY;
    }

	@Override
	public int score() {
		return 0;
	}

	@Override
	public void merge(Region r) {
		
	}
}