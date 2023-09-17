import java.net.*;
import java.io.*;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

//idek
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MeetPurdueMain {
    public static void main(String[] args) {
        try {

            String serverIP = "127.0.0.1";
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Enter the IP address of the server (press enter for default): ");
            String serverIPInput = scanner1.nextLine();
            if ( !"".equals(serverIPInput) ) {
                serverIP = serverIPInput;
            }
            scanner1.close();

            // tries to connect to open ServerSocket
            Socket socketC1 = null;
            boolean openPortFound = false;
            int portIndex = 0;
            while ( !openPortFound ) {
                if ( portIndex >= Server.SERVER_PORTS.length ) {
                    System.out.println("All server connections full! Try again later.");
                    return;
                } else {
                    try {
                        socketC1 = new Socket(serverIP, Server.SERVER_PORTS[portIndex]);
                        System.out.println("Available port found! Now connected to port " + Server.SERVER_PORTS[portIndex] + " of server " + serverIP);
                        openPortFound = true;
                    } catch (Exception portAlreadyUsed){
                        portIndex++;
                    }
                    
                }
            }

            // prepare to send data
            PrintWriter printWriterC1 = new PrintWriter(socketC1.getOutputStream(), true); // auto flushing output writer      
            // get ready to receive data
            InputStreamReader inputStreamReaderC1 = new InputStreamReader(socketC1.getInputStream()); // input reader (not going to be used)
            BufferedReader bufferedReaderC1 = new BufferedReader(inputStreamReaderC1); // buffered input reader

            CreateLoginForm form = new CreateLoginForm( printWriterC1, bufferedReaderC1 );
            form.setPreferredSize(new Dimension(840, 840 / 12 * 9));
            form.setSize(840, 840 / 12 * 9);
            form.setVisible(true);
            form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            form.setLocationRelativeTo(null);

            Scanner waiter = new Scanner(System.in); // this is just to keep the main method from returning/ending
            int waiterResult = waiter.nextInt();
            System.out.println("AAAAAAAAAAAAAAAAAA");

            socketC1.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}