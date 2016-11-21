import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Church class implements Region.java:
|  	Contains methods specific to Church Region Type
-------------------------------------------------------------------------------------------------*/

public class Den extends Region {

    public Den() 
    {
        type = GameInfo.CHURCH;
    }

	@Override
	public int score() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void merge(Region r) {
		
	}
}