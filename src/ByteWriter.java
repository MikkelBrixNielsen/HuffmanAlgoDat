import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteWriter {

    public static void writeStringToOutputFile(String[] toWrite, String outputFile) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            fileOutputStream.write((toWrite[0]).getBytes());
            for (int i = 1; i < toWrite.length; i++)
                fileOutputStream.write(("\n" + toWrite[i]).getBytes());
            fileOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
