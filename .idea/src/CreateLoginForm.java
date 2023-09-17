import java.net.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//idek
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CreateLoginForm extends JFrame{
    //Form components

    JButton submitButton, newAccountButton;
    JPanel formPanel;
    JLabel usernameLabel, passwordLabel;
    final JTextField textField1, textField2;

    PrintWriter printWriterC1;
    BufferedReader bufferedReaderC1;

    public void setUpButtonListeners(){
        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {

                    String userValue = textField1.getText();
                    String passValue = textField2.getText();

                    //Reads file to check if user and pass are correct
                    // NOTE: unused for now, may copy over for server-side data-keeping
                    /*
                    boolean checker = false;
                    String line;
                    String[] elements;
                    try {
                        FileReader fr = new FileReader("UserData.txt");
                        BufferedReader br = new BufferedReader(fr);
                        while ((line = br.readLine()) != null) {
                            elements = line.split(" ");
                            if (elements[0].equals(userValue) && elements[1].equals(passValue)) {
                                checker = true;
                            }
                        }
                        br.close();
                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");
                    } catch (IOException ex) {
                        System.out.println("IO Exception");
                    }
                    */

                    boolean loginSuccessful = false;
                    String loginFeedback = new String("");

                    // send data
                    printWriterC1.println(userValue);
                    printWriterC1.println(passValue);
                    // receive feedback
                    loginFeedback = new String(bufferedReaderC1.readLine());
                    loginSuccessful = "Login Successful!".equals(loginFeedback);

                    if(loginSuccessful) {
                        System.out.println("Password correct!");
                    } else {
                        System.out.println("Username/Password incorrect!");
                    }

                } catch (UnknownHostException e1){
                    System.out.println(e1);
                } catch (IOException e2){
                    System.out.println(e2);
                }
            }
        };

        ActionListener newButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Create New Form
                CreateNewAccountForm form = new CreateNewAccountForm();
                form.setPreferredSize(new Dimension(840, 840 / 12 * 9));
                form.setSize(840, 840 / 12 * 9);
                form.setVisible(true);
                form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                form.setLocationRelativeTo(null);

                //Remove Current Form
                dispose();
                setVisible(false);
            }
        };

        submitButton.addActionListener(submitListener);
        newAccountButton.addActionListener(newButtonListener);
    }

    CreateLoginForm( PrintWriter printWriterC1arg, BufferedReader bufferedReaderC1arg ) {

        printWriterC1 = printWriterC1arg;
        bufferedReaderC1 = bufferedReaderC1arg;

        //Username Label
        usernameLabel = new JLabel();
        usernameLabel.setText("Enter Username");
        textField1 = new JTextField(150);

        //Password Label
        passwordLabel = new JLabel();
        passwordLabel.setText("Enter Password");
        textField2 = new JTextField(150);

        //Submit Button
        submitButton = new JButton("SUBMIT");

        //New Button
        newAccountButton = new JButton("NEW ACCOUNT");
        setUpButtonListeners();

        //Form List
        formPanel = new JPanel(new GridLayout(3, 1));
        formPanel.add(usernameLabel);
        formPanel.add(textField1);
        formPanel.add(passwordLabel);
        formPanel.add(textField2);
        formPanel.add(submitButton);
        formPanel.add(newAccountButton);
        add(formPanel, BorderLayout.CENTER);

        //Form Title
        setTitle("Login Form");



        formPanel.setSize(1000,1000);
    }





}
