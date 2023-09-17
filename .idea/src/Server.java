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
    private static ArrayList<String[]> allData = new ArrayList<String[]>();

    public int serverNumber;

    public Server(int serverNumberArg) {
        this.serverNumber = serverNumberArg;
        availableServerNumbers.remove( availableServerNumbers.indexOf(this.serverNumber) ); // mark this number as used when thread starts
    }
    
    public static void main(String[] args) {
        fileToArray();

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

        while(true) { // indefinite server running loop
            if ( availableServerNumbers.size() != 0 ) {// fire up whatever server numbers are available
                int firstAvailableServerNumber = availableServerNumbers.get(0);
                Server serverObject = new Server(firstAvailableServerNumber);
                serverObject.start();
                System.out.println("Fired up Server Number " + firstAvailableServerNumber + "!");

            }

        }
    }

    private static void fileToArray(){
        try {
            File dataFile = new File(".idea\\src\\UserData.txt");
            Scanner fileScanner = new Scanner(dataFile);

            while ( fileScanner.hasNextLine() ) {
                String nxtln = fileScanner.nextLine();
                System.out.println(nxtln);
                allData.add( nxtln.split(",") );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private static void arrayToFile(){
        try {
            FileWriter writer1 = new FileWriter(".idea\\src\\UserData.txt");

            String toWrite = "";
            for (String[] profile : allData) {
                for (String dataPoint : profile) {
                    toWrite += dataPoint + ",";
                }
                toWrite += "\n";
            }

            writer1.append(toWrite);
            writer1.close();

        } catch (Exception e) {
            System.out.println(e);
        }
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

            // logging-in phase
            String usernameLoggedInto = null;
            while( null == usernameLoggedInto ) {
                usernameLoggedInto = loginReceive( printWriterS1, bufferedReaderS1 );
            }
            
            // this profile writing phase
            

            // other profile reading phase



            // logging-out phase
            serverSocket1.close();
            availableServerNumbers.add(this.serverNumber); // free up server number when thread ends

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
    private String loginReceive( PrintWriter printWriterS1, BufferedReader bufferedReaderS1) throws IOException, UnknownHostException {

        HashMap<String, String> loginDetails = new HashMap<String, String>();
        for (String[] profileLine : allData) {
            loginDetails.put( profileLine[0], profileLine[1] );
        }

        // receive data
        String username = new String(bufferedReaderS1.readLine());
        String password = new String(bufferedReaderS1.readLine());

        // try to login and send feedback
        if ( null == loginDetails.get(username) ) { // user doesn't exist, create new user
            printWriterS1.println("User Doesn't Exist.");

            allData.add(new String[] {username, password, " ", " ", " ", " "});
            arrayToFile();

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
