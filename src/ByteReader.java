import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ByteReader {
    public static int[] occurrences;

    public static void main(String[] args) {
        occurrences = new int[255];
        File file = new File("C:\\Users\\mikke\\OneDrive\\Dokumenter\\SDU\\DM507 - Algoritmer og datastrukture\\Projekt\\src\\text2.txt");

        // readBytesAsInt(file);
        // readBytesAsZeroOrOne(file);
        // read4Bytes(file);

        for (int i = 0; i < occurrences.length; i++)
            if (occurrences[i] != 0)
                System.out.println(i + ": " + occurrences[i]);
    }

    private static void readBytesAsInt(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int n = fileInputStream.read();
            while (n != -1) {
                occurrences[n]++;
                n = fileInputStream.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void readBytesAsZeroOrOne(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BitInputStream BIStream = new BitInputStream(fileInputStream);
            int bit = BIStream.readBit();
            while (bit != -1) {
                System.out.println(bit);
                bit = BIStream.readBit();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void read4Bytes(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BitInputStream BIStream = new BitInputStream(fileInputStream);
            int n1 = BIStream.readInt();
            int n2 = BIStream.readInt();
            int n3 = BIStream.readInt();
            int n4 = BIStream.readInt();
            System.out.println("n1: " + n1 + ", " + "n2: " + n2 + ", " + "n3: " + n3 + ", " + "n4: " + n4);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}