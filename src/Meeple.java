/*-----------------------------------------------------------------------------------------------	
|	Meeple Interface:   											
|  	Contains methods common to all types of Meeples			  										
-------------------------------------------------------------------------------------------------*/

public interface Meeple {
	int getScore();
	int getType();
	boolean isAvailable();
	Region getRegion(); 
}
