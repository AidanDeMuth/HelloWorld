import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Server extends Thread{

    public static final int[] SERVER_PORTS = {5000, 5001, 5002, 5003, 5004, 5005, 5006, 5007, 5008, 5009};
    
    public static ArrayList<Integer> availableServerNumbers = new ArrayList<Integer>();
    public static String serverIP = "127.0.0.1";
    public static String serverName = "LOCALHOST";

    public int serverNumber;
    private ArrayList<String[]> allData = new ArrayList<String[]>();

    public Server(int serverNumberArg) {
        this.serverNumber = serverNumberArg;
        availableServerNumbers.remove( availableServerNumbers.indexOf(this.serverNumber) ); // mark this number as used when thread starts
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < SERVER_PORTS.length; i++ ) {
            availableServerNumbers.add(i);
        }

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter the IP address of this server (press enter for default): ");
        String serverIPInput = scanner1.nextLine();
        if ( !"".equals(serverIPInput) ) {
            serverIP = serverIPInput;
        }
        System.out.println("Enter the name of this server (press enter for default): ");
        String serverNameInput = scanner1.nextLine();
        if ( !"".equals(serverNameInput) ) {
            serverIP = serverNameInput;
        }
        scanner1.close();

        int b = 0;
        while(true) { // indefinite server running loop
            if ( availableServerNumbers.size() != 0 ) {// fire up whatever server numbers are available
                int firstAvailableServerNumber = availableServerNumbers.get(0);
                Server serverObject = new Server(firstAvailableServerNumber);
                serverObject.start();
                System.out.println("Fired up Server Number " + firstAvailableServerNumber + "!");

            }

            b++;
            if(b==0){
                System.out.println("cycle");
            }
        }
    }

    private void fileToArray(){

    }
    private void arrayToFile(){

    }

    public void run() {
        try {

            // opens ServerSocket, waits for connecting ClientSocket
            ServerSocket serverSocket1 = new ServerSocket( SERVER_PORTS[serverNumber] );
            System.out.println("Server number " + serverNumber + ", port number " + SERVER_PORTS[serverNumber] + ": Waiting on client to connect...");
            Socket socketS1 = serverSocket1.accept();
            System.out.println("Server number " + serverNumber + ", port number " + SERVER_PORTS[serverNumber] + " successfully connected!");

            // prepare to send data
            PrintWriter printWriterS1 = new PrintWriter(socketS1.getOutputStream(), true); // auto flushing output writer
            // get ready to receive data
            InputStreamReader inputStreamReaderS1 = new InputStreamReader(socketS1.getInputStream()); // input reader
            BufferedReader bufferedReaderS1 = new BufferedReader(inputStreamReaderS1); // buffered input reader

            ///////////////////////////////////////////////////////////////

            HashMap<String, String> loginDetails = new HashMap<String, String>();
            loginDetails.put("Aidan", "password123");

            // logging-in phase
            String usernameLoggedInto = null;
            while( null == usernameLoggedInto ) {
                usernameLoggedInto = loginReceive( printWriterS1, bufferedReaderS1, loginDetails );
            }
            
            // this profile writing phase
            

            // other profile reading phase



            // logging-out phase
            serverSocket1.close();
            availableServerNumbers.add(this.serverNumber); // free up server number when thread ends
            System.out.println(availableServerNumbers);

        } catch (UnknownHostException e1) {
            System.out.println(e1);
        } catch (IOException e2) {
            System.out.println(e2);
        }
    }

    /*
     * returns null if login was NOT successful
     * 
     */
    private static String loginReceive( PrintWriter printWriterS1, BufferedReader bufferedReaderS1, HashMap loginDetails) throws IOException, UnknownHostException {

        // receive data
        String username = new String(bufferedReaderS1.readLine());
        String password = new String(bufferedReaderS1.readLine());

        // try to login and send feedback
        if ( null == loginDetails.get(username) ) { // user doesn't exist, create new user
            printWriterS1.println("User Doesn't Exist.");

            allData.add(new String[] {username, password, "", "", "", ""});

            return null;
        } else if ( !password.equals(loginDetails.get(username)) ) { // password is incorrect, 
            printWriterS1.println("Password is Incorrect.");
            return null;
        } else { // login successful
            printWriterS1.println("Login Successful!");
            return username;
        }

    }



}
