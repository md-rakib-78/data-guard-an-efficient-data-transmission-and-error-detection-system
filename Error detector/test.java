import java.util.Scanner;

public class test {

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the bit stream (e.g., 1010110): ");
        String bitStream = sc.nextLine().trim();

        int[] data = new int[bitStream.length()];
        for (int i = 0; i < bitStream.length(); i++) {
            data[i] = bitStream.charAt(i) - '0';
        }

        int[] hammingCode = calculateParityBits(data);

        System.out.print("Generated Hamming code: ");
        for (int bit : hammingCode) {
            System.out.print(bit + " ");
        }
        System.out.println();




        
        // Optional error simulation
        int[] corruptedCode = hammingCode.clone();
        System.out.print("Enter position to induce error (0 for no error): ");
        int errorPos = sc.nextInt();

        if (errorPos > 0 && errorPos <= corruptedCode.length) {
            corruptedCode[errorPos - 1] ^= 1;
            System.out.print("Corrupted code: ");
            for (int bit : corruptedCode) {
                System.out.print(bit + " ");
            }
            System.out.println();
        }

        System.out.print("Received code: ");
        for (int bit : corruptedCode) {
            System.out.print(bit + " ");
        }
        System.out.println();

        detectAndCorrect(corruptedCode);

        System.out.print("Corrected code: ");
        for (int bit : corruptedCode) {
            System.out.print(bit + " ");
        }
        System.out.println();

        sc.close();
    }
}
