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

public class CreateLoginForm extends JFrame implements ActionListener {
    //Form components

    JButton submitButton;
    JPanel formPanel;
    JLabel usernameLabel, passwordLabel;
    final JTextField textField1, textField2;


    public static void main(String[] args) {
        try {
            new CreateLoginForm();
            CreateLoginForm form = new CreateLoginForm();
            form.setSize(1000, 1000);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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

        //Form List
        formPanel = new JPanel(new GridLayout(3, 1));
        formPanel.add(usernameLabel);
        formPanel.add(textField1);
        formPanel.add(passwordLabel);
        formPanel.add(textField2);
        formPanel.add(submitButton);
        add(formPanel, BorderLayout.CENTER);

        //Submit Button
        submitButton.addActionListener(this);
        setTitle("Login Form");

        formPanel.setSize(1000,1000);
    }


    public void actionPerformed(ActionEvent ae)
    {
        //Pulls info from text boxs
        String userValue = textField1.getText();
        String passValue = textField2.getText();

        if(userValue.equals("test1") && passValue.equals("test2")) {
            //send to new page
        } else {
            System.out.println("Please enter valid username and password");
        }
    }


}
