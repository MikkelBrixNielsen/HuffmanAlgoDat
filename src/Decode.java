//Mikkel Brix Nielsen (mikke21)
//Nicolai Larsen (dalar21)
//Steffen Bach (stbac21)

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decode {
    public static void main(String[] args) {
        decode(new File(args[0]), new File(args[1]));
    }

    /**
     * Decodes an already encoded file that has been encoded with the Encode.java
     * @param inputFile the file which to decode
     * @param outputFile the file which to write the decoded data to
     */
    private static void decode(File inputFile, File outputFile) {
        // Variable that holds the total amount of characters
        // that needs to be written to eliminate the potential
        // occurrence of zero padding when encoding
        int numberOfCharacters = 0;
        // Variable to hold the frequency table read from the encoded file
        int[] frequencyTable = new int[256];

        try {
            BitInputStream BIStream = new BitInputStream(new FileInputStream(inputFile));
            FileOutputStream FOStream = new FileOutputStream(outputFile);
            // Reads the first 256 ints which make up the frequency table
            int n = 0;
            while (n < 256) {
                frequencyTable[n] = BIStream.readInt();
                // Calculates the sum of all frequencies which corresponds
                // to the amount of characters that needs to be written
                numberOfCharacters += frequencyTable[n];
                n++;
            }

            // recreates the HuffmanTree that was used to encode the original file from the read frequency table
            HuffmanTree huffmanTree = HuffmanTree.createHuffmanTree(frequencyTable);
            // creates a point that points to subtree that is currently is being visited
            HuffmanTree HTPointer = huffmanTree;
            n = 0;
            while (n < numberOfCharacters) { // writes no more characters than the combined amount found in frequency table
                // Goes down a path in the HuffmanTree as long as the HTPoint does not point to a subtree which is equal to null
                while (HTPointer.left() != null) {  // exits if a leaf is reached
                    // Goes down the right or left subtree depending on what is returned bit BIStream.readBit()
                    // if 0 is read HTPoint is updated to point to its current left subtree else if a 1 is read
                    // HTPointer is updated to point to its current right subtree
                    int bit = BIStream.readBit();
                    if (bit == 0)
                        HTPointer = HTPointer.left();
                    else if (bit == 1)
                        HTPointer = HTPointer.right();
                }
                // Writes the integer representation of the decoded Huffman code found at the leaf
                // which the HTPointer is pointing at.
                FOStream.write(HTPointer.getBitValue());
                HTPointer = huffmanTree; // resets the HTPointer when a leaf is reached
                n++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
