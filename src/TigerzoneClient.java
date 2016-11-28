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

        // Create a TCPAdapter to communicate with the server
        TCPAdapter adapter = new TCPAdapter(host,port);

        // Declare strings for messages exchanged with server:
        String fromServer;
        String toServer;

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
        List<Tile> deckA = new LinkedList<Tile>();
        List<Tile> deckB = new LinkedList<Tile>();

        deckB = new LinkedList<Tile>();

        // Declare players
        AI AIplayerA;
        AI AIplayerB;
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
            if (DEBUG) System.out.println("adapter.receiverMessage()");
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
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("MATCH") && tokens[1].equals("BEGINS"))
            {
                // MATCH BEGINS IN <timeplan> SECONDS
                //  0      1    2     3         4

                // n SECONDS TO PREP FOR THE MATCH

                // Generate the starting tile
                startTile = tf.create(startingTile);

                if (DEBUG) System.out.println("startTile created");

                // Create the two decks
                for (int j = 0; j < tiles.length; j++)
                {
                    // Passing each tile string into TileFactory to make the tiles
                    // then adding the returned Tile to the deck
                    deckA.add( tf.create ( tiles[j] ) );
                    deckB.add( tf.create ( tiles[j] ) );
                }

                if (DEBUG) System.out.println("Decks created");

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
                move = new Move(new Coor(x,y),orientation/90,startTile);
                BoardA.place(move);
                BoardB.place(move);

                if (DEBUG) System.out.println("startTile placed on both boards");
                adapter.sendMessage("");
            }
            else if (tokens[0].equals("MAKE") && tokens[1].equals("YOUR"))
            {
                // MAKE YOUR MOVE IN GAME <gid> WITHIN <timemove> SECOND: MOVE <#> PLACE <tile>
                //  0    1    2   3   4     5     6        7        8      9   10   11    12
                gid = tokens[5];
                moveNumber = Integer.parseInt(tokens[10]);
                tile = tokens[12];

                if (DEBUG) System.out.println("gid: " + gid);
                if (DEBUG) System.out.println("moveNumber: " + moveNumber);
                if (DEBUG) System.out.println("tile: " + tile);

                if (gid.equals("A"))
                {
                    // AIplayerA makes the move in BoardA

                    // GAME <gid> MOVE <#> PLACE <tile> AT <x> <y> <orientation> NONE
                    // GAME <gid> MOVE <#> PLACE <tile> AT <x> <y> <orientation> CROCODILE
                    // GAME <gid> MOVE <#> PLACE <tile> AT <x> <y> <orientation> TIGER <zone>
                    // toServer = "GAME " + gid + " MOVE " + moveNumber + " PLACE " + move.toString() + "NONE";

                    // GAME <gid> MOVE <#> TILE <tile> UNPLACEABLE PASS
                    // GAME <gid> MOVE <#> TILE <tile> UNPLACEABLE RETRIEVE TIGER AT <x> <y>
                    // GAME <gid> MOVE <#> TILE <tile> UNPLACEABLE ADD ANOTHER TIGER TO <x> <y>
                    // toServer = "GAME " + gid + " MOVE " + moveNumber + " TILE " + tile + " UNPLACEABLE PASS";

                    // adapter.sendMessage(toServer);
                }

                if (gid.equals("B"))
                {
                    // AIplayerB makes the move in BoardB
                }
                adapter.sendMessage("HERE'S MY MOVE");
            }
            else if (tokens[0].equals("GAME") && tokens[6].equals("PLACED"))
            {
                // SERVER SENDS WHAT HAS BEEN PLAYED

                // GAME <gid> MOVE <#> PLAYER <pid> PLACED <tile> AT <x> <y> <orientation> NONE
                //  0     1    2    3     4     5     6      7    8   9  10       11        12

                gid = tokens[1];
                pid = tokens[5];

                if (pid != username) // make sure it's not us who just played
                {
                    moveNumber = Integer.parseInt(tokens[3]);
                    tile = tokens[5];
                    Tile tfTile = tf.create(tile);
                    x = Integer.parseInt(tokens[9]);
                    y = Integer.parseInt(tokens[10]);
                    orientation = Integer.parseInt(tokens[11]);
                    meeple = tokens[12];

                    if (meeple.equals("NONE"))
                    {
                        move = new Move(new Coor(x,y),orientation/90,tfTile);
                    }

                    if (meeple.equals("TIGER"))
                    {
                        zone = Integer.parseInt(tokens[13]);
                        move = new Move(new Coor(x,y),orientation/90,tfTile);
                    }

                    if (meeple.equals("CROCODILE"))
                    {
                        move = new Move(new Coor(x,y),orientation/90,tfTile,true);
                    }

                    if (gid.equals("A"))
                    {
                        // tcpA makes the move in BoardA
                        tcpA.makeMove(move);
                    }

                    if (gid.equals("B"))
                    {
                        // tcpB makes the move in BoardB
                        tcpB.makeMove(move);
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

                // gid = tokens[1];
                // pid = tokens[5];
                //
                // if (pid != username) // make sure it's not us who just played
                // {
                //     moveNumber = Integer.parseInt(tokens[3]);
                //     tile = tokens[5];
                //     x = Integer.parseInt(tokens[9]);
                //     y = Integer.parseInt(tokens[10]);
                //     orientation = Integer.parseInt(tokens[11]);
                //     meeple = tokens[12];
                //
                //
                // }
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

                // Just wait here too...
                adapter.sendMessage("");
            }
            else
            {
                if (DEBUG) System.out.println("REACHED ELSE CONDITION");
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
