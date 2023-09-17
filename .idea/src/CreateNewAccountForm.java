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

//FileWriter


public class CreateNewAccountForm extends JFrame {

    JButton submitButton, backButton;
    JPanel formPanel;
    JLabel usernameLabel, passwordLabel, confirmPasswordLabel, finalLabel;
    final JTextField textField1, textField2, textField3;

    PrintWriter printWriterC1;
    BufferedReader bufferedReaderC1;

    CreateNewAccountForm( PrintWriter printWriterC1arg, BufferedReader bufferedReaderC1arg ) {
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

        //Confirm Password Label
        confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setText("Confirm Password");
        textField3 = new JTextField(150);

        //Submit Button
        submitButton = new JButton("SUBMIT");

        //Back Button
        backButton = new JButton("BACK");

        setUpButtonListeners();

        //Form List
        formPanel = new JPanel(new GridLayout(4, 1));
        formPanel.add(usernameLabel);
        formPanel.add(textField1);
        formPanel.add(passwordLabel);
        formPanel.add(textField2);
        formPanel.add(confirmPasswordLabel);
        formPanel.add(textField3);
        formPanel.add(submitButton);
        formPanel.add(backButton);

        //Format
        add(formPanel, BorderLayout.CENTER);

        //Submit Button
        setTitle("Create Account Form");

        formPanel.setSize(1000, 1000);
    }

    public void setUpButtonListeners() {
        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Pulls info from text boxs
                String userValue = textField1.getText().trim();
                String passValue = textField2.getText().trim();
                String confirmValue = textField3.getText().trim();

                //Reads each line, finds first word, checks if they match
                boolean checker = false;
                String line;
                String[] elements;
                try {
                    FileReader fr = new FileReader("UserData.txt");
                    BufferedReader br = new BufferedReader(fr);
                    while ((line = br.readLine()) != null) {
                        elements = line.split(" ");
                        if (elements[0].equals(userValue)) {
                            checker = true;
                        }
                    }
                    br.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                } catch (IOException ex) {
                    System.out.println("IO Exception");
                }


                //Checks the correct inputs
                if (checker) {
                    System.out.println("Username Already Exists!");
                } else if ((userValue.equals("") || passValue.equals("") || confirmValue.equals(""))) {
                    System.out.println("Empty value entered!");
                } else if (!passValue.equals(confirmValue)) {
                    System.out.println("Passwords do not match!");
                } else if (passValue.equals(confirmValue) && (userValue != null && passValue != null && confirmValue != null)) {
                    try {
                        //This section writes to the file
                        FileWriter writer = new FileWriter("UserData.txt", true);
                        String[] writeString = {userValue, passValue};
                        String dataLine = String.join(" ", writeString);
                        dataLine += "\n";
                        System.out.println(dataLine);
                        writer.write(dataLine);
                        writer.flush();
                        writer.close();

                        //This section opens a new login
                        CreateLoginForm form = new CreateLoginForm( printWriterC1, bufferedReaderC1 );
                        form.setPreferredSize(new Dimension(840, 840 / 12 * 9));
                        form.setSize(840, 840 / 12 * 9);
                        form.setVisible(true);
                        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        form.setLocationRelativeTo(null);

                        //Close current form
                        setVisible(false);
                        dispose();

                    } catch (Exception e) {
                        ;
                    }
                } else {
                    System.out.println("Please enter valid username and password");
                }
            }
        };

        ActionListener backListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Create New Form
                CreateLoginForm form = new CreateLoginForm( printWriterC1, bufferedReaderC1 );
                form.setPreferredSize(new Dimension(840, 840 / 12 * 9));
                form.setSize(840, 840 / 12 * 9);
                form.setVisible(true);
                form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                form.setLocationRelativeTo(null);

                //Remove old form
                setVisible(false);
                dispose();
            }
        };

        submitButton.addActionListener(submitListener);
        backButton.addActionListener(backListener);
    }
}