package design03.decorator.io.noblank;

/* edit plus does not work when package declared java files,
 * because you don't send root directory's info to compiler.
 * standard case : src is root directory and don't write src in path
 * this file's root is patterns and you have to open patterns dir
 * that action set the root dir
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class Print {

    final static int EOF = -1;

    static void PerCharacter(InputStream in) throws IOException{
        int c = 0;
        while((c = in.read()) != EOF) {
            System.out.print((char)c);
        }
    }

    static void PerByte(InputStream in) throws IOException{
        byte[] bytes = new byte[1024];
        int bytesUnit = 0;
        while((bytesUnit = in.read(bytes)) != EOF) {
            System.out.write(bytes, 0, bytesUnit);
        }        
    }
}

class NoBlankInputStream extends FilterInputStream {

    final static int EOF = -1;

    NoBlankInputStream(InputStream in) {
        super(in);
    }

    public int read() throws IOException {

        int c;

        while ((c = super.read()) != EOF) {
            if (Character.isWhitespace((char)c) == false) {
                break;
            }
        }
        return c;
    }

    public int read(byte[] bytes, int offset, int bytesUnit) throws IOException {

        int Unit = super.read(bytes, offset, bytesUnit);
        if (Unit == EOF) {
            return EOF;
        }
        
        int j = 0;
        for (int i = offset; i < offset+bytesUnit; i++) {
            if (Character.isWhitespace((char)bytes[i]) == false) {
                bytes[j] = bytes[i];
                j++;
            }
        }

        return j;
    }
}

class TestDriver {
    public static void main(String[] args) {
        
        try {
            InputStream in = new NoBlankInputStream(
                         new BufferedInputStream(
                         new FileInputStream("design03.decorator/io/noblank/text.txt"))); // rule is same about package
            // Print.PerCharacter(in);
            Print.PerByte(in);
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}