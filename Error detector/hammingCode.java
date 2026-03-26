import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;

public class hammingCode extends Menu{

    JTextField in, in1,in2;
    JButton pb1, pb2;
    JLabel ll1;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenW = screenSize.width;
    int screenH = screenSize.height;

    hammingCode()
    {
         super();
        JLabel p1 = new JLabel("Hamming Code");
        p1.setFont(new Font("Arial", Font.BOLD, 18));
        p1.setForeground(Color.white);
        p1.setBackground(Color.DARK_GRAY);
        p1.setHorizontalAlignment(SwingConstants.CENTER);
        p1.setVerticalAlignment(SwingConstants.CENTER);
        p1.setOpaque(true);
        p1.setBounds(0, 0, screenW - 405, 30);
        cp.add(p1);

        // Input 1
        JLabel p3 = new JLabel("Enter the bit stream: ");
        p3.setFont(new Font("Arial", Font.BOLD, 17));
        p3.setForeground(Color.DARK_GRAY);
        p3.setBounds(50, 100, 250, 30);
        cp.add(p3);

        in = new JTextField();
        in.setBounds(340, 100, 450, 40);
        in.setFont(new Font("Arial", Font.PLAIN, 17));
        in.setForeground(Color.DARK_GRAY);
        in.setBackground(Color.LIGHT_GRAY);
        cp.add(in);

        // Input 2
        JLabel p4 = new JLabel("Enter position to induce error: ");
        p4.setFont(new Font("Arial", Font.BOLD, 17));
        p4.setForeground(Color.DARK_GRAY);
        p4.setBounds(50, 180, 250, 30);
        cp.add(p4);

        in1 = new JTextField();
        in1.setBounds(340, 180, 450, 40);
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
        pb1.setBounds(340, 350, 170, 35);
        pb1.setFont(new Font("Arial", Font.BOLD, 17));
        pb1.setForeground(Color.GREEN);
        pb1.setBackground(Color.DARK_GRAY);
        pb1.addActionListener(this);
        cp.add(pb1);

        /// Check button
        pb2 = new JButton("Check");
        pb2.setBounds(500, 350, 170, 35);
        pb2.setFont(new Font("Arial", Font.BOLD, 17));
        pb2.setForeground(Color.decode("#063970"));
        pb2.setBackground(Color.LIGHT_GRAY);
        pb2.addActionListener(this);
        cp.add(pb2);

    }

    public void actionPerformed(ActionEvent e) {

        String input = in.getText();
        String input1 = in1.getText();
    
        devil:

        // Check
        if (e.getSource() == pb2) {

            String result = checkout(input);

            if (ll1 != null) {
                super.ot.remove(ll1);
            }



            if(!input.isEmpty() && !result.equals("Invalid Input Field 1!"))
            {

                if(!input1.isEmpty())
                {

                    if (!input1.isEmpty()) {
                        int[] intArray = new int[input.length()];
        
                        for (int i = 0; i < input.length(); i++) {
                            intArray[i] = input.charAt(i) - '0';
                        }
        
                        int[] hammingCode = calculateParityBits(intArray);
        
                        /// Generated Hamming code
        
                        StringBuilder hammingCodeStr = new StringBuilder();
                        for (int bit : hammingCode) {
                            hammingCodeStr.append(bit);
                        }
        
                        // Optional error simulation
                        int[] corruptedCode = hammingCode.clone();
        
                        int errorPos = Integer.parseInt(input1);
                        StringBuilder hammingCodeStr1 = new StringBuilder();
        
                        if (errorPos > 0 && errorPos <= corruptedCode.length) {
                            corruptedCode[errorPos - 1] ^= 1;
        
                            // Corrupted code:
        
                            for (int bit : corruptedCode) {
                                hammingCodeStr1.append(bit);
                            }
                        }else if(errorPos==0)
                        {
                            for (int bit : corruptedCode) {
                                hammingCodeStr1.append(bit);
                            }
                        }
        
                        // Received code same hammingCodeStr1
        
                        String er = detect(corruptedCode);
        
                        System.out.println();
        
                        // Detected corrupted bit
                        int pos = detectAndCorrect(corruptedCode);
        
                        StringBuilder hammingCodeStr2 = new StringBuilder();
                        for (int bit : hammingCode) {
                            hammingCodeStr2.append(bit);
                        }
        
                        if (er.equals("No error detected")) {
                            ll1 = new JLabel(
                                    "<html><b>Data Stream: </b><i>" + input +
                                            "</i><br><br><b>Input Error Position: </b><i>" + input1 +
                                            "</i><br><br><b>Generated Hamming code: </b><i>" + hammingCodeStr +
                                            "</i><br><br><b>Transmitted data: </b><i>" + hammingCodeStr +
                                            "</i><br><br><b>Error detected at position: </b><i>" + pos +
                                            "</i><br><br><b>Received Data: </b><i>" + hammingCodeStr1 +
                                            "</i><br><br><b>Correction Data: </b><i>" + hammingCodeStr2 +
                                            "</i><br><br><b>Output: </b><i><font color='green'>" + er + "</font></i></html>");
                        } else {
                            ll1 = new JLabel(
                                    "<html><b>Data Stream: </b><i>" + input +
                                            "</i><br><br><b>Input Error Position: </b><i>" + input1 +
                                            "</i><br><br><b>Generated Hamming code: </b><i>" + hammingCodeStr +
                                            "</i><br><br><b>Transmitted data: </b><i>" + hammingCodeStr +
                                            "</i><br><br><b>Error detected at position: </b><i>" + pos +
                                            "</i><br><br><b>Received Data: </b><i>" + hammingCodeStr1 +
                                            "</i><br><br><b>Correction Data: </b><i>" + hammingCodeStr2 +
                                            "</i><br><br><b>Output: </b><i><font color='red'>" + er + "& correction</font></i></html>");
                        }
                        ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                        ll1.setForeground(Color.BLACK);
                        ll1.setBounds(20, 50, screenW - 415, (screenH / 3)+100);
                        ll1.setVerticalAlignment(SwingConstants.TOP);
                        super.ot.add(ll1);
                        super.ot.revalidate();
                        super.ot.repaint();
        
                    } else {
                        String r = "The position must be greater than or equal 1 and less than or equal ";
        
                        int num = (int) Math.pow(2, input.length());
        
                        ll1 = new JLabel(
                                "<html>><i><font color='red'>" + r
                                        + num + "</font></i></html>");
                        ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                        ll1.setForeground(Color.BLACK);
                        ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                        ll1.setVerticalAlignment(SwingConstants.TOP);
                        super.ot.add(ll1);
                        super.ot.revalidate();
                        super.ot.repaint();
        
                        
                    }
                    break devil;
                }


///////////////////////////////

                ll1 = new JLabel(
                    "<html><b>Data Stream: </b><i>" + input +
                            "</i><br><br><b>Input Error Position: </b><i>null</i><br><br><b>Generated Hamming code: </b><i>" + result +"</i></html>");
        
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
            String header = ">>>>>> Hamming Code <<<<<<<\n";
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
       public String checkout(String input) {

        String Error = "Empty Input value!";

        int[] intArray = new int[input.length()];


        for (int i = 0; i < input.length(); i++) {
            intArray[i] = input.charAt(i) - '0';
        }


        if (input.isEmpty()) {
            return "Please enter the input field 1 before proceeding!";
        }



        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] > 1 || intArray[i] < 0) {
                Error = "Invalid Input Field 1!";
                return Error;
            }
        }

        int[] hammingCode = calculateParityBits(intArray);

        StringBuilder hammingCodeStr = new StringBuilder();
        for (int bit : hammingCode) {
            hammingCodeStr.append(bit);
        }
       
        Error=hammingCodeStr.toString();

        return Error;

    }


// Function to calculate parity bits and return Hamming code
    public static int[] calculateParityBits(int[] data) {
        int dataBits = data.length;
        int parityBits = 1;

        while (Math.pow(2, parityBits) < dataBits + parityBits + 1) {
            parityBits++;
        }

        int totalBits = dataBits + parityBits;
        int[] hammingCode = new int[totalBits];

        int j = 0, p = 0;

        // Place data bits and placeholders (-1) for parity bits
        for (int i = 1; i <= totalBits; i++) {
            if (i == Math.pow(2, p)) {
                hammingCode[i - 1] = -1; // placeholder
                p++;
            } else {
                hammingCode[i - 1] = data[j];
                j++;
            }
        }

        // Calculate parity bits
        for (int i = 0; i < parityBits; i++) {
            int position = (int) Math.pow(2, i);
            int parity = 0;

            for (int k = position; k <= totalBits; k++) {
                if ((k & position) == position && hammingCode[k - 1] != -1) {
                    parity ^= hammingCode[k - 1];
                }
            }

            hammingCode[position - 1] = parity;
        }

        return hammingCode;
    }


     // Function to detect and correct error
     public static int detectAndCorrect(int[] receivedCode) {
        int totalBits = receivedCode.length;
        int parityBits = 0;

        // Find number of parity bits
        while (Math.pow(2, parityBits) < totalBits + 1) {
            parityBits++;
        }

        int errorPosition = 0;

        for (int i = 0; i < parityBits; i++) {
            int position = (int) Math.pow(2, i);
            int parity = 0;

            for (int k = position; k <= totalBits; k++) {
                if ((k & position) == position) {
                    parity ^= receivedCode[k - 1];
                }
            }

            errorPosition += parity * position;
        }

        if (errorPosition != 0) {
            System.out.println("Error detected at position: " + errorPosition);
            receivedCode[errorPosition - 1] ^= 1; // Correct the bit
        } else {
            System.out.println("No error detected.");
        }

        return errorPosition;
    }


     // Function to detect and correct error
     public String detect(int[] receivedCode) {

        String er="";

        int totalBits = receivedCode.length;
        int parityBits = 0;

        // Find number of parity bits
        while (Math.pow(2, parityBits) < totalBits + 1) {
            parityBits++;
        }

        int errorPosition = 0;

        for (int i = 0; i < parityBits; i++) {
            int position = (int) Math.pow(2, i);
            int parity = 0;

            for (int k = position; k <= totalBits; k++) {
                if ((k & position) == position) {
                    parity ^= receivedCode[k - 1];
                }
            }

            errorPosition += parity * position;
        }

        if (errorPosition != 0) {
           er="Error Detected!"; // Correct the bit
        } else {
            er="No error detected";
        }

        return er;
    }

}
