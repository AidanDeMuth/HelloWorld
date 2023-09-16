//for UI

import javax.swing.*;

//for login system
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;

public class CreateLoginForm extends JFrame implements ActionListener {
    //Create frame
    JFrame j = new JFrame();

    //Form components
    JButton submitButton;
    JPanel formPanel;
    JLabel usernameLabel, passwordLabel;
    final JTextField textField1, textField2;

    CreateLoginForm() {
        //Username Label
        usernameLabel = new JLabel();
        usernameLabel.setText("Enter Username");
        textField1 = new JTextField(15);

        //Password Label
        passwordLabel = new JLabel();
        passwordLabel.setText("Enter Password");
        textField2 = new JTextField(15);

        //Form List
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passwordLabel);
        newPanel.add(textField2);
        newPanel.add(b1);
        add(newPanel, BoderLayout.CENTER);

        //Submit Button
        b1.addActionListener(this);
        setTitle("Login Form");
    }

    public void actionPerformed(ActionEvent ae)
    {
        String userValue = textField1.getText();
        String passValue = textField2.getText();

        if(userValue.equals("test1") && passValue.equals("test2")) {
            NewPage page = new NewPage();
            page.setVisible(true);
        } else {
            System.out.println("Please enter valid username and password");
        }
    }
}
