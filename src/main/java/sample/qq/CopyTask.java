package sample.qq;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;

// Copy all file in C:/Windows
public class CopyTask extends Task<List<File>> {

    @Override
    protected List<File> call() throws Exception {
        List<File> copied = new ArrayList<>();



        File dir = new File("D:\\E\\ff");
        File[] files = dir.listFiles();
        int count = files.length;

        int i = 0;
        for (File file : files) {
            if (file.isFile()) {
                this.copy(file);
                copied.add(file);
            }
            i++;
            this.updateProgress(i, count);
        }
        return copied;
    }

    private void copy(File file) throws Exception {
        this.updateMessage("Copying: " + file.getAbsolutePath());
        Thread.sleep(500);
    }

}