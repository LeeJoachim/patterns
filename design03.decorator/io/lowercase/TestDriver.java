package design03.decorator.io.lowercase;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

class LowerCaseInputStream extends FilterInputStream {

    LowerCaseInputStream(InputStream in) throws IOException{
        super(in);
    }
    
    @Override
    public int read() throws IOException{
        int c = super.read();
        return (c == -1 ? c : Character.toLowerCase((char)c));
    }

    @Override
    public int read(byte[] b, int offset, int len) throws IOException {
        int result = super.read(b, offset, len);
        for (int i = offset; i < offset+result; i++) {
            b[i] = (byte)Character.toLowerCase((char)b[i]);
        }
        return result;
    }
}

class TestDriver {
    public static void main(String[] args)
    {
        int c;

        try {
            InputStream in = new LowerCaseInputStream(
                         new BufferedInputStream(
                         new FileInputStream("design03.decorator/io/lowercase/text.txt")));
            while((c = in.read()) >= 0) {
                System.out.print((char)c);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}