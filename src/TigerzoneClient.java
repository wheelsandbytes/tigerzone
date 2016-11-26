/*

- This class communicates with the tournament server directly

*/

import java.io.*;
import java.net.*;

public class TigerzoneClient {

    private static final boolean DEBUG = false; // for easy on/off debug messages

    private int challenges; // number of challenges given by the server
    private int rounds; // number of rounds given by the server
    private int rid; // current round ID
    private int ourPID; // our assigned player ID
    private int oppPID; // opponent player ID
    private int gid;

    public static void main(String[] args) throws IOException {

        // check to make sure we're specifying what server to connect to
        if (args.length != 2) {
            System.err.println(
            "Usage: java TigerZoneClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try {
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));

            String fromServer;

            // this is where the messages are exchanged with the server
            while ((fromServer = in.readLine()) != null) { // waiting on response
                System.out.println("Server: " + fromServer);

                if (fromServer.equals("Bye.")) // termination message from server
                    break;

                // send to server whatever gets processed and returned
                // out.println(processMessage(fromServer));
                System.out.println("SUCCESS"); System.exit(1);

            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
            hostName);
            System.exit(1);
        }

    }

    private String processMessage(String fromServer)
    {
        String clientResponse;
        clientResponse = "HELLO";
        // switch (fromServer) {
        //     case "THIS IS SPARTA!":
        //         clientResponse = getUserInput();
        //         break;
        //
        //     case "HELLO!":
        //         clientResponse = getUserInput();
        //         break;
        //
        //     case "":
        // }
        return clientResponse;
    }

    private String getUserInput()
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

    private String getAIinput()
    {
        return "SUCCESS";
    }
}
