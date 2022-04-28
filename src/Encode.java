import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encode {
    public static void main(String[] args) {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        int[] frequencyTable = createFrequencyTableFrom(inputFile);
        String[] huffmanTable = HuffmanTree.createHuffmanTable(frequencyTable);


        writeFile(frequencyTable, inputFile, outputFile);

        //ikke nødvendigt hvis vi bruger den nuværende kode
        /*ByteWriter.writeIntToOutputFile(frequencyTable, outputFile); //the ByteWriter method is probably not correct / broken
        writeEncodedString(encode(inputFile, huffmanTable),outputFile);*/
    }

    // måske omdøb til createBitcodeString eller noget
    public static String encode(File file, String[] huffmanTable) {
        String bitCode = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int n = fileInputStream.read();
            while (n != -1) {
                bitCode += huffmanTable[n];
                n = fileInputStream.read();
            }
            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bitCode;
    }

    // Nok unødvendigt
/*    private  static void writeEncodedString(String encodedString, File outputFile) {
        int[] bitCode = new int[encodedString.length()];
        for (int i = 0; i < bitCode.length; i++)
            bitCode[i] = Character.getNumericValue(encodedString.charAt(i));
        ByteWriter.writeBitToOutputFile(bitCode, outputFile);
    }*/

    private static void writeFile (int[] frequencyTable, File inputFile, File outputFile){
        String[] huffmanTable = HuffmanTree.createHuffmanTable(frequencyTable);
        String bitcode = encode(inputFile, huffmanTable);

        int[] bitCodeInt = new int[bitcode.length()];
        for (int i = 0; i < bitCodeInt.length; i++)
            bitCodeInt[i] = Character.getNumericValue(bitcode.charAt(i));

        try {
            BitOutputStream bitOutputStream = new BitOutputStream(new FileOutputStream(outputFile));
            for (int intToWrite : frequencyTable)
                bitOutputStream.writeInt(intToWrite);

            for (int bitsToWrite : bitCodeInt)
                bitOutputStream.writeBit(bitsToWrite);

            bitOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private static int[] createFrequencyTableFrom(File file) {
        int[] frequencyTable = new int[256];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int n = fileInputStream.read();
            while (n != -1) {
                frequencyTable[n]++;
                n = fileInputStream.read();
            }
            fileInputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return frequencyTable;
    }
}









