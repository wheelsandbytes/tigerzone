/*

TigerZone Main Client

This is the class connects to the tournament server,
parses incoming server strings,
passes in the appropriate actions to the players,
and is able to play two simultaneous games of TigerZone.

*/

import java.io.*;
import java.net.*;
import java.util.*;

public class TigerzoneClient {

    private static final boolean DEBUG = true; // for easy on/off debug messages

    private static String playMove()
    {
        return "GAME <gid> PLACE <tile> AT <x> <y> <orientation> <meeple type>";
    }

    public static void main(String[] args) {

        // check to make sure we're specifying what server to connect to
        if (args.length != 5) {
            System.err.println(
            "Usage: java TigerzoneClient <host name> <port number> <tournament password> <team username> <password>");
            System.exit(1);
        }

        if (DEBUG)
        {
            for (int i = 0; i < args.length; i++)
                System.out.println(args[i]);
        }

        // Parse the arguments here:
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String tournamentPassword = args[2];
        String username = args[3];
        String password = args[4];
        String[] gids = {null, null};

        // Create a TCPAdapter to communicate with the server
        TCPAdapter adapter = new TCPAdapter(host,port);

        // Declare strings for messages exchanged with server:
        String fromServer;
        String toServer = "";

        // Declare tournament details:
        int challenges; // number of challenges given by the server
        int rounds; // number of rounds given by the server
        int rid; // current round ID
        int cid; // current challenge ID
        String gid; // game ID when parsing server messages
        String pid; // player ID when parsing
        int moveNumber;

        // Possible states
        // the integer values are arbitrary, this is just for clarity
        int WAITING = 0;
        int MAKEMOVE = 1;
        int RECEIVED = 2;
        int GAME_END = 9;
        int state = WAITING;

        // Declare opponent variables
        String opponentName = null;
        String opponentMove = null;

        // Declare the boards here
        Board BoardA = null;
        Board BoardB = null;

        // Declare board components
        TileFactory tf = new TileFactory();
        List<Tile> deckListA = new LinkedList<Tile>();
        List<Tile> deckListB = new LinkedList<Tile>();
        Deck deckA = null;
        Deck deckB = null;

        // Declare players
        AI AIplayerA = null;
        AI AIplayerB = null;
        TCPPlayer tcpA = null;
        TCPPlayer tcpB = null;

        // Declare preliminary placement details
        String startingTile = null;
        Tile startTile;
        String tile;
        String[] tiles = null;
        int x = 0;
        int y = 0;
        int orientation = 0;
        Move move = null;
        int zone;
        String meeple;

        while (state != GAME_END)
        {
            // Wait for a message from the server:
            fromServer = adapter.receiveMessage();

            // Split the message for parsing:
            String delims = "[\\[ \\]]+";
            String[] tokens = fromServer.split(delims);

            if (DEBUG)
            { for (int i = 0; i < tokens.length; i++) System.out.println(tokens[i]);}

            if (fromServer.equals("THANK YOU FOR PLAYING! GOODBYE"))
            {
                state = GAME_END;
                
            }
            else if (fromServer.equals("THIS IS SPARTA!"))
            {
                toServer = "JOIN " + tournamentPassword;
                adapter.sendMessage(toServer);
            }
            else if (tokens[0].equals("THIS") && tokens[1].equals("IS") && tokens[2].equals("SPARTA!"))
            {
                toServer = "JOIN " + tournamentPassword;
                adapter.sendMessage(toServer);
            }
            else if (fromServer.equals("HELLO!"))
            {
                toServer = "I AM " + username + " " + password;
                adapter.sendMessage(toServer);
            }
            else if (tokens[0].equals("WELCOME"))
            {
                // WELCOME <pid> PLEASE WAIT FOR THE NEXT CHALLENGE
                // we sit and wait
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("NEW") && tokens[1].equals("CHALLENGE"))
            {
                // NEW CHALLENGE <cid> YOU WILL PLAY <rounds> MATCH
                //  0      1       2    3    4    5     6       7
                cid = Integer.parseInt(tokens[2]);
                rounds = Integer.parseInt(tokens[6]);

                if (DEBUG) System.out.println("cid = " + cid + " and rounds = " + rounds);
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("BEGIN") && tokens[1].equals("ROUND"))
            {
                // BEGIN ROUND <rid> OF <rounds>
                //   0     1     2    3    4
                rid = Integer.parseInt(tokens[2]);

                if (DEBUG) System.out.println("rid = " + rid);
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("YOUR") && tokens[1].equals("OPPONENT"))
            {
                // YOUR OPPONENT IS PLAYER <pid>
                //   0     1      2    3     4
                opponentName = tokens[4];

                if (DEBUG) System.out.println("opponent = " + opponentName);
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("STARTING") && tokens[1].equals("TILE"))
            {
                // STARTING TILE IS <tile> AT <x> <y> <orientation>
                //    0      1   2   3     4   5   6       7
                startingTile = tokens[3];
                x = Integer.parseInt(tokens[5]);
                y = Integer.parseInt(tokens[6]);
                orientation = Integer.parseInt(tokens[7]);

                if (DEBUG) System.out.println("starting tile = " + startingTile + "and x,y,orientation = " + x + " " + y + " " + orientation);
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("THE") && tokens[1].equals("REMAINING"))
            {
                // THE REMAINING <number_tiles> TILES ARE [ <tiles> ]
                //  0      1            2         3    4       5 ... tokens.length
                int noTiles = Integer.parseInt(tokens[2]);

                if (DEBUG) System.out.println("no. of tiles = "+ noTiles);

                // Array of tiles to be sent to TileFactory later
                tiles = new String[noTiles];

                // Parse for the tiles
                for (int i = 5; i < tokens.length; i++)
                {
                    tiles[i-5] = tokens[i];
                    if (DEBUG) System.out.println("tiles["+(i-5)+"] = " + tiles[i-5] + "tokens["+i+"] = " +tokens[i]);
                }
                //adapter.sendMessage("");
            }
            else if (tokens[0].equals("MATCH") && tokens[1].equals("BEGINS"))
            {
                // MATCH BEGINS IN <timeplan> SECONDS
                //  0      1    2     3         4

                // n SECONDS TO PREP FOR THE MATCH

                // Generate the starting tile
                Tile startTileA = tf.create(startingTile);
                Tile startTileB = tf.create(startingTile);

                if (DEBUG) System.out.println("startTile created");

                // Create the two decks
                for (int j = 0; j < tiles.length; j++)
                {
                    // Passing each tile string into TileFactory to make the tiles
                    // then adding the returned Tile to the deck
                    deckListA.add( tf.create ( tiles[j] ) );
                    deckListB.add( tf.create ( tiles[j] ) );
                }

                if (DEBUG) System.out.println("Decks created");
                
                deckA = new Deck(deckListA);
                deckB = new Deck(deckListB);

                BoardA = new Board();
                BoardB = new Board();

                if (DEBUG) System.out.println("Boards initialized");

                AIplayerA = new AI(BoardA,username,deckA);
                AIplayerB = new AI(BoardB,username,deckB);

                if (DEBUG) System.out.println("AI players created, Boards passed in");

                tcpA = new TCPPlayer(BoardA,opponentName,deckA);
                tcpB = new TCPPlayer(BoardB,opponentName,deckB);

                if (DEBUG) System.out.println("TCP players created, boards passed in");

                // PLACE THE STARTING TILE ON BOTH BOARDS
                move = new Move(new Coor(x,y),orientation/90,startTileA);
                BoardA.place(move);
                move = new Move(new Coor(x,y),orientation/90,startTileB);
                BoardB.place(move);

                if (DEBUG) System.out.println("startTile placed on both boards");
                //adapter.sendMessage("");
            }
            else if (tokens[0].equals("MAKE") && tokens[1].equals("YOUR"))
            {
                // MAKE YOUR MOVE IN GAME <gid> WITHIN <timemove> SECOND: MOVE <#> PLACE <tile>
                //  0    1    2   3   4     5     6        7        8      9   10   11    12
                gid = tokens[5];
                moveNumber = Integer.parseInt(tokens[10]);
                tile = tokens[12];
                
                if(gids[0] == null) { gids[0] = gid; }
                else if(gids[1] == null) { gids[1] = gid; }

                if (DEBUG) System.out.println("gid: " + gid);
                if (DEBUG) System.out.println("moveNumber: " + moveNumber);
                if (DEBUG) System.out.println("tile: " + tile);

                // GAME <gid> MOVE <#> PLACE <tile> AT <x> <y> <orientation> NONE
                // GAME <gid> MOVE <#> PLACE <tile> AT <x> <y> <orientation> CROCODILE
                // GAME <gid> MOVE <#> PLACE <tile> AT <x> <y> <orientation> TIGER <zone>
                // toServer = "GAME " + gid + " MOVE " + moveNumber + " PLACE " + move.toString() + "NONE";

                // GAME <gid> MOVE <#> TILE <tile> UNPLACEABLE PASS
                // GAME <gid> MOVE <#> TILE <tile> UNPLACEABLE RETRIEVE TIGER AT <x> <y>
                // GAME <gid> MOVE <#> TILE <tile> UNPLACEABLE ADD ANOTHER TIGER TO <x> <y>
                // toServer = "GAME " + gid + " MOVE " + moveNumber + " TILE " + tile + " UNPLACEABLE PASS";

                if (gid.equals(gids[0]))
                {
                    // AIplayerA makes the move in BoardA
                    move = AIplayerA.decideMove();
                    
                    if (move != null)
                    {
                    	AIplayerA.makeMove(move);
                    	MeeplePlacement meep = AIplayerA.decideMeeple();
                    	
                    	if (meep != null)
                    	{
                    		if (meep.type == GameInfo.TIGER)
                            {
                                toServer = "GAME " + gid + " MOVE " + moveNumber + " PLACE " + move.toString() + " TIGER " + meep.pos;
                            }
                            else if (meep.type == GameInfo.CROCODILE)
                            {
                                toServer = "GAME " + gid + " MOVE " + moveNumber + " PLACE " + move.toString() + " CROCODILE";
                            }
                    		
                    		AIplayerA.placeMeeple(meep, move.getTile());
                    	}
                        else
                        {
                            toServer = "GAME " + gid + " MOVE " + moveNumber + " PLACE " + move.toString() + " NONE";
                        }
                    }
                    else 
                    {
                    	// PASS
                        toServer = "GAME " + gid + " MOVE " + moveNumber + " TILE " + tile + " UNPLACEABLE PASS";

                    }
                    deckA.next();
                }

                if (gid.equals(gids[1]))
                {
                    // AIplayerB makes the move in BoardB
                    move = AIplayerB.decideMove();
                    
                    if (move != null)
                    {
                    	AIplayerB.makeMove(move);
                    	MeeplePlacement meep = AIplayerB.decideMeeple();
                    	
                    	if (meep != null)
                    	{
                    		if (meep.type == GameInfo.TIGER)
                            {
                                toServer = "GAME " + gid + " MOVE " + moveNumber + " PLACE " + move.toString() + " TIGER " + meep.pos;
                            }
                            else if (meep.type == GameInfo.CROCODILE)
                            {
                                toServer = "GAME " + gid + " MOVE " + moveNumber + " PLACE " + move.toString() + " CROCODILE";
                            }
                    		
                    		AIplayerB.placeMeeple(meep, move.getTile());
                    	}
                        else
                        {
                            toServer = "GAME " + gid + " MOVE " + moveNumber + " PLACE " + move.toString() + " NONE";
                        }
                    }
                    else 
                    {
                    	// PASS
                        toServer = "GAME " + gid + " MOVE " + moveNumber + " TILE " + tile + " UNPLACEABLE PASS";

                    }
                    deckB.next();
                }
                // SEND THE MOVE TO THE SERVER
                adapter.sendMessage(toServer);
            }
            else if (tokens[0].equals("GAME") && tokens[6].equals("PLACED"))
            {
                // SERVER SENDS WHAT HAS BEEN PLAYED

                // GAME <gid> MOVE <#> PLAYER <pid> PLACED <tile> AT <x> <y> <orientation> NONE
                //  0     1    2    3     4     5     6      7    8   9  10       11        12

                gid = tokens[1];
                pid = tokens[5];
                if(gids[0] == null) { gids[0] = gid; }
                else if(gids[1] == null) { gids[1] = gid; }
                
                if (DEBUG) System.out.println("pid: "+pid);

                if (pid != username) // make sure it's not us who just played
                {
                    moveNumber = Integer.parseInt(tokens[3]);
                    tile = tokens[5];
                    x = Integer.parseInt(tokens[9]);
                    y = Integer.parseInt(tokens[10]);
                    orientation = Integer.parseInt(tokens[11]);
                    meeple = tokens[12];

                    if (gid.equals(gids[0]))
                    {
                        // tcpA makes the move in BoardA
                    	if (meeple.equals("NONE"))
                        {
                            move = new Move(new Coor(x,y),orientation/90,deckA.getCurrent());
                            tcpA.makeMove(move);
                        }

                    	else if (meeple.equals("TIGER"))
                        {
                            zone = Integer.parseInt(tokens[13]);
                            move = new Move(new Coor(x,y),orientation/90, deckA.getCurrent());
                            tcpA.makeMove(move);
                            tcpA.placeMeeple(new MeeplePlacement(GameInfo.TIGER, zone), deckA.getCurrent());
                        }

                    	else if (meeple.equals("CROCODILE"))
                        {
                            move = new Move(new Coor(x,y),orientation/90,deckA.getCurrent());
                            tcpA.makeMove(move);
                            tcpA.placeMeeple(new MeeplePlacement(GameInfo.CROCODILE, -1), deckA.getCurrent());
                        }
                        deckA.next();
                    }

                    if (gid.equals(gids[1]))
                    {
                        // tcpB makes the move in BoardB
                    	if (meeple.equals("NONE"))
                        {
                            move = new Move(new Coor(x,y),orientation/90,deckB.getCurrent());
                            tcpB.makeMove(move);
                        }

                    	else if (meeple.equals("TIGER"))
                        {
                            zone = Integer.parseInt(tokens[13]);
                            move = new Move(new Coor(x,y),orientation/90,deckB.getCurrent());
                            tcpB.makeMove(move);
                            tcpB.placeMeeple(new MeeplePlacement(GameInfo.TIGER, zone), deckB.getCurrent());
                        }

                    	else if (meeple.equals("CROCODILE"))
                        {
                            move = new Move(new Coor(x,y),orientation/90,deckB.getCurrent(),true);
                            tcpB.makeMove(move);
                            tcpB.placeMeeple(new MeeplePlacement(GameInfo.CROCODILE, -1), deckB.getCurrent());
                        }
                        deckB.next();
                    }
                }
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("GAME") && tokens[6].equals("TILE"))
            {
                // SERVER SENDS WHAT HAS BEEN PLAYED

                // GAME <gid> MOVE <#> PLAYER <pid> TILE <tile> UNPLACEABLE PASSED
                //  0     1    2    3    4      5     6    7       8          9

                // GAME <gid> MOVE <#> PLAYER <pid> TILE <tile> UNPLACEABLE RETRIEVED TIGER AT <x> <y>
                //  0     1    2    3    4      5     6    7       8          9        10   11  12  13

                // GAME <gid> MOVE <#> PLAYER <pid> TILE <tile> UNPLACEABLE ADDED ANOTHER TIGER TO <x> <y>
                //  0     1    2    3    4      5     6    7       8          9     10     11   12  13  14

                gid = tokens[1];
                pid = tokens[5];
                boolean PASS;

                if (DEBUG) System.out.println("pid: "+pid);

                if (pid != username) // make sure it's not us who just played
                {
                    moveNumber = Integer.parseInt(tokens[3]);
                    tile = tokens[5];

                    if (tokens[9].equals("RETRIEVED"))
                    {
                        x = Integer.parseInt(tokens[12]);
                        y = Integer.parseInt(tokens[13]);
                        PASS = false;
                    }
                    else if (tokens[9].equals("ADDED"))
                    {
                        x = Integer.parseInt(tokens[13]);
                        y = Integer.parseInt(tokens[14]);
                        PASS = false;
                    }
                    else
                    {
                        x = 0;
                        y = 0;
                        PASS = true;
                    }

                    if (gid.equals(gids[0]))
                    {
                        if (tokens[9].equals("RETRIEVED"))
                        {
                            tcpA.removeTiger(new Coor(x,y));
                        }
                        else if(tokens[9].equals("ADDED"))
                        {
                            tcpA.placeTiger(tcpA.getTiger(), new Coor(x,y));
                        }
                        deckA.next();
                    }
                    else if (gid.equals(gids[1]))
                    {
                    	if (tokens[9].equals("RETRIEVED"))
                        {
                            tcpB.removeTiger(new Coor(x,y));
                        }
                        else if(tokens[9].equals("ADDED"))
                        {
                            tcpB.placeTiger(tcpB.getTiger(), new Coor(x,y));
                        }
                        deckB.next();
                    }
                    

                }
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("GAME") && tokens[6].equals("FORFEITED:"))
            {

                // GAME <gid> MOVE <#> PLAYER <pid> FORFEITED: ILLEGAL TILE PLACEMENT
                //  0     1    2    3    4      5      6         7       8     9
                // just waiting here...
            	adapter.sendMessage("");
            }
            else if (tokens[0].equals("GAME") && tokens[2].equals("OVER"))
            {

                // GAME <gid> OVER PLAYER <pid> <score> PLAYER <pid> <score>
                //  0     1    2     3     4      5       6      7      8
                // GAME OVER, just waiting here...
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("END") || tokens[0].equals("PLEASE"))
            {

                // END OF ROUND <rid> OF <rounds>
                // END OF ROUND <rid> OF <rounds> PLEASE WAIT FOR THE NEXT MATCH
                // END OF CHALLENGES
                // PLEASE WAIT FOR THE NEXT CHALLENGE TO BEGIN
            	gids[0] = null;
            	gids[1] = null;
            	
            	tf = new TileFactory();
                // Just wait here too...
                adapter.sendMessage("");
            }
            else
            {
                if (DEBUG) System.out.println("SERVER SENT GARBAGE");
                adapter.sendMessage("");
            }

            if (state == GAME_END)
            {
                break;
            }
            else if (state == WAITING)
            {
                // do nothing, we are just waiting for the server
                if (DEBUG) System.out.println("ACTION Just waiting for the server...");
            }
        }
    }
}
