package MainSimulations;
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

import Adapters.TCPAdapter;
import GameEntities.Game;
import GameEntities.DataTools.Coor;
import GameEntities.DataTools.MeeplePlacement;
import GameEntities.DataTools.Move;
import GlobalReferences.GameInfo;

public class TigerzoneClient {

    private static final boolean DEBUG = true; // for easy on/off debug messages
//private static Drawer d1;
//private static Drawer d2;

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
        for (int i = 0; i < args.length; i++);
    }

    // Parse the arguments here:
    String host = args[0];
    int port = Integer.parseInt(args[1]);
    String tournamentPassword = args[2];
    String username = args[3];
    String password = args[4];
    String[] gids = {null, null};

    // Create a gameA.player2dapter to communicate with the server
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
    int moveNumber; // number of the move sent by the server

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

    // Declare the two games
    Game gameA = null;
    Game gameB = null;
    String gidGameA = null;
    String gidGameB = null;

    // Declare preliminary placement details
    String startingTile = null;
    String tile = null;
    String[] tiles = null;
    int x = 0;
    int y = 0;
    int orientation = 0;
    Move move = null;
    int zone;
    String meeple = null;

    // try {
    	while (state != GAME_END)
        { // Wait for a message from the server:

            fromServer = adapter.receiveMessage();
            // Split the message for parsing:
            String delims = "[\\[ \\]]+";
            
        	String[] tokens = fromServer.split(delims);

//            if (DEBUG)
//            { for (int i = 0; i < tokens.length; i++) System.out.println(tokens[i]);}
        	System.out.println("Server: "+fromServer);

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
            }
            else if (tokens[0].equals("NEW") && tokens[1].equals("CHALLENGE"))
            {
                // NEW CHALLENGE <cid> YOU WILL PLAY <rounds> MATCH
                //  0      1       2    3    4    5     6       7
                cid = Integer.parseInt(tokens[2]);
                rounds = Integer.parseInt(tokens[6]);

            }
            else if (tokens[0].equals("BEGIN") && tokens[1].equals("ROUND"))
            {
                // BEGIN ROUND <rid> OF <rounds>
                //   0     1     2    3    4
                rid = Integer.parseInt(tokens[2]);

                //
            }
            else if (tokens[0].equals("YOUR") && tokens[1].equals("OPPONENT"))
            {
                // YOUR OPPONENT IS PLAYER <pid>
                //   0     1      2    3     4
                opponentName = tokens[4];

            }
            else if (tokens[0].equals("STARTING") && tokens[1].equals("TILE"))
            {
                // STARTING TILE IS <tile> AT <x> <y> <orientation>
                //    0      1   2   3     4   5   6       7
                startingTile = tokens[3];
                x = Integer.parseInt(tokens[5]);
                y = Integer.parseInt(tokens[6]);
                orientation = Integer.parseInt(tokens[7]);

            }
            else if (tokens[0].equals("THE") && tokens[1].equals("REMAINING"))
            {
                // THE REMAINING <number_tiles> TILES ARE [ <tiles> ]
                //  0      1            2         3    4       5 ... tokens.length
                int noTiles = Integer.parseInt(tokens[2]);


                // String list of tiles to be sent to the Game for creation
                tiles = new String[noTiles];

                // Parse for the tiles
                for (int i = 5; i < tokens.length; i++)
                {
                    tiles[i-5] = tokens[i];
                }
            }
            else if (tokens[0].equals("MATCH") && tokens[1].equals("BEGINS"))
            {
                // MATCH BEGINS IN <timeplan> SECONDS
                //  0      1    2     3         4
                // Integer.parseInt(tokens[4]) SECONDS TO PREP FOR THE MATCH

                gameA = new Game(username,opponentName,startingTile,tiles,x,y,orientation/90);


                gameB = new Game(username,opponentName,startingTile,tiles,x,y,orientation/90);

            }
            else if (tokens[0].equals("MAKE") && tokens[1].equals("YOUR"))
            {
                // MAKE YOUR MOVE IN GAME <gid> WITHIN <timemove> SECOND: MOVE <#> PLACE <tile>
                //  0    1    2   3   4     5     6        7        8      9   10   11    12
                gid = tokens[5];
                moveNumber = Integer.parseInt(tokens[10]);
                tile = tokens[12];

                if(gidGameA == null) { gidGameA = gid; gidGameA = gid; }
                else if(gidGameB == null && !gid.equals(gidGameA)) { gidGameB = gid; gidGameB = gid; }


                // GAME <gid> MOVE <#> PLACE <tile> AT <x> <y> <orientation> NONE
                // GAME <gid> MOVE <#> PLACE <tile> AT <x> <y> <orientation> CROCODILE
                // GAME <gid> MOVE <#> PLACE <tile> AT <x> <y> <orientation> TIGER <zone>
                // toServer = "GAME " + gid + " MOVE " + moveNumber + " PLACE " + move.toString() + "NONE";

                // GAME <gid> MOVE <#> TILE <tile> UNPLACEABLE PASS
                // GAME <gid> MOVE <#> TILE <tile> UNPLACEABLE RETRIEVE TIGER AT <x> <y>
                // GAME <gid> MOVE <#> TILE <tile> UNPLACEABLE ADD ANOTHER TIGER TO <x> <y>
                // toServer = "GAME " + gid + " MOVE " + moveNumber + " TILE " + tile + " UNPLACEABLE PASS";

                if (gid.equals(gidGameA))
                {
                    // AI player makes the move in Game A
                    move = gameA.player1.decideMove();

                    if (move != null)
                    {
                    	gameA.player1.makeMove(move);
                    	MeeplePlacement meep = gameA.player1.decideMeeple();
                    	gameA.player1.placeMeeple(meep, move.getTile());
                    	
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
                    gameA.deck.next();
                }

                if (gid.equals(gidGameB))
                {
                    // AI makes the move in Game B
                    move = gameB.player1.decideMove();

                    if (move != null)
                    {
                    	gameB.player1.makeMove(move);
                    	MeeplePlacement meep = gameB.player1.decideMeeple();
                    	gameB.player1.placeMeeple(meep, move.getTile());
                    	
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
                    gameB.deck.next();
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
                if(gidGameA == null) { gidGameA = gid; }
                else if(gidGameB == null && !gid.equals(gidGameA)) { gidGameB = gid; }


                if (!pid.equals(username)) // make sure it's not us who just played
                {
                    moveNumber = Integer.parseInt(tokens[3]);
                    tile = tokens[5];
                    x = Integer.parseInt(tokens[9]);
                    y = Integer.parseInt(tokens[10]);
                    orientation = Integer.parseInt(tokens[11]);
                    meeple = tokens[12];

                    // IF THIS IS FOR GAME A
                    if (gid.equals(gidGameA))
                    {
                        // opponent makes the move in GameA
                    	if (meeple.equals("NONE"))
                        {
                            move = new Move(new Coor(x,y),orientation/90,gameA.deck.getCurrent());
                            gameA.player2.makeMove(move);
                            gameA.player2.placeMeeple(null, gameA.deck.getCurrent());
                        }
                    	else if (meeple.equals("TIGER"))
                        {
                            zone = Integer.parseInt(tokens[13]);
                            move = new Move(new Coor(x,y),orientation/90, gameA.deck.getCurrent());
                            gameA.player2.makeMove(move);
                            gameA.player2.placeMeeple(new MeeplePlacement(GameInfo.TIGER, zone), gameA.deck.getCurrent());
                        }
                    	else if (meeple.equals("CROCODILE"))
                        {
                            move = new Move(new Coor(x,y),orientation/90,gameA.deck.getCurrent());
                            gameA.player2.makeMove(move);
                            gameA.player2.placeMeeple(new MeeplePlacement(GameInfo.CROCODILE, -1), gameA.deck.getCurrent());
                        }
                        else
                        {
                        	//nothing
                        }
                        gameA.deck.next();
                    }

                    // IF THIS IS FOR GAME B
                    if (gid.equals(gidGameB))
                    {
                        // opponent makes the move in Game B
                    	if (meeple.equals("NONE"))
                        {
                            move = new Move(new Coor(x,y),orientation/90,gameB.deck.getCurrent());
                            gameB.player2.makeMove(move);
                            gameB.player2.placeMeeple(null, gameB.deck.getCurrent());
                        }

                    	else if (meeple.equals("TIGER"))
                        {
                            zone = Integer.parseInt(tokens[13]);
                            move = new Move(new Coor(x,y),orientation/90,gameB.deck.getCurrent());
                            gameB.player2.makeMove(move);
                            gameB.player2.placeMeeple(new MeeplePlacement(GameInfo.TIGER, zone), gameB.deck.getCurrent());
                        }

                    	else if (meeple.equals("CROCODILE"))
                        {
                            move = new Move(new Coor(x,y),orientation/90,gameB.deck.getCurrent(),true);
                            gameB.player2.makeMove(move);
                            gameB.player2.placeMeeple(new MeeplePlacement(GameInfo.CROCODILE, -1), gameB.deck.getCurrent());
                        }
                        gameB.deck.next();
                    }
                }
                //
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


                if (!pid.equals(username)) // make sure it's not us who just played
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

                    if (gid.equals(gidGameA))
                    {
                        if (tokens[9].equals("RETRIEVED"))
                        {
                            gameA.player2.removeTiger(new Coor(x,y));
                        }
                        else if(tokens[9].equals("ADDED"))
                        {
                            gameA.player2.placeTiger(gameA.player2.getTiger(), new Coor(x,y));
                        }
                        gameA.deck.next();
                    }
                    else if (gid.equals(gidGameB))
                    {
                    	if (tokens[9].equals("RETRIEVED"))
                        {
                            gameB.player2.removeTiger(new Coor(x,y));
                        }
                        else if(tokens[9].equals("ADDED"))
                        {
                            gameB.player2.placeTiger(gameB.player2.getTiger(), new Coor(x,y));
                        }
                        gameB.deck.next();
                    }


                }
                //
            }
            else if (tokens[0].equals("GAME") && tokens[6].equals("FORFEITED:"))
            {

                // GAME <gid> MOVE <#> PLAYER <pid> FORFEITED: ILLEGAL TILE PLACEMENT
                //  0     1    2    3    4      5      6         7       8     9
                // just waiting here...
            	//
            }
            else if (tokens[0].equals("GAME") && tokens[2].equals("OVER"))
            {

                // GAME <gid> OVER PLAYER <pid> <score> PLAYER <pid> <score>
                //  0     1    2     3     4      5       6      7      8
                // GAME OVER, just waiting here...
                //
            }
            else if (tokens[0].equals("END") || tokens[0].equals("PLEASE"))
            {

                // END OF ROUND <rid> OF <rounds>
                // END OF ROUND <rid> OF <rounds> PLEASE WAIT FOR THE NEXT MATCH
                // END OF CHALLENGES
                // PLEASE WAIT FOR THE NEXT CHALLENGE TO BEGIN
            	gidGameA = null;
            	gidGameB = null;
                // gameA = null;
                // gameB = null;
                // Just wait here too...
            }
            else
            {
            }

            if (state == GAME_END)
            {
                break;
            }
            else if (state == WAITING)
            {
                // do nothing, we are just waiting for the server
            }
        }
    // } catch (IOException e) {
    //     	System.err.println("CONNECTION ERROR");
    //     }
    }
}
