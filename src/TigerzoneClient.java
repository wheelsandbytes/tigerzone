/*

- This class communicates with the tournament server directly

*/

import java.io.*;
import java.net.*;

public class TigerzoneClient {

    private static final boolean DEBUG = true; // for easy on/off debug messages

    // Client connection parameters
    private static String hostName;
    private static int portNumber;
    private static Socket tzSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    // establish connection to the server
    private static void connectToServer(String host, int port)
    {
        hostName = host;
        portNumber = port;
        try {
            tzSocket = new Socket(hostName, portNumber);
            out = new PrintWriter(tzSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(tzSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
            hostName);
            System.exit(1);
        }
        if (DEBUG){
            System.out.println("Connection established successfully");
            System.out.println("I/O established successfully");
        }

    }

    private static void sendMessage(String m)
    {
        System.out.println(m);
        out.println(m);
    }

    private static String joinTournament(String password)
    {
        String joinMessage = "JOIN " + password;
        if (DEBUG) System.out.println(joinMessage);
        return joinMessage;
    }

    private static String joinGame(String username, String password)
    {
        String joinMessage = "I AM " + username + " " + password;
        if (DEBUG) System.out.println(joinMessage);
        return joinMessage;
    }

    private static String playMove()
    {
        return "GAME <gid> PLACE <tile> AT <x> <y> <orientation> <meeple type>";
    }

    public static void main(String[] args) throws IOException {

        // check to make sure we're specifying what server to connect to
        if (args.length != 5) {
            System.err.println(
            "Usage: java TigerZoneClient <host name> <port number> <tournament password> <team username> <password>");
            System.exit(1);
        }

        if (DEBUG) System.out.println(args);

        // Connect to the server and establish the I/O streams for communication
        connectToServer ( args[0] , Integer.parseInt( args[1] ) ) ;

        String fromServer; // string that holds the server messages
        String toServer = ""; // string that will be sent to the server

        // Initialize tournament details:
        int challenges; // number of challenges given by the server
        int rounds; // number of rounds given by the server
        int rid; // current round ID
        int cid; // current challenge ID
        String opponent; // opponent player name
        String gid; // game ID when parsing server messages

        // Possible states
        int WAITING = 0;
        int MAKEMOVE = 1;
        int state = WAITING;

        /*
            Initialize game boards here:
            // BoardA
            // BoardB
        */

        // -----------------------THIS IS THE MAIN LOOP-------------------------

        while ((fromServer = in.readLine()) != null)
        { // wait for a message from the server

            System.out.println("Server: " + fromServer);

            // Split the message
            String delims = "[ ]+";
            String[] tokens = fromServer.split(delims);

            if (DEBUG)
            {
                for (int i = 0; i < tokens.length; i++)
                    System.out.println(tokens[i]);
            }

            // PARSING
            if (fromServer.equals("THANK YOU FOR PLAYING! GOODBYE")) // termination message from server
                break;

            if(fromServer.equals("THIS IS SPARTA!"))
            {
                sendMessage(joinTournament(args[2]));
                state  = WAITING;
            }
            else if (fromServer.equals("HELLO!"))
            {
                sendMessage(joinGame(args[3],args[4]));
                state  = WAITING;
            }
            else if (fromServer.equals("Knock! Knock!")) //ignore this for now
            {
                System.out.println(getUserInput());
                break;
            }
            else if (tokens[0].equals("WELCOME"))
            {
                // this is the welcome message
                // we sit and wait
            }
            else if (tokens[0].equals("NEW") && tokens[1].equals("CHALLENGE"))
            {
                // NEW CHALLENGE <cid> YOU WILL PLAY <rounds> MATCH
                //  0      1       2    3    4    5     6       7
                cid = Integer.parseInt(tokens[2]);
                rounds = Integer.parseInt(tokens[6]);

                if (DEBUG) System.out.println("cid = " + cid + " and rounds = " + rounds);
            }
            else if (tokens[0].equals("BEGIN") && tokens[1].equals("ROUND"))
            {
                // BEGIN ROUND <rid> OF <rounds>
                //   0     1     2    3    4
                rid = Integer.parseInt(tokens[2]);

                if (DEBUG) System.out.println("rid = " + rid);
            }
            else if (tokens[0].equals("YOUR") && tokens[1].equals("OPPONENT"))
            {
                // YOUR OPPONENT IS PLAYER <pid>
                //   0     1      2    3     4
                opponent = tokens[4];

                if (DEBUG) System.out.println("opponent = " + opponent);
            }
            else if (tokens[0].equals("STARTING") && tokens[1].equals("TILE"))
            {
                // STARTING TILE IS <tile> AT <x> <y> <orientation>
                //    0      1   2   3     4   5   6       7
                String tile = tokens[3];
                int x = Integer.parseInt(tokens[5]);
                int y = Integer.parseInt(tokens[6]);
                int orientation = Integer.parseInt(tokens[7]);

                if (DEBUG) System.out.println("tile = " + tile + "and x,y,orientation = " + x + " " + y + " " + orientation);
            }
        }
    }

    private static String getUserInput()
    {
        String fromUser;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            fromUser = br.readLine();
            if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                return fromUser;
            }
        } catch (IOException e) {
            System.err.println("BAD INPUT");
            System.exit(1);
        }
        return "";
    }

}
