package a1.assignment16.acadgild.com.assignment161;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile extends AsyncTask<String, Integer, String > {
    File file;
//    constructor that takes the file
    WriteToFile(File file) {
        this.file = file;
    }
    @Override
    protected String doInBackground(String... strings) {
        FileWriter fileWriter = null;
//        Try to write the data to the file and add a new line to it.
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.append(strings[0].toString());
            fileWriter.append("\n");
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
                ;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
