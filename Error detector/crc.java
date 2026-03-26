import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;

public class crc extends Menu {
    JTextField in, in1,in2;
    JButton pb1, pb2;
    JLabel ll1;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenW = screenSize.width;
    int screenH = screenSize.height;
    

    crc() {
        super();

        JLabel p1 = new JLabel("Cyclic Redundancy Check (CRC)");
        p1.setFont(new Font("Arial", Font.BOLD, 18));
        p1.setForeground(Color.white);
        p1.setBackground(Color.DARK_GRAY);
        p1.setHorizontalAlignment(SwingConstants.CENTER);
        p1.setVerticalAlignment(SwingConstants.CENTER);
        p1.setOpaque(true);
        p1.setBounds(0, 0, screenW - 405, 30);
        cp.add(p1);

        // Input 1
        JLabel p3 = new JLabel("Input Binary String : ");
        p3.setFont(new Font("Arial", Font.BOLD, 17));
        p3.setForeground(Color.DARK_GRAY);
        p3.setBounds(50, 100, 200, 30);
        cp.add(p3);

        in = new JTextField();
        in.setBounds(270, 100, 450, 40);
        in.setFont(new Font("Arial", Font.PLAIN, 17));
        in.setForeground(Color.DARK_GRAY);
        in.setBackground(Color.LIGHT_GRAY);
        cp.add(in);


        // Input 2
        JLabel p4 = new JLabel("Input Divisor : ");
        p4.setFont(new Font("Arial", Font.BOLD, 17));
        p4.setForeground(Color.DARK_GRAY);
        p4.setBounds(50, 180, 200, 30);
        cp.add(p4);

        in1 = new JTextField();
        in1.setBounds(270, 180, 450, 40);
        in1.setFont(new Font("Arial", Font.PLAIN, 17));
        in1.setForeground(Color.DARK_GRAY);
        in1.setBackground(Color.LIGHT_GRAY);
        cp.add(in1);


        // Input 3
        JLabel p5 = new JLabel("Input Error Position: ");
        p5.setFont(new Font("Arial", Font.BOLD, 17));
        p5.setForeground(Color.DARK_GRAY);
        p5.setBounds(50, 260, 200, 30);
        cp.add(p5);

        in2 = new JTextField();
        in2.setBounds(270, 260, 450, 40);
        in2.setFont(new Font("Arial", Font.PLAIN, 17));
        in2.setForeground(Color.DARK_GRAY);
        in2.setBackground(Color.LIGHT_GRAY);
        cp.add(in2);


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

        super.re.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                in2.setText("");
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
        pb1.setBounds(270, 350, 170, 35);
        pb1.setFont(new Font("Arial", Font.BOLD, 17));
        pb1.setForeground(Color.GREEN);
        pb1.setBackground(Color.DARK_GRAY);
        pb1.addActionListener(this);
        cp.add(pb1);

        /// Check button
        pb2 = new JButton("Check");
        pb2.setBounds(445, 350, 170, 35);
        pb2.setFont(new Font("Arial", Font.BOLD, 17));
        pb2.setForeground(Color.decode("#063970"));
        pb2.setBackground(Color.LIGHT_GRAY);
        pb2.addActionListener(this);
        cp.add(pb2);

    }

    public void actionPerformed(ActionEvent e) {

        String input = in.getText();
        String input1 = in1.getText();
        String input2 = in2.getText();

        outer:


        // Check
        if (e.getSource() == pb2) {

            String result = checkout(input, input1);

            if (ll1 != null) {
                super.ot.remove(ll1);
            }


            if(!input2.isEmpty() && !input1.isEmpty() &&!input.isEmpty())
            {

                int pos=input.length();
                int ero=Integer.parseInt(input2);

                System.out.println("length: "+pos);
                System.out.println("length: "+ero);

                if(pos<ero || ero<1)
                {
                    String r="The position must be greater than or equal 1 and less than or equal ";

    
                    ll1 = new JLabel(
                            "<html>><i><font color='red'>" + r
                                    + pos+"</font></i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3)+100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();    
                    
                    break outer;
                    
                }

            }


            if ((!input.isEmpty() && !input1.isEmpty())
                    && (!result.equals("Invalid Input Field 1!") && !result.equals("Invalid Input Field 2!"))) {

                        String er="";
                        String receivedData="";

                // Sending data with CRC
                String transmittedData = input + result;
                System.out.println("Transmitted Data: " + transmittedData);

                if(!input2.isEmpty())
                {
                    int ero=Integer.parseInt(input2);

                    char[] corruptedData = transmittedData.toCharArray();
                    corruptedData[ero-1] = (corruptedData[ero-1] == '1') ? '0' : '1'; 
                    receivedData = new String(corruptedData);
                    System.out.println("Received Data (with error): " + receivedData);

                    // Receiver side check
                    String checkCRC = calculateCRC(receivedData, input1);

                    if (checkCRC.equals("0".repeat(input1.length() - 1))) {

                        er = "No Error Detected in received data!";

                    } else {
                        er = "Error Detected in Received Data!";
                    }

                }
                else
                {
                    // Receiver side check
                    receivedData=transmittedData;

                    System.out.println("rakib");

                    String checkCRC = calculateCRC(transmittedData, input1);
                    if (checkCRC.equals("0".repeat(input1.length() - 1))) {

                        er = "No Error Detected in received data!";

                    } else {

                        er = "Error Detected in Received Data!";
                    }

                }


                if (er.equals("No Error Detected in received data!")) {
                    ll1 = new JLabel(
                        "<html><b>Data: </b><i>" + input +
                        "</i><br><br><b>Divisor: </b><i>" + input1 +
                        "</i><br><br><b>CRC: </b><i>" + result +
                        "</i><br><br><b>Transmitted data: </b><i>" + transmittedData +
                        "</i><br><br><b>Received Data: </b><i>" + receivedData +"</i><br><br><b>Output: </b><i><font color='green'>" + er + "</font></i></html>"
                    );
                } else {

                    int ero1=Integer.parseInt(input2);
                    char[] correctionData = receivedData.toCharArray();
                    correctionData[ero1-1] = (correctionData[ero1-1] == '1') ? '0' : '1'; 
                    String received = new String(correctionData);

                    ll1 = new JLabel(
                        "<html><b>Data: </b><i>" + input +
                        "</i><br><br><b>Divisor: </b><i>" + input1 +
                        "</i><br><br><b>CRC: </b><i>" + result +
                        "</i><br><br><b>Transmitted data: </b><i>" + transmittedData +
                        "</i><br><br><b>Received Data (with error): </b><i>" + receivedData +
                        "</i><br><br><b>Correction Data: </b><i>" + received +
                        "</i><br><br><b>Output: </b><i><font color='red'>" + er + " at position ("+ero1+")</font></i></html>"
                    );
                }

                ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                ll1.setForeground(Color.BLACK);
                ll1.setBounds(20, 50, screenW - 415, (screenH / 3)+100);
                ll1.setVerticalAlignment(SwingConstants.TOP);
                super.ot.add(ll1);
                super.ot.revalidate();
                super.ot.repaint();
            } else {
                ll1 = new JLabel(
                        "<html>><i><font color='red'>" + result
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
            String header = ">>>>>> Cyclic Redundancy Check (CRC) <<<<<<<\n";
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
        setVisible(false);
        new Cstuf();
    }
    //CRC
    else if(e.getSource()==super.b0)
    {
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
    else if(e.getSource()==super.b6)
    {
        new hammingCode();
        setVisible(false);
    }

    }

    // Main Logic
    public String checkout(String input, String input1) {

        String Error = "Empty Input value!";

        int[] intArray = new int[input.length()];
        int[] intArray1 = new int[input1.length()];

        for (int i = 0; i < input.length(); i++) {
            intArray[i] = input.charAt(i) - '0';
        }

        for (int i = 0; i < input1.length(); i++) {
            intArray1[i] = input1.charAt(i) - '0';
        }

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

        String crc = calculateCRC(input, input1);
        Error = crc;

        System.out.println("Crc: "+crc);
        return Error;

    }

    // Method to perform CRC division
    public static String calculateCRC(String data, String divisor) {
        int dataLength = data.length();
        int divisorLength = divisor.length();
        

        String dividend = data + "0".repeat(divisorLength - 1);
        char[] remainder = dividend.toCharArray();

        for (int i = 0; i <= dividend.length() - divisorLength; i++) {
            if (remainder[i] == '1') {
                for (int j = 0; j < divisorLength; j++) {
                    remainder[i + j] = xor(remainder[i + j], divisor.charAt(j));
                }
            }
        }

        return new String(remainder).substring(dataLength);
    }

    // XOR operation between two characters
    private static char xor(char a, char b) {
        return (a == b) ? '0' : '1';
    }

    public static void main(String[] args) {
        new crc();
    }
}
