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

    public class Frame extends JFrame {
        public static void main(String[] args) {
            new Frame();
        }
        public Frame() {

            // frame & panel
            JFrame frame = new JFrame();
            JPanel panel = new JPanel();
            frame.add(panel);  // put panel inside the frame

            // frame setting
            frame.setVisible(true);
            frame.setPreferredSize(new Dimension(840, 840 / 12 * 9));
            frame.setSize(840, 840 / 12 * 9);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel btnpanel = new JPanel();
            panel.add(btnpanel);
            JButton btn1 = new JButton("Log In");
            btnpanel.add(btn1);
            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MeetPurdueMain();
                    new Login();
                    setVisible(false);
                }
            });


            // label
            JLabel label = new JLabel("Welcome");

            panel.add(label); // add label to panel
        }



        }


