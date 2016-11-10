import java.util.List;

/*-----------------------------------------------------------------------------------------------
|	Field class implements Region.java:
|  	Contains methods specific to Field Region Type
-------------------------------------------------------------------------------------------------*/

public class Field extends Region {
	
    public Field() 
    {
        type = GameInfo.FIELD;
    }

	@Override
	public int score() {
		return 3;
	}

	@Override
	public void merge(Region r) {
		// TODO Auto-generated method stub
		
	}

}