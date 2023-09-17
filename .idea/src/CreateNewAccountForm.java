import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
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
    JLabel usernameLabel, passwordLabel, confirmPasswordLabel, finalLabel;
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
        //Last Label
        finalLabel = new JLabel();
        finalLabel.setText("Space for Text");
        formPanel.add(finalLabel);

        //Format
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
        String userValue = textField1.getText().trim();
        //PassValue is password
        String passValue = textField2.getText().trim();
        String confirmValue = textField3.getText().trim();

        if(passValue.equals(confirmValue) && (userValue != null && passValue != null && confirmValue != null)) {

            try
            {
                //This section writes to the file
                FileWriter writer = new FileWriter("UserData.txt", true);
                String[] writeString = {userValue, passValue};
                String dataLine = String.join(" ",writeString);
                dataLine += "\n";
                System.out.println(dataLine);
                writer.write(dataLine);
                writer.flush();
                writer.close();

                //This section opens a new login
                CreateLoginForm form = new CreateLoginForm();
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
}
