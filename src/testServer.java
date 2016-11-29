import java.net.*;
import java.io.*;
import java.util.*;

public class testServer {
	public Deck d1 = new Deck();
	
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage: java testServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));

            String inputLine, outputLine;

            // Initiate conversation with client
            outputLine = "THIS IS SPARTA!";
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);

                outputLine = stdIn.readLine();
                out.println(outputLine);
                if (outputLine.equals("Bye."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
