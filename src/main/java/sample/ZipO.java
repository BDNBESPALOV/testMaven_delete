package sample;

import java.io.*;
import java.util.zip.*;

public class ZipO {
    ZipO(){
        String filename = "E:/ff/*.*";
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("E:/ff/NNoutput.zip"));
            FileInputStream fis= new FileInputStream(filename);)
        {
            ZipEntry entry1=new ZipEntry("notes.txt");
            zout.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }
}
