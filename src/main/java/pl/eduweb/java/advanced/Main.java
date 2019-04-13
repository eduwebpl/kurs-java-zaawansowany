package pl.eduweb.java.advanced;


/*
struct Class_File_Format {
   u4 magic_number;
   u2 minor_version;
   u2 major_version;
   ...
}
*/

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) {
        String filePath = "target/classes/pl/eduweb/java/advanced/Main.class";
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            System.out.println(Integer.toHexString(getMagicNumber(raf)));
            System.out.println(getMinorVersion(raf));
            System.out.println(getMajorVersion(raf));
            setMajorVersion(raf, (short) 53);
            System.out.println(getMajorVersion(raf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setMajorVersion(RandomAccessFile raf, short majorVersion) throws IOException {
        raf.seek(6);
        raf.writeShort(majorVersion);
    }

    private static int getMagicNumber(RandomAccessFile raf) throws IOException {
        raf.seek(0);
        ByteBuffer buffer4 = ByteBuffer.allocate(4);
        raf.read(buffer4.array());
        return buffer4.getInt();
    }

    private static short getMinorVersion(RandomAccessFile raf) throws IOException {
        raf.seek(4);
        ByteBuffer buffer2 = ByteBuffer.allocate(2);
        raf.read(buffer2.array());
        return buffer2.getShort(0);
    }

    private static short getMajorVersion(RandomAccessFile raf) throws IOException {
        raf.seek(6);
        ByteBuffer buffer2 = ByteBuffer.allocate(2);
        raf.read(buffer2.array());
        return buffer2.getShort(0);
    }
}