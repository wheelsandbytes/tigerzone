package GameEntities.DataTools;
/*-----------------------------------------------------------------------------------------------
|	MeeplePlacement Class:
|  	Encapsulates Meeple placement information. Public accessible fields.
-------------------------------------------------------------------------------------------------*/

public class MeeplePlacement{
	public int type;
	public int pos;
	
	public MeeplePlacement(int type, int pos){
		this.type = type;
		this.pos = pos;
	}
}