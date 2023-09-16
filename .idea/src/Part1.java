import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Part1 extends JFrame {
    Part1() {
        super("Log In"); //타이틀
        JPanel p = new JPanel();

        p.setBackground(Color.BLUE);
        JButton btn1 = new JButton();
        p.add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(300, 200);

        add(p);

        setVisible(true);
    }
}
