import java.util.*;
/*------------------------------------------------------------------------------
|	AI extends Player Class:
|  	Contains information and behaviours of the AI
------------------------------------------------------------------------------*/

/*
AI functions:

Everything the base Player can do plus:

	Calulate the coordinate and rotation of the tile to be placed

*/
public class AI extends Player{

	// this method overrides setMove
	// this is where the decision making happens
	 private void setMove(Tile t)
	 {
		 Coor loc;
		 int rot;
		 // decisions to set loc and rot...
		move = new Move(loc,rot,t);
	 }

}
