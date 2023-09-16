import java.net.*;
import java.io.*;
import java.util.HashMap;

public class Server extends Thread{

    public static final int[] SERVER_PORTS = {5001, 5002, 5003, 5004, 5005};
    public static final String SERVER_IP = "";
    public static final String SERVER_NAME = "LOCALHOST";

    public int serverNumber;

    public Server(int serverNumberArg) {
        serverNumber = serverNumberArg;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < SERVER_PORTS.length(); i++){

        }
    }


    public void run() {

        try {

            // opens ServerSocket, waits for connecting ClientSocket
            ServerSocket serverSocket1 = new ServerSocket( 5001 );
            System.out.println("Waiting on client to connect...");
            Socket socketS1 = serverSocket1.accept();
            System.out.println("Server successfully connected!");

            // prepare to send data
            PrintWriter printerWriterS1 = new PrintWriter(socketS1.getOutputStream(), true); // auto flushing output writer
            // get ready to receive data
            InputStreamReader inputStreamReaderS1 = new InputStreamReader(socketS1.getInputStream()); // input reader
            BufferedReader bufferedReaderS1 = new BufferedReader(inputStreamReaderS1); // buffered input reader

            ///////////////////////////////////////////////////////////////

            HashMap<String, String> loginDetails = new HashMap<String, String>();
            loginDetails.put("Aidan", "password123");

            boolean loginSuccessful = false;
            while ( !loginSuccessful ) {
                loginSuccessful = loginReceive( printerWriterS1, bufferedReaderS1, loginDetails );
            }
            System.out.println("Someone successfully logged in!");

        } catch (UnknownHostException e1) {
            System.out.println(e1);
        } catch (IOException e2) {
            System.out.println(e2);
        }
    }


    private static boolean loginReceive( PrintWriter printerWriterS1, BufferedReader bufferedReaderS1, HashMap loginDetails) throws IOException, UnknownHostException {

        // receive data
        boolean loggedIn = false;
        String username = new String(bufferedReaderS1.readLine());
        String password = new String(bufferedReaderS1.readLine());

        // try to login and send feedback
        if ( null == loginDetails.get(username) ) { // user doesn't exist
            printerWriterS1.println("User Doesn't Exist.");
            return false;
        } else if ( !password.equals(loginDetails.get(username)) ) { // password is incorrect
            printerWriterS1.println("Password is Incorrect.");
            return false;
        } else { // login successful
            printerWriterS1.println("Login Successful!");
            return true;
        }

    }



}
