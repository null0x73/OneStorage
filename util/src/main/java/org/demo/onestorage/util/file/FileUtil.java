package org.demo.onestorage.util.file;

import java.io.*;

public class FileUtil {

    public static void writeBinaryFile(String targetFilename, byte[] data) throws IOException {
        File file = new File(targetFilename);
        if(!file.exists()){
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        outputStream.write(data);
        outputStream.close();
    }

    public static void writeBinaryFile(String targetPath, String filename, byte[] data) throws IOException {
        writeBinaryFile(targetPath+"/"+filename, data);
    }

}
