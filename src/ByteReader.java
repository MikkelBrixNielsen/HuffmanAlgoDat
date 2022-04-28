import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ByteReader {

    // gets not enough bits available exception
    // public static int[] createFrequencyTableFrom(File file) {
    //     int[] frequencyTable = new int[256];
    //     try {
    //         BitInputStream BIStream = new BitInputStream(new FileInputStream(file));
    //         int n = BIStream.readInt();
    //         while (n != -1) {
    //             frequencyTable[n % 256]++;
    //             n = BIStream.readInt();
    //         }
    //     } catch (IOException ex) {
    //         ex.printStackTrace();
    //     }
    //     return frequencyTable;
    // }


    // broken*** bruger ikke bitInputStream
    public static int[] createFrequencyTableFrom(File file) {
        int[] frequencyTable = new int[256];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int n = fileInputStream.read();
            while (n != -1) {
                frequencyTable[n]++;
                n = fileInputStream.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return frequencyTable;
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