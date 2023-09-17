import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

public class CreateLoginForm extends JFrame{
    //Form components

    JButton submitButton, newAccountButton;
    JPanel formPanel;
    JLabel usernameLabel, passwordLabel;
    final JTextField textField1, textField2;

    public void setUpButtonListeners(){
        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String userValue = textField1.getText();
                String passValue = textField2.getText();

                //Reads file to check if user and pass are correct
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
                if(checker) {
                    System.out.println("Password correct!");
                } else {
                    System.out.println("Username/Password incorrect!");
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

    CreateLoginForm() {

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
