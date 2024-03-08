import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter input filename");
        String path = sc.next();
        File f1 = new File(path);
        System.out.println("Enter output filename");
        path = sc.next();
        File f2 = new File(path);
        try {
            int[] score = scoreSymbols(f1);
            writeSymbols(f2, score);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static int[] scoreSymbols(File file) throws IOException {
        if (!file.exists()) throw new FileNotFoundException("Input File doesn't exist!");

        int[] res = new int[256];
        InputStream input = new FileInputStream(file);
        Reader reader = new InputStreamReader(input);
        int symb;
        while ((symb = reader.read()) != -1) {
            if ((symb >= 65 && symb <= 90) || (symb >= 97 && symb <= 122)) res[symb]++;
        }
        return res;
    }

    private static void writeSymbols(File file, int[] arr) throws IOException {
        if (!file.exists()) throw new FileNotFoundException("Output File doesn't exist!");

        OutputStream output = new FileOutputStream(file);
        Writer writer = new OutputStreamWriter(output);
        for (int i = 0; i < 256; i++) {
            if (arr[i] != 0) {
                writer.write(((char) i) + " = " + arr[i] + "\n");
            }
        }
        writer.close();
    }
}