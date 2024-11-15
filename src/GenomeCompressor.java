/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java <
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/

/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {
        int[] keys = new int[256];
        keys['A'] = 0;
        keys['C'] = 1;
        keys['G'] = 2;
        keys['T'] = 3;

        String data = BinaryStdIn.readString();

        BinaryStdOut.write(data.length());

        for(int i = 0; i < data.length(); i++){
            BinaryStdOut.write(keys[data.charAt(i)], 2);
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {
        char[] codes = new char[4]; // Adjust to only store necessary mappings.
        codes[0] = 'A';
        codes[1] = 'C';
        codes[2] = 'G';
        codes[3] = 'T';

        int numCharacters = BinaryStdIn.readInt();

        for(int i = 0; i < numCharacters; i++){
            int num = BinaryStdIn.readInt(2);
            BinaryStdOut.write(codes[num]);
        }

        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        if (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}