/*

    This is the class contains the TCPAdapter that will be used
    to communicate with the server.

    This class will send/receive messages to/from the server
*/

import java.io.*;
import java.net.*;

public class TCPAdapter {

    private static final boolean DEBUG = false; // easy on/off debug messages

    private String hostName;
    private int portNumber;
    private Socket tzSocket;
    private PrintWriter out;
    public BufferedReader in;

    public TCPAdapter () {} // default constructor

    public TCPAdapter (String h, int p)
    {
        hostName = h;
        portNumber = p;

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

    public void sendMessage (String m)
    {
        System.out.println("Client: " + m);
        out.println(m);
    }

    public String receiveMessage ()
    {
        try {
            return in.readLine();

        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    public static void main(String[] args) throws IOException
    {
        // check to make sure we're specifying what server to connect to
        if (args.length != 2) {
            System.err.println(
            "Usage: java TCPAdapter <host name> <port number>");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String fromUser = "";
        String fromServer = "";

        TCPAdapter adapter = new TCPAdapter(args[0],Integer.parseInt(args[1]));

        for (int j = 0; j<6 ; j++) {
            fromServer = adapter.receiveMessage();

            fromUser = stdIn.readLine();
            adapter.sendMessage(fromUser);
        }

    }
}
