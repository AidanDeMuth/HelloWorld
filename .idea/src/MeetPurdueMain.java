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

public class MeetPurdueMain {
    public static void main(String[] args) {
        try {
            CreateLoginForm form = new CreateLoginForm();
            form.setPreferredSize(new Dimension(840, 840 / 12 * 9));
            form.setSize(840, 840/12*9);
            form.setLocationRelativeTo(null);
            form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
