import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    Main() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = screenSize.width;
        int screenH = screenSize.height;

        JLabel l1 = new JLabel("Error Detection System");
        l1.setFont(new Font("Eracake", Font.PLAIN, 44));
        l1.setForeground(Color.decode("#eeeee4"));
        l1.setBounds(0, (screenH - (screenH / 2)) / 2 - 100, screenW - screenH, 200);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setVerticalAlignment(SwingConstants.CENTER);
        add(l1);

        JLabel l2 = new JLabel("<html>Version rtp(1.0) / 64-bit(win64)<br>April 2025</html>");
        l2.setFont(new Font("Arial", Font.PLAIN, 13));
        l2.setForeground(Color.decode("#eeeee4"));
        l2.setBounds(25, -50, 700, 200);
        add(l2);

        JLabel l3 = new JLabel("<html><b>Creator:</b><br>Md Rakibul Islam & Md Siam <br> BSc in CSE</html>");
        l3.setFont(new Font("Arial", Font.PLAIN, 13));
        l3.setForeground(Color.decode("#eeeee4"));
        l3.setBounds(25, screenH - (screenH / 2) - 150, 700, 200);
        add(l3);

        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#063970"));
        setLayout(null);
        setBounds(0, 0, screenW - screenH, screenH - (screenH / 2));
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            Thread.sleep(2000);
            setVisible(false);
            new Menu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Main();
    }
}
