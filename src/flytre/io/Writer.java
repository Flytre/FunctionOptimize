package flytre.io;

import java.io.*;

public class Writer {


    public static void setFileContents(File f, String content) {
        try(DataOutputStream outstream = new DataOutputStream(new FileOutputStream(f,false));) {
            outstream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
