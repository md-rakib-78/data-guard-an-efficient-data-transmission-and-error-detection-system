import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class History extends JFrame implements ActionListener {

    JButton ex, dh, re;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenW = screenSize.width;
    int screenH = screenSize.height;

    History() {
        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        historyArea.setBackground(Color.decode("#063970"));
        historyArea.setForeground(Color.WHITE);
        historyArea.setLineWrap(true); // Optional: wrap long lines
        historyArea.setWrapStyleWord(true);

        // Enable right-click copy menu (optional)
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(e -> historyArea.copy());
        popupMenu.add(copyItem);
        historyArea.setComponentPopupMenu(popupMenu);

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBounds(20, 70, screenW - screenH - 50, screenH - (screenH / 2) - 150);
        add(scrollPane);

        File file = new File("TextFile/History.txt");
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                reader.close();

                if (sb.toString().equals("")) {
                    historyArea.setText("No history found.");
                } else {
                    historyArea.setText(sb.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            historyArea.setText("No history file found.");
        }

        // Frame dimensions
        int frameWidth = screenW - screenH;
        int frameHeight = screenH - (screenH / 2);
        setBounds(0, 0, frameWidth, frameHeight);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBackground(Color.WHITE);
        topPanel.setBounds(0, 0, frameWidth, 40);
        add(topPanel);

        JLabel title = new JLabel("History", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        title.setForeground(Color.decode("#063970"));
        title.setBounds(0, 5, frameWidth, 30);
        topPanel.add(title);

        // Minimize button (positioned just before the Close button)
        JButton minimize = new JButton("-");
        minimize.setFont(new Font("Tahoma", Font.BOLD, 18));
        minimize.setBounds(frameWidth - 110, 5, 50, 30);
        minimize.setBackground(Color.white);
        minimize.setForeground(Color.BLUE);
        minimize.setOpaque(true);
        minimize.setBorderPainted(true);
        minimize.setFocusPainted(true);
        minimize.setContentAreaFilled(true);
        minimize.addActionListener(e -> setState(JFrame.ICONIFIED));
        topPanel.add(minimize);

        // Close button
        JButton ex = new JButton("X");
        ex.setFont(new Font("Tahoma", Font.BOLD, 16));
        ex.setBounds(frameWidth - 55, 5, 50, 30);
        ex.setBackground(Color.white);
        ex.setForeground(Color.RED);
        ex.setOpaque(true);
        ex.setBorderPainted(true);
        ex.setFocusPainted(true);
        ex.setContentAreaFilled(true);
        ex.addActionListener(e -> dispose());
        topPanel.add(ex);

        re = new JButton("Refresh");
        re.setFont(new Font("Tahoma", Font.BOLD, 14));
        re.setBounds(frameWidth - 350, frameHeight - 45, 150, 30);
        re.setBackground(Color.white);
        re.setForeground(Color.GRAY);
        re.setOpaque(true);
        re.setBorderPainted(true);
        re.setFocusPainted(true);
        re.setContentAreaFilled(true);
        re.addActionListener(this);
        add(re);

        dh = new JButton("Delete History");
        dh.setFont(new Font("Tahoma", Font.BOLD, 14));
        dh.setBounds(frameWidth - 190, frameHeight - 45, 170, 30);
        dh.setBackground(Color.white);
        dh.setForeground(Color.RED);
        dh.setOpaque(true);
        dh.setBorderPainted(true);
        dh.setFocusPainted(true);
        dh.setContentAreaFilled(true);
        dh.addActionListener(this);
        add(dh);

        // Frame setup
        setUndecorated(true);
        setTitle("History");
        getContentPane().setBackground(Color.decode("#063970"));
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        // Close tab
        if (e.getSource() == ex) {
            setVisible(false);
            System.exit(0);
        }
        // Delete History
        else if (e.getSource() == dh) {
            try {
                FileWriter writer = new FileWriter("TextFile/History.txt", false);
                writer.write("");
                writer.close();
                System.out.println("History file cleared.");
            } catch (IOException a) {
                a.printStackTrace();
            }
            setVisible(false);
            new History();
        }

        // Refresh
        else if (e.getSource() == re) {
            setVisible(false);
            new History();
        }

    }

}
