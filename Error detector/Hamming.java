import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;

public class Hamming extends Menu {
    JTextField in, in1;
    JButton pb1, pb2;
    JComboBox cb1;
    JLabel ll1;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenW = screenSize.width;
    int screenH = screenSize.height;

    Hamming() {
        super();

        JLabel p1 = new JLabel("Hamming Distance");
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
        cb1.setFont(new Font("Arial", Font.ITALIC, 17));
        cb1.setForeground(Color.darkGray);
        cb1.setBackground(Color.lightGray);
        cb1.setOpaque(true);
        cb1.addActionListener(this);
        cp.add(cb1);

        // Input 1
        JLabel p3 = new JLabel("Input 1 : ");
        p3.setFont(new Font("Arial", Font.BOLD, 17));
        p3.setForeground(Color.DARK_GRAY);
        p3.setBounds(50, 150, 100, 30);
        cp.add(p3);

        in = new JTextField();
        in.setBounds(170, 150, 350, 40);
        in.setFont(new Font("Arial", Font.PLAIN, 17));
        in.setForeground(Color.DARK_GRAY);
        in.setBackground(Color.LIGHT_GRAY);
        cp.add(in);

        // Input 2
        JLabel p4 = new JLabel("Input 2 : ");
        p4.setFont(new Font("Arial", Font.BOLD, 17));
        p4.setForeground(Color.DARK_GRAY);
        p4.setBounds(50, 230, 100, 30);
        cp.add(p4);

        in1 = new JTextField();
        in1.setBounds(170, 230, 350, 40);
        in1.setFont(new Font("Arial", Font.PLAIN, 17));
        in1.setForeground(Color.DARK_GRAY);
        in1.setBackground(Color.LIGHT_GRAY);
        cp.add(in1);

        super.re.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                in.setText(""); 
            }
        });

        super.re.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                in1.setText(""); 
            }
        });

        // --- Add Right-Click Popup Menu ---
        JPopupMenu popup = new JPopupMenu();
        JPopupMenu popup1 = new JPopupMenu();

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

        JMenuItem copyy = new JMenuItem("Copy");
        copyy.addActionListener(e -> in1.copy());
        popup1.add(copyy);

        JMenuItem pastee = new JMenuItem("Paste");
        pastee.addActionListener(e -> in1.paste());
        popup1.add(pastee);

        JMenuItem cutt = new JMenuItem("Cut");
        cutt.addActionListener(e -> in1.cut());
        popup1.add(cutt);
        in1.setComponentPopupMenu(popup1);

        /// Save Button
        pb1 = new JButton("Save");
        pb1.setBounds(170, 310, 170, 35);
        pb1.setFont(new Font("Arial", Font.BOLD, 17));
        pb1.setForeground(Color.GREEN);
        pb1.setBackground(Color.DARK_GRAY);
        pb1.addActionListener(this);
        cp.add(pb1);

        /// Check button
        pb2 = new JButton("Check");
        pb2.setBounds(345, 310, 170, 35);
        pb2.setFont(new Font("Arial", Font.BOLD, 17));
        pb2.setForeground(Color.decode("#063970"));
        pb2.setBackground(Color.LIGHT_GRAY);
        pb2.addActionListener(this);
        cp.add(pb2);
    }

    public void actionPerformed(ActionEvent e) {
        String selected = (String) cb1.getSelectedItem();
        String input = in.getText();
        String input1 = in1.getText();

        // Check
        if (e.getSource() == pb2) {

            String result = checkout(selected, input, input1);

            System.out.println(result);

            if (ll1 != null) {
                super.ot.remove(ll1);
            }

            if (selected.equals("Binary")) {

                if ((!input.isEmpty() && !input1.isEmpty())
                        && (!result.equals("Invalid Input Field 1!") && !result.equals("Invalid Input Field 2!"))) {
                    ll1 = new JLabel(
                            "<html><b>Input Type: </b><i>" + selected + "</i><br><br><b>Input 1 Binary Value: </b><i>"
                                    + input
                                    + "</i><br><br><b>Input 2 Binary Value: </b><i>" + input1
                                    + "</i><br><br><b>Output: </b><i>" + result + "</i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) + 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                } else {
                    ll1 = new JLabel(
                            "<html><b>Input Type: </b><i>" + selected + "</i><br><br><b>Input 1 Binary Value: </b><i>"
                                    + input
                                    + "</i><br><br><b>Input 2 Binary Value: </b><i>" + input1
                                    + "</i><br><br><b>Output: </b><i><font color='red'>" + result
                                    + "</font></i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) + 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();

                }

            } else if (selected.equals("Decimal")) {
                int deci, deci1;
                String bina = null;
                String bina1 = null;

                if ((!input.isEmpty() && !input1.isEmpty())
                        && (!result.equals("Invalid Input Field 1!") && !result.equals("Invalid Input Field 2!"))
                        && !result.equals("Error!: Binary value's must be of equal length.")) {
                    deci = Integer.parseInt(input);
                    deci1 = Integer.parseInt(input1);
                    bina = Integer.toBinaryString(deci);
                    bina1 = Integer.toBinaryString(deci1);

                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input 1 Decimal Value: </b><i>" + input
                            + "</i><br><br><b>Input 2 Decimal Value: </b><i>" + input1
                            + "</i><br><br><b>Binary Value 1: </b><i>" + bina + "</i><br><br><b>Binary Value 2: </b><i>"
                            + bina1 + "</i><br><br><b>Output: </b><i>" + result + "</i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) + 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                } else {
                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input 1 Decimal Value: </b><i>" + input
                            + "</i><br><br><b>Input 2 Decimal Value: </b><i>" + input1
                            + "</i><br><br><b>Binary Value 1: </b><i>" + bina + "</i><br><br><b>Binary Value 2: </b><i>"
                            + bina1 + "</i><br><br><b>Output: </b><font color='red'><i>" + result
                            + "</i></font></html>");

                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) + 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                }
            } else if (selected.equals("Octal")) {
                int deci;
                int deci1;
                String bina = null;
                String bina1 = null;

                if ((!input.isEmpty() && !input1.isEmpty())
                        && (!result.equals("Invalid Input Field 1!") && !result.equals("Invalid Input Field 2!"))
                        && !result.equals("Error!: Binary value's must be of equal length.")) {

                    deci = Integer.parseInt(input, 8);
                    deci1 = Integer.parseInt(input1, 8);
                    bina = Integer.toBinaryString(deci);
                    bina1 = Integer.toBinaryString(deci1);

                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input 1 Octal Value: </b><i>" + input
                            + "</i><br><br><b>Input 2 Octal Value: </b><i>" + input1
                            + "</i><br><br><b>Binary Value 1: </b><i>" + bina + "</i><br><br><b>Binary Value 2: </b><i>"
                            + bina1 + "</i><br><br><b>Output: </b><i>" + result + "</i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) + 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                } else {
                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input 1 Octal Value: </b><i>" + input
                            + "</i><br><br><b>Input 2 Octal Value: </b><i>" + input1
                            + "</i><br><br><b>Binary Value 1: </b><i>" + bina + "</i><br><br><b>Binary Value 2: </b><i>"
                            + bina1 + "</i><br><br><b>Output: </b><font color='red'><i>" + result
                            + "</i></font></html>");

                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) + 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                }
            }

            else if (selected.equals("Hexadecimal")) {
                int deci;
                int deci1;
                String bina = null;
                String bina1 = null;

                if ((!input.isEmpty() && !input1.isEmpty())
                        && (!result.equals("Invalid Input Field 1!") && !result.equals("Invalid Input Field 2!"))
                        && !result.equals("Error!: Binary value's must be of equal length.")) {
                    deci = Integer.parseInt(input, 16);
                    deci1 = Integer.parseInt(input1, 16);
                    bina = Integer.toBinaryString(deci);
                    bina1 = Integer.toBinaryString(deci1);

                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input 1 Hexa Value: </b><i>" + input
                            + "</i><br><br><b>Input 2 Hexa Value: </b><i>" + input1
                            + "</i><br><br><b>Binary Value 1: </b><i>" + bina + "</i><br><br><b>Binary Value 2: </b><i>"
                            + bina1 + "</i><br><br><b>Output: </b><i>" + result + "</i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) + 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                } else {
                    ll1 = new JLabel("<html><b>Input Type: </b><i>" + selected
                            + "</i><br><br><b>Input 1 Hexa Value: </b><i>" + input
                            + "</i><br><br><b>Input 2 Hexa Value: </b><i>" + input1
                            + "</i><br><br><b>Binary Value 1: </b><i>" + bina + "</i><br><br><b>Binary Value 2: </b><i>"
                            + bina1 + "</i><br><br><b>Output: </b><font color='red'><i>" + result
                            + "</i></font></html>");

                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) + 100);
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

                String htmlText = ll1.getText();


                htmlText = htmlText.replaceAll("(?i)<br\\s*/?>", "\n");


                String plainText = htmlText.replaceAll("<[^>]*>", "")
                        .replace("&nbsp;", " ")
                        .replace("&lt;", "<")
                        .replace("&gt;", ">")
                        .replace("&amp;", "&");

                FileWriter writer = new FileWriter(file, true);
                String header = ">>>>>> Hamming Distance <<<<<<<\n";
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
        // History
        else if (e.getSource() == super.his) {
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
    public String checkout(String type, String input, String input1) {

        String Error = "Empty Input value!";

        int[] intArray = new int[input.length()];
        int[] intArray1 = new int[input1.length()];

        for (int i = 0; i < input.length(); i++) {
            intArray[i] = input.charAt(i) - '0';
        }

        for (int i = 0; i < input1.length(); i++) {
            intArray1[i] = input1.charAt(i) - '0';
        }

        switch (type) {

            /// For Binary
            case "Binary":

                if (input.isEmpty()) {
                    return "Please enter the input field 1 before proceeding!";
                }

                if (input1.isEmpty()) {
                    return "Please enter the input field 2 before proceeding!";
                }

                for (int i = 0; i < intArray.length; i++) {
                    if (intArray[i] > 1 || intArray[i] < 0) {
                        Error = "Invalid Input Field 1!";
                        return Error;
                    }
                }

                for (int i = 0; i < intArray1.length; i++) {
                    if (intArray1[i] > 1 || intArray1[i] < 0) {
                        Error = "Invalid Input Field 2!";
                        return Error;
                    }
                }

                int cal = calHD(input, input1);
                if (cal == -1) {
                    Error = "Error!: Binary value's must be of equal length.";
                    return Error;
                } else {
                    String st = Integer.toString(cal);
                    Error = st;
                }
                break;

            /// For Decimal
            case "Decimal":

                if (input.isEmpty()) {
                    return "Please enter the input field 1 before proceeding!";
                }

                if (input1.isEmpty()) {
                    return "Please enter the input field 2 before proceeding!";
                }

                for (int i = 0; i < intArray.length; i++) {
                    if (intArray[i] < 0 || intArray[i] > 9) {
                        Error = "Invalid Input Field 1!";
                        return Error;
                    }
                }

                for (int i = 0; i < intArray1.length; i++) {
                    if (intArray1[i] < 0 || intArray1[i] > 9) {
                        Error = "Invalid Input Field 2!";
                        return Error;
                    }
                }

                int num = Integer.parseInt(input);
                int num1 = Integer.parseInt(input1);
                String binary = Integer.toBinaryString(num);
                String binary1 = Integer.toBinaryString(num1);

                int cal1 = calHD(binary, binary1);

                if (cal1 == -1) {
                    Error = "Error!: Binary value's must be of equal length.";
                    return Error;
                } else {
                    String st = Integer.toString(cal1);
                    Error = st;
                }
                break;

            /// For Octal
            case "Octal":

                if (input.isEmpty()) {
                    return "Please enter the input field 1 before proceeding!";
                }

                if (input1.isEmpty()) {
                    return "Please enter the input field 2 before proceeding!";
                }

                for (int i = 0; i < intArray.length; i++) {
                    if (intArray[i] < 0 || intArray[i] > 7) {
                        Error = "Invalid Input Field 1!";
                        return Error;
                    }
                }

                for (int i = 0; i < intArray1.length; i++) {
                    if (intArray1[i] < 0 || intArray1[i] > 7) {
                        Error = "Invalid Input Field 2!";
                        return Error;
                    }
                }

                int decimal = Integer.parseInt(input, 8);
                int decimal1 = Integer.parseInt(input1, 8);
                String b1 = Integer.toBinaryString(decimal);
                String b2 = Integer.toBinaryString(decimal1);

                int cal2 = calHD(b1, b2);

                if (cal2 == -1) {
                    Error = "Error!: Binary value's must be of equal length.";
                    return Error;
                } else {
                    String st = Integer.toString(cal2);
                    Error = st;
                }
                break;

            /// For hexadecimal
            case "Hexadecimal":

                if (input.isEmpty()) {
                    return "Please enter the input field 1 before proceeding!";
                }

                if (input1.isEmpty()) {
                    return "Please enter the input field 2 before proceeding!";
                }

                if (isValidHex(input)) {

                } else {
                    return "Invalid Input Field 1!";
                }

                if (isValidHex(input1)) {

                } else {
                    return "Invalid Input Field 2!";
                }

                int de = Integer.parseInt(input, 16);
                int de1 = Integer.parseInt(input1, 16);
                String b3 = Integer.toBinaryString(de);
                String b4 = Integer.toBinaryString(de1);

                int cal3 = calHD(b3, b4);

                if (cal3 == -1) {
                    Error = "Error!: Binary value's must be of equal length.";
                    return Error;
                } else {
                    String st = Integer.toString(cal3);
                    Error = st;
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

    /// Calculate hamming distance method
    public int calHD(String binary1, String binary2) {

        // Check if the lengths are equal
        if (binary1.length() != binary2.length()) {
            return -1;
        }

        int distance = 0;

        for (int i = 0; i < binary1.length(); i++) {
            if (binary1.charAt(i) != binary2.charAt(i)) {
                distance++;
            }
        }

        return distance;
    }

}
