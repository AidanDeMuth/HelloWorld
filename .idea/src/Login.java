import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    Login() {
        super("Log In"); //타이틀
        JPanel jpanel = new JPanel();

        JButton btnSubmit = new JButton("Submit");
        JPanel btnpanel = new JPanel();
        jpanel.add(btnpanel);
        btnpanel.add(btnSubmit);
        btnSubmit.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               setVisible(false);
           }
       });


        setSize(300, 200);
        Dimension frameSize = getSize();

        add(jpanel);
        setVisible(true);
        };


}
