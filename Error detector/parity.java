import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;


public class parity extends Menu {

    JTextField in;
    JButton pb1, pb2;
    JComboBox cb1;
    JLabel ll1;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenW = screenSize.width;
    int screenH = screenSize.height;

    public parity() {
        super();

        JLabel p1 = new JLabel("Parity Bit");
        p1.setFont(new Font("Arial", Font.BOLD, 18));
        p1.setForeground(Color.white);
        p1.setBackground(Color.DARK_GRAY);
        p1.setHorizontalAlignment(SwingConstants.CENTER);
        p1.setVerticalAlignment(SwingConstants.CENTER);
        p1.setOpaque(true);
        p1.setBounds(0, 0, screenW - 405, 30);
        cp.add(p1);

        JLabel p2 = new JLabel("Input Type: ");
        p2.setFont(new Font("Arial", Font.BOLD, 17));
        p2.setForeground(Color.DARK_GRAY);
        p2.setBounds(50, 70, 100, 30);
        cp.add(p2);

        String str[] = { "Decimal", "Binary", "Octal", "Hexadecimal" };
        cb1 = new JComboBox(str);
        cb1.setBounds(170, 70, 350, 40);
        cb1.setFont(new Font("Arial", Font.ITALIC, 15));
        cb1.setForeground(Color.darkGray);
        cb1.setBackground(Color.lightGray);
        cb1.setOpaque(true);
        cb1.addActionListener(this);
        cp.add(cb1);

        JLabel p3 = new JLabel("Input: ");
        p3.setFont(new Font("Arial", Font.BOLD, 17));
        p3.setForeground(Color.DARK_GRAY);
        p3.setBounds(50, 150, 100, 30);
        cp.add(p3);

        in = new JTextField();
        in.setBounds(170, 150, 350, 40);
        in.setFont(new Font("Arial", Font.PLAIN, 15));
        in.setForeground(Color.DARK_GRAY);
        in.setBackground(Color.LIGHT_GRAY);
        cp.add(in);

        super.re.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                in.setText(""); 
            }
        });
        
        // --- Add Right-Click Popup Menu ---
        JPopupMenu popup = new JPopupMenu();
        
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(e -> in.copy());
        popup.add(copy);
        
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(e -> in.paste());
        popup.add(paste);
        
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(e -> in.cut());
        popup.add(cut);
        
        in.setComponentPopupMenu(popup);  
        

        pb1 = new JButton("Save");
        pb1.setBounds(170, 250, 170, 35);
        pb1.setFont(new Font("Arial", Font.BOLD, 17));
        pb1.setForeground(Color.GREEN);
        pb1.setBackground(Color.DARK_GRAY);
        pb1.addActionListener(this);
        cp.add(pb1);

        pb2 = new JButton("Check");
        pb2.setBounds(345, 250, 170, 35);
        pb2.setFont(new Font("Arial", Font.BOLD, 17));
        pb2.setForeground(Color.decode("#063970"));
        pb2.setBackground(Color.LIGHT_GRAY);
        pb2.addActionListener(this);
        cp.add(pb2);

        /// Output Information Checkout

    }

    public void actionPerformed(ActionEvent e) {

        String selected = (String) cb1.getSelectedItem();
        String input = in.getText();

        if (e.getSource() == pb2) {

            String result = checkout(selected, input);

            System.out.println(result);

            if (ll1 != null) {
                super.ot.remove(ll1);
            }

            if (selected.equals("Binary")) {

                if(!input.isEmpty() && !result.equals("Invalid Input!"))
                {
                    ll1 = new JLabel(
                        "<html><b>Input Type: </b><i>" + selected + "</i><br><br><b>Input Binary Value: </b><i>" + input
                                + "</i><br><br><b>Output: </b><i>" + result + "</i></html>");
                ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                ll1.setForeground(Color.BLACK);
                ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                ll1.setVerticalAlignment(SwingConstants.TOP);
                super.ot.add(ll1);
                super.ot.revalidate();
                super.ot.repaint();
                }
                else
                {
                    ll1 = new JLabel(
                        "<html><b>Input Type: </b><i>" + selected + "</i><br><br><b>Input Binary Value: </b><i>" + input
                                + "</i><br><br><b>Output: </b><i><font color='red'>" + result + "</font></i></html>");
                ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                ll1.setForeground(Color.BLACK);
                ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                ll1.setVerticalAlignment(SwingConstants.TOP);
                super.ot.add(ll1);
                super.ot.revalidate();
                super.ot.repaint();

                }


            } else if (selected.equals("Decimal")) {
                int deci;
                String bina = null;

                if (!input.isEmpty() && !result.equals("Invalid Input!")) {
                    deci = Integer.parseInt(input);
                    bina = Integer.toBinaryString(deci);

                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input Decimal Value: </b><i>" + input
                            + "</i><br><br><b>Binary Value: </b><i>"
                            + bina + "</i><br><br><b>Output: </b><i>" + result + "</i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                } else {
                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input Decimal Value: </b><i>" + input
                            + "</i><br><br><b>Binary Value: </b><i>"
                            + bina + "</i><br><br><b>Output: </b><i><font color='red'>" + result
                            + "</font></i></html>");

                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                }
            }
            else if (selected.equals("Octal")) {
                int deci;
                String bina = null;

                if (!input.isEmpty() && !result.equals("Invalid Input!")) {
                    deci = Integer.parseInt(input,8);
                    bina = Integer.toBinaryString(deci);

                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input Octal Value: </b><i>" + input
                            + "</i><br><br><b>Binary Value: </b><i>"
                            + bina + "</i><br><br><b>Output: </b><i>" + result + "</i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                } else {
                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input Octal Value: </b><i>" + input
                            + "</i><br><br><b>Binary Value: </b><i>"
                            + bina + "</i><br><br><b>Output: </b><i><font color='red'>" + result
                            + "</font></i></html>");

                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                }
            }
            else if (selected.equals("Hexadecimal")) {
                int deci;
                String bina = null;

                if (!input.isEmpty() && !result.equals("Invalid Input!")) {
                    deci = Integer.parseInt(input,16);
                    bina = Integer.toBinaryString(deci);

                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input Hexa Value: </b><i>" + input
                            + "</i><br><br><b>Binary Value: </b><i>"
                            + bina + "</i><br><br><b>Output: </b><i>" + result + "</i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                } else {
                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input Hexa Value: </b><i>" + input
                            + "</i><br><br><b>Binary Value: </b><i>"
                            + bina + "</i><br><br><b>Output: </b><i><font color='red'>" + result
                            + "</font></i></html>");

                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                }
            }

        }

        // Save Button
        else if (e.getSource() == pb1) {
            File file = new File("TextFile/History.txt");
            try {
                // Get HTML text from JLabel
                String htmlText = ll1.getText();

                // Replace <br> with single newlines
                htmlText = htmlText.replaceAll("(?i)<br\\s*/?>", "\n");

                // Strip HTML tags and decode HTML entities
                String plainText = htmlText.replaceAll("<[^>]*>", "")
                        .replace("&nbsp;", " ")
                        .replace("&lt;", "<")
                        .replace("&gt;", ">")
                        .replace("&amp;", "&");

                // Write to file in append mode
                FileWriter writer = new FileWriter(file, true);
                String header = ">>>>>> Parity Bit <<<<<<<\n";
                writer.write("\n" + header);
                writer.write(plainText.trim() + "\n\n");
                writer.close();

                System.out.println(plainText);
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
        // Parity Bit
        else if (e.getSource() == super.b1) {
            setVisible(false);
            new parity();
        }
        // Hamming Distance
        else if (e.getSource() == super.b2) {
            new Hamming();
            setVisible(false);
        }
        // Bit
        else if (e.getSource() == super.b3) {
            setVisible(false);
            new bit();
        }
        // Character Stuffing and Destuffing
        else if (e.getSource() == super.b4) {
            new Cstuf();
            setVisible(false);
        }
        // CRC
        else if (e.getSource() == super.b0) {
            setVisible(false);
            new crc();
        }
        //History
        else if(e.getSource()==super.his)
        {    
            new History();
        }

        // Minimize action
        else if (e.getSource() == super.m) {
            setState(JFrame.ICONIFIED);
        }
        // Exit action
        else if (e.getSource() == super.b5) {
            setVisible(false);
            System.exit(0);
        }
        else if(e.getSource()==b6)
        {
            new hammingCode();
            setVisible(false);
        }
    }

    // Main Logic
    public String checkout(String type, String input) {

        String Error = "Empty Input value!";

        int[] intArray = new int[input.length()];

        for (int i = 0; i < input.length(); i++) {
            intArray[i] = input.charAt(i) - '0'; 
        }
        switch (type) {

            /// For Binary
            case "Binary":

                if (input.isEmpty()) {
                    return "Please enter the input before proceeding!";
                }
                int count = 0;

                for (int i = 0; i < intArray.length; i++) {
                    if (intArray[i] > 1 || intArray[i] < 0) {
                        Error = "Invalid Input!";
                        return Error;
                    }
                }

                for (int i = 0; i < intArray.length; i++) {
                    if (intArray[i] == 1) {
                        count++;
                    }
                }

                if (count % 2 == 0) {
                    Error = " Even Parity";
                } else {
                    Error = " Odd Parity";
                }
                break;

            /// For Decimal
            case "Decimal":

                if (input.isEmpty()) {
                    return "Please enter the input before proceeding!";
                }

                for (int i = 0; i < intArray.length; i++) {
                    if (intArray[i] < 0 || intArray[i] > 9) {
                        Error = "Invalid Input!";
                        return Error;
                    }
                }

                int num = Integer.parseInt(input);
                String binary = Integer.toBinaryString(num);

                int count2 = 0;
                for (int i = 0; i < binary.length(); i++) {
                    if (binary.charAt(i) == '1') {
                        count2++;
                    }
                }

                if (count2 % 2 == 0) {
                    Error = "Even Parity";

                } else {
                    Error = "Odd Parity";

                }
                break;

                /// For Octal
                case "Octal":
                if (input.isEmpty()) {
                    return "Please enter the input before proceeding!";
                }

                for (int i = 0; i < intArray.length; i++) {
                    if (intArray[i] < 0 || intArray[i] > 7) {
                        Error = "Invalid Input!";
                        return Error;
                    }
                }

                int decimal = Integer.parseInt(input, 8);
                String b1 = Integer.toBinaryString(decimal);

                int count3 = 0;
                for (int i = 0; i < b1.length(); i++) {
                    if (b1.charAt(i) == '1') {
                        count3++;
                    }
                }

                if (count3 % 2 == 0) {
                    Error = "Even Parity";

                } else {
                    Error = "Odd Parity";

                }
                break;

            /// For hexadecimal
             case "Hexadecimal":
                 
             if (input.isEmpty()) {
                return "Please enter the input before proceeding!";
            }

            if (isValidHex(input)) {
                
            } else {
                return "Invalid Input!";
            }
            
            int de = Integer.parseInt(input, 16); 
            String b2= Integer.toBinaryString(de);

            int count4 = 0;
            for (int i = 0; i < b2.length(); i++) {
                if (b2.charAt(i) == '1') {
                    count4++;
                }
            }

            if (count4 % 2 == 0) {
                Error = "Even Parity";

            } else {
                Error = "Odd Parity";

            }           
             break;

            default:
                break;
        }

        return Error;
    }

    public boolean isValidHex(String hex) {
        String regex = "^[0-9A-Fa-f]+$";
        return hex.matches(regex);
    }


}
