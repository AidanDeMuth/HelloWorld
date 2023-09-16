import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

//idek
import javax.swing.*;
import java.awt.*;
import java.util.*;

//FileWriter
import java.io.FileWriter;
import java.io.IOException;


public class CreateNewAccountForm extends JFrame implements ActionListener{

    JButton submitButton;
    JPanel formPanel;
    JLabel usernameLabel, passwordLabel, confirmPasswordLabel;
    final JTextField textField1, textField2, textField3;

    CreateNewAccountForm() {
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

        //Form List
        formPanel = new JPanel(new GridLayout(4, 1));
        formPanel.add(usernameLabel);
        formPanel.add(textField1);
        formPanel.add(passwordLabel);
        formPanel.add(textField2);
        formPanel.add(confirmPasswordLabel);
        formPanel.add(textField3);
        formPanel.add(submitButton);
        add(formPanel, BorderLayout.CENTER);

        //Submit Button
        submitButton.addActionListener(this);
        setTitle("Create Account Form");

        formPanel.setSize(1000,1000);
    }
    public void actionPerformed(ActionEvent ae)
    {
        //Pulls info from text boxs
        //UserValue is username
        String userValue = textField1.getText();
        //PassValue is password
        String passValue = textField2.getText();
        String confirmValue = textField3.getText();

        if(passValue.equals(confirmValue)) {
            try
            {
                FileWriter writer = new FileWriter("UserData.csv", true);
                System.out.println("Got Here");
                String info = userValue + "," + passValue;
                writer.write(info);
            } catch (Exception e) {
                ;
            }


        } else {
            System.out.println("Please enter valid username and password");
        }
    }
}
