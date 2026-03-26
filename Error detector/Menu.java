import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {

    JTextField f1, f2;
    JButton b1, b2, b3, b4, b0, b5,b6, m, re, his;
    public JPanel cp;
    public JPanel ot;
    JTextArea historyArea;

    public Menu() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = screenSize.width;
        int screenH = screenSize.height;

        JLabel l1 = new JLabel("Error Detection System");
        l1.setFont(new Font("Eracake", Font.PLAIN, 48));
        l1.setForeground(Color.decode("#eeeee4"));
        l1.setBackground(Color.decode("#063970"));
        l1.setOpaque(true);
        l1.setBounds(0, 0, screenW, 120);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setVerticalAlignment(SwingConstants.CENTER);
        add(l1);

        // MENU PANEL
        JPanel menuP = new JPanel();
        menuP.setBounds(3, 123, 400, screenH - 126);
        menuP.setBackground(Color.decode("#063970"));
        menuP.setLayout(null);
        add(menuP);

        b1 = new JButton("Parity Bit");
        b1.setFont(new Font("Tahoma", Font.PLAIN, 21));
        b1.setBounds(3, 1, 395, 50);
        b1.setBackground(Color.white);
        b1.setForeground(Color.darkGray);
        b1.setOpaque(true);
        b1.setBorderPainted(true);
        b1.setFocusPainted(true);
        b1.setContentAreaFilled(true);
        b1.addActionListener(this);
        menuP.add(b1);

        b6 = new JButton("Hamming Code");
        b6.setFont(new Font("Tahoma", Font.PLAIN, 21));
        b6.setBounds(3, 53, 395, 50);
        b6.setBackground(Color.white);
        b6.setForeground(Color.darkGray);
        b6.setOpaque(true);
        b6.setBorderPainted(true);
        b6.setFocusPainted(true);
        b6.setContentAreaFilled(true);
        b6.addActionListener(this);
        menuP.add(b6);

        b2 = new JButton("Hamming Distacne");
        b2.setFont(new Font("Tahoma", Font.PLAIN, 21));
        b2.setBounds(3, 105, 395, 50);
        b2.setBackground(Color.white);
        b2.setForeground(Color.darkGray);
        b2.setOpaque(true);
        b2.setBorderPainted(true);
        b2.setFocusPainted(true);
        b2.setContentAreaFilled(true);
        b2.addActionListener(this);
        menuP.add(b2);

        b3 = new JButton("Bit Stuffing & Destuffing");
        b3.setFont(new Font("Tahoma", Font.PLAIN, 21));
        b3.setBounds(3, 157, 395, 50);
        b3.setBackground(Color.white);
        b3.setForeground(Color.darkGray);
        b3.setOpaque(true);
        b3.setBorderPainted(true);
        b3.setFocusPainted(true);
        b3.setContentAreaFilled(true);
        b3.addActionListener(this);
        menuP.add(b3);

        b4 = new JButton("Character Stuffing & Destuffing");
        b4.setFont(new Font("Tahoma", Font.PLAIN, 21));
        b4.setBounds(3, 209, 395, 50);
        b4.setBackground(Color.white);
        b4.setForeground(Color.darkGray);
        b4.setOpaque(true);
        b4.setBorderPainted(true);
        b4.setFocusPainted(true);
        b4.setContentAreaFilled(true);
        b4.addActionListener(this);
        menuP.add(b4);

        b0 = new JButton("Cyclic Redundancy Check");
        b0.setFont(new Font("Tahoma", Font.PLAIN, 21));
        b0.setBounds(3, 261, 395, 50);
        b0.setBackground(Color.white);
        b0.setForeground(Color.darkGray);
        b0.setOpaque(true);
        b0.setBorderPainted(true);
        b0.setFocusPainted(true);
        b0.setContentAreaFilled(true);
        b0.addActionListener(this);
        menuP.add(b0);

        re = new JButton("Reset");
        re.setFont(new Font("Tahoma", Font.PLAIN, 23));
        re.setBounds(5, screenH - (120 + 100 + 55), 194, 50);
        re.setBackground(Color.white);
        re.setForeground(Color.DARK_GRAY);
        re.setOpaque(true);
        re.setBorderPainted(true);
        re.setFocusPainted(true);
        re.setContentAreaFilled(true);
        re.addActionListener(this);
        menuP.add(re);

        m = new JButton("Minimize");
        m.setFont(new Font("Tahoma", Font.PLAIN, 23));
        m.setBounds(5, screenH - (120 + 100), 194, 50);
        m.setBackground(Color.white);
        m.setForeground(Color.DARK_GRAY);
        m.setOpaque(true);
        m.setBorderPainted(true);
        m.setFocusPainted(true);
        m.setContentAreaFilled(true);
        m.addActionListener(this);
        menuP.add(m);

        his = new JButton("Histroy");
        his.setFont(new Font("Tahoma", Font.PLAIN, 23));
        his.setBounds(201, screenH - (120 + 100 + 55), 194, 50);
        his.setBackground(Color.white);
        his.setForeground(Color.DARK_GRAY);
        his.setOpaque(true);
        his.setBorderPainted(true);
        his.setFocusPainted(true);
        his.setContentAreaFilled(true);
        his.addActionListener(this);
        menuP.add(his);

        b5 = new JButton("Exit");
        b5.setFont(new Font("Tahoma", Font.PLAIN, 23));
        b5.setBounds(201, screenH - (120 + 100), 194, 50);
        b5.setBackground(Color.white);
        b5.setForeground(Color.decode("#ff0000"));
        b5.setOpaque(true);
        b5.setBorderPainted(true);
        b5.setFocusPainted(true);
        b5.setContentAreaFilled(true);
        b5.addActionListener(this);
        menuP.add(b5);

        // CONTROL PANEL
        cp = new JPanel();
        cp.setBounds(405, 123, screenW - 405, (screenH / 3) * 2 - 110);
        cp.setBackground(Color.decode("#eeeee4"));
        cp.setLayout(null);
        add(cp);

        // Output PANEL

        JLabel o1 = new JLabel("Output Panel");
        o1.setFont(new Font("Arial", Font.BOLD, 15));
        o1.setForeground(Color.white);
        o1.setBackground(Color.DARK_GRAY);
        o1.setOpaque(true);
        o1.setBounds(405, (screenH / 3) * 2 + 15, screenW - 405, 30);
        add(o1);

        ot = new JPanel();
        ot.setBackground(Color.WHITE);
        ot.setLayout(null); 

        // Make sure to set preferred size if you're using null layout!
        ot.setPreferredSize(new Dimension(screenW - 405, 1000)); 

        JScrollPane scrollPane = new JScrollPane(ot);
        scrollPane.setBounds(405, (screenH / 3) * 2 + 45, screenW - 405, (screenH / 3) );

        add(scrollPane);

        // add(o1);

        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#ffff"));
        setLayout(null);
        setBounds(0, 0, screenW, screenH);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        // Parity Bit
        if (e.getSource() == b1) {
            new parity();
            setVisible(false);

        }
        // Hamming Distance
        else if (e.getSource() == b2) {
            new Hamming();
            setVisible(false);
        }
        // Bit Stuffing and Destuffing
        else if (e.getSource() == b3) {
            new bit();
            setVisible(false);
        }
        // Character Stuffing and Destuffing
        else if (e.getSource() == b4) {
            new Cstuf();
            setVisible(false);
        }
        // Character Stuffing and Destuffing
        else if (e.getSource() == b0) {
            new crc();
            setVisible(false);
        }
        // Reset Button
        else if (e.getSource() == re) {
            setVisible(false);
            new Menu();
        } else if (e.getSource() == his) {
            new History();
        }
        // Minimize
        else if (e.getSource() == m) {
            setState(JFrame.ICONIFIED);
        }
        // Close
        else if (e.getSource() == b5) {

            setVisible(false);
            System.exit(0);
        }
        else if(e.getSource()==b6)
        {
            new hammingCode();
            setVisible(false);
        }

    }

}
