/*
    This class extends Player and provides explicit functionality
    for communicating with the TCP server
*/

public class TCPPlayer extends Player {

    public Move decideMove (String fromServer)
    {
        Move move;
        // logic for parsing fromServer and extracting move details
        String delims = "[\\[ \\]]+";
        String[] tokens = fromServer.split(delims);

        /*
        GAME <gid> MOVE <#> PLAYER <pid> <move>
            where <move> :=
                PLACED <tile> AT <x> <y> <orientation> NONE or
                PLACED <tile> AT <x> <y> <orientation> CROCODILE or
                PLACED <tile> AT <x> <y> <orientation> TIGER <zone> or
                 0       1    2   3   4      5           6     7

                TILE <tile> UNPLACEABLE PASSED or
                TILE <tile> UNPLACEABLE RETRIEVED     TIGER AT <x> <y> or
                TILE <tile> UNPLACEABLE ADDED ANOTHER TIGER TO <x> <y>
                 0     1        2         3     4       5   6   7   7
        */
        return move;
	}

	public MeeplePlacement decideMeeple ()
    {
		return null;
	}
}
