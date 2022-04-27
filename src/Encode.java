import java.io.File;
import java.nio.file.Path;

public class Encode {
    public static void main(String[] args) {
        int[] test = ByteReader.readBytesAsInt(new File(args[0]));
        for (int i = 0; i < test.length; i++)
            System.out.println(i + " : " + test[i]);










    }







}
