/*-----------------------------------------------------------------------------------------------
|	MeeplePlacement Class:
|  	Encapsulates Meeple placement information. Public accessible fields.
-------------------------------------------------------------------------------------------------*/

public class MeeplePlacement{
	public int type;
	public int pos;
	
	MeeplePlacement(int type, int pos){
		this.type = type;
		this.pos = pos;
	}
}