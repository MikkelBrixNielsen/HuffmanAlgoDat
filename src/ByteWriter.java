import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteWriter {

    // broken***
    public static void writeIntToOutputFile(int[] toWrite, File outputFile) {
        try {
            BitOutputStream bitOutputStream = new BitOutputStream(new FileOutputStream(outputFile));
            for (int intToWrite : toWrite)
                bitOutputStream.writeInt(intToWrite);
            bitOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // broken***
    public static void writeBitToOutputFile(int[] toWrite, File outputFile) {
        try {
            BitOutputStream bitOutputStream = new BitOutputStream(new FileOutputStream(outputFile));
            for (int bitsToWrite : toWrite)
                try {
                    bitOutputStream.writeBit(bitsToWrite);
                } catch (IllegalArgumentException e) {
                    bitOutputStream.close();
                }
            bitOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
