import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Cstuf extends Menu {

    JTextField in, in1, in2;
    JButton pb1, pb2;
    JComboBox cb1;
    JLabel ll1;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenW = screenSize.width;
    int screenH = screenSize.height;

    Cstuf()
    {
        super();
        JLabel p1 = new JLabel("Character Stuffing & Destuffing");
        p1.setFont(new Font("Arial", Font.BOLD, 18));
        p1.setForeground(Color.white);
        p1.setBackground(Color.DARK_GRAY);
        p1.setHorizontalAlignment(SwingConstants.CENTER);
        p1.setVerticalAlignment(SwingConstants.CENTER);
        p1.setOpaque(true);
        p1.setBounds(0, 0, screenW - 405, 30);
        cp.add(p1);

        JLabel p2 = new JLabel("Select Option: ");
        p2.setFont(new Font("Arial", Font.BOLD, 17));
        p2.setForeground(Color.DARK_GRAY);
        p2.setBounds(50, 70, 150, 30);
        cp.add(p2);

        String str[] = { "Character Stuffing", "Character Destuffing" };
        cb1 = new JComboBox(str);
        cb1.setBounds(220, 70, 350, 40);
        cb1.setFont(new Font("Arial", Font.ITALIC, 15));
        cb1.setForeground(Color.darkGray);
        cb1.setBackground(Color.lightGray);
        cb1.setOpaque(true);
        cb1.addActionListener(this);
        cp.add(cb1);

        // Input 1
        JLabel p3 = new JLabel("Input Character: ");
        p3.setFont(new Font("Arial", Font.BOLD, 17));
        p3.setForeground(Color.DARK_GRAY);
        p3.setBounds(50, 150, 150, 30);
        cp.add(p3);

        in = new JTextField();
        in.setBounds(220, 150, 350, 40);
        in.setFont(new Font("Arial", Font.PLAIN, 17));
        in.setForeground(Color.DARK_GRAY);
        in.setBackground(Color.LIGHT_GRAY);
        cp.add(in);

        // Input 2
        JLabel p4 = new JLabel("Input Flag : ");
        p4.setFont(new Font("Arial", Font.BOLD, 17));
        p4.setForeground(Color.DARK_GRAY);
        p4.setBounds(50, 230, 100, 30);
        cp.add(p4);

        in1 = new JTextField();
        in1.setBounds(220, 230, 350, 40);
        in1.setFont(new Font("Arial", Font.PLAIN, 17));
        in1.setForeground(Color.DARK_GRAY);
        in1.setBackground(Color.LIGHT_GRAY);
        cp.add(in1);

        // Input 3
        JLabel p5 = new JLabel("Escape character: ");
        p5.setFont(new Font("Arial", Font.BOLD, 17));
        p5.setForeground(Color.DARK_GRAY);
        p5.setBounds(620, 230, 190, 30);
        cp.add(p5);

        in2 = new JTextField();
        in2.setBounds(810, 230, 250, 40);
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
        pb1.setBounds(220, 310, 170, 35);
        pb1.setFont(new Font("Arial", Font.BOLD, 17));
        pb1.setForeground(Color.GREEN);
        pb1.setBackground(Color.DARK_GRAY);
        pb1.addActionListener(this);
        cp.add(pb1);

        /// Check button
        pb2 = new JButton("Check");
        pb2.setBounds(395, 310, 170, 35);
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
        String input3 = in2.getText();

        String line1 = "";
        String line2 = "";
        String line3 = "";

        try {
            File file = new File("TextFile/char.txt");
            Scanner inp = new Scanner(file);

            if (inp.hasNextLine()) {
                line1 = inp.nextLine();
            }

            if (inp.hasNextLine()) {
                line2 = inp.nextLine();
            }
            if (inp.hasNextLine()) {
                line3 = inp.nextLine();
            }

            inp.close();

        } catch (Exception a) {
            System.out.println("Error: " + a.getMessage());
        }

///// Check
        if (e.getSource() == pb2) {

            if (ll1 != null) {
                super.ot.remove(ll1);
            }

            if (selected.equals("Character Stuffing")) {

                String result = checkout(selected, input, input1, input3);

                if (!input.isEmpty()&&!input3.isEmpty()&&!input1.isEmpty()) {
                   
                    String framedData = frameData(result, input1);

                    try {
                        FileWriter writer = new FileWriter("TextFile/char.txt");

                        // Writing two lines
                        writer.write(result + "\n");
                        writer.write(input1 + "\n");
                        writer.write(input3 + "\n");

                        writer.close();

                        System.out.println("Data written successfully to Bit.txt!");

                    } catch (IOException r) {
                        System.out.println("Error writing to file: " + r.getMessage());
                    }

                    ll1 = new JLabel(
                            "<html><b>Selected Option: </b><i>" + selected + "</i><br><br><b>Original Data: </b><i>"
                                    + input
                                    + "</i><br><br><b>Flag: </b><i>" + input1 + "</i><br><br><b>Escape: </b><i>" + input3 + "</i><br><br><b>Stuffed Data: </b><i>"
                                    + result + "</i><br><br><b>Framed Data: </b><i>" + framedData + "</i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 10, screenW - 415, screenH / 3);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                } else {
                    ll1 = new JLabel(
                            "<html><i><font color='red'>" + result + "</font></i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 50, screenW - 415, (screenH / 3) - 100);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();

                }
            } else if (selected.equals("Character Destuffing")) {

                String result = "";

                if (input.isEmpty() && !line1.isEmpty()) {

                    input = line1;
                    input1 = line2;
                    input3 = line3;

                    result = checkout(selected, input, input1, input3);
                } else {
                    result = checkout(selected, input, input1, input3);
                }

                if (!input.isEmpty() && !input1.isEmpty() && !input3.isEmpty()) {

                //String framedData = frameData(result, input1);

                    ll1 = new JLabel(
                            "<html><b>Selected Option: </b><i>" + selected
                                    + "</i><br><br><b>Received Stuffed Data: </b><i>" + input
                                    + "</i><br><br><b>Flag: </b><i>" + input1 + "</i><br><br><b>Escape: </b><i>" + input3 + "</i><br><br><b>Destuffed Data: </b><i>"
                                    + result + "</i></html>");
                    ll1.setFont(new Font("Arial", Font.PLAIN, 18));
                    ll1.setForeground(Color.BLACK);
                    ll1.setBounds(20, 10, screenW - 415, screenH / 3);
                    ll1.setVerticalAlignment(SwingConstants.TOP);
                    super.ot.add(ll1);
                    super.ot.revalidate();
                    super.ot.repaint();
                } else {
                    ll1 = new JLabel(
                            "<html><i><font color='red'>" + result + "</font></i></html>");
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
    
                    // Write to file in append mode
                    FileWriter writer = new FileWriter(file, true);
                    String header = ">>>>>> " + selected + " <<<<<<<\n";
                    writer.write("\n" + header);
                    writer.write(plainText.trim() + "\n\n");
                    writer.close();
    
                    System.out.println(plainText);
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
            else if(e.getSource()==b6)
            {
                new hammingCode();
                setVisible(false);
            }
    
    }



    public String checkout(String type, String input, String input1, String input2) {

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
            case "Character Stuffing":

                if (input.isEmpty()) {
                    return "Please enter the input string before proceeding!";
                }
                if (input1.isEmpty()) {
                    return "Please enter the input flag character before proceeding!";
                }

                if (input2.isEmpty()) {
                    return "Please enter the input escape character before proceeding!";
                }

                String stuffedData = stuffData(input,input1,input2);
                Error = stuffedData;

                break;

            /// For Decimal
            case "Character Destuffing":

            if (input.isEmpty()) {
                return "Please enter the input string before proceeding!";
            }
            if (input1.isEmpty()) {
                return "Please enter the input flag character before proceeding!";
            }

            if (input2.isEmpty()) {
                return "Please enter the input escape character before proceeding!";
            }


                 String destuffedData = destuff(input, input1,input2);
                 Error = destuffedData;
                break;

            default:
                break;
        }

        return Error;
    }

// Method to perform character stuffing
public static String stuffData(String data, String flag, String esc) {
    String stuffed = data;


    stuffed = stuffed.replace(esc, esc + esc);


    stuffed = stuffed.replace(flag, esc + flag);

    return stuffed;
}

// Method to perform character destuffing
public static String destuff(String stuffedData, String flag, String escape) {


    String destuffed = stuffedData;


    destuffed = destuffed.replace(escape+escape,escape);


    destuffed = destuffed.replace(escape+flag,flag);

    return destuffed;

}



public static String frameData(String stuffedData, String flag) {
    return flag + stuffedData + flag;
}


}
