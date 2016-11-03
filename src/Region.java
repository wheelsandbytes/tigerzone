import java.util.*;

/*-----------------------------------------------------------------------------------------------	
|	Region Interface:   											
|  	Contains methods common to all types of Regions			  										
-------------------------------------------------------------------------------------------------*/

public interface Region {
	//Can have multiple Meeples (maybe one yours and one for opposing player)
	List<Meeple> getMeeples();
	int getType();
}
