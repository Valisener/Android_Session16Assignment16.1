package a1.assignment16.acadgild.com.assignment161;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
//    Creatings buttons,textviews,editviews, filename, file,external storage variables
    Button btnAdd;
    Button btnDelete;
    TextView textContents;
    EditText editText;
    static String FILENAME = "File1.txt";
    File file;
    File externalStorage = Environment.getExternalStorageDirectory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Reference the button add
        btnAdd = findViewById(R.id.btn_Add);
//        Reference the button delete
        btnDelete= findViewById(R.id.btn_Delete);
//        Reference the text view that displays all the information from the text file
        textContents = findViewById(R.id.textContent);
//        references the edit text box
        editText = findViewById(R.id.editTextInput);
//        set the file name and directory of where the file will be
        file = new File(externalStorage,FILENAME);

//        Try to create a new file if it does not exist already then no new file will be created
        try {
            file.createNewFile();

            }
        catch (IOException e) {
            e.printStackTrace();
            }
//        Call method to read from file
        readFromFile();
//        Add button on click listener to handle adding data to the file and resetting edit text
//        Then update the textview by calling read from file method
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToFileAndResetEditText();
                readFromFile();
            }
        });
//        Method that deletes the file and clears the text view of text
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Calls method that deletes the file and clears the text view
                deleteFileAndClearTextView();
            }
        });

    }

//    Method that deletes the file and clears the text view that displays the contents of the file
    public void deleteFileAndClearTextView() {
        file.delete();
        textContents.setText("");
    }
//  Method that adds the data entered to the file and resets the edit text to empty
    public void addDataToFileAndResetEditText(){
        String text = editText.getText().toString();
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        new WriteToFile(file).execute(text);
        editText.setText("");
    }

//  Reads the text from the file if it exists.
    private void readFromFile() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = bufferedReader.readLine())  != null) {
                stringBuilder.append(text);
                stringBuilder.append("\n");
            }
            textContents.setText(stringBuilder);
            bufferedReader.close();;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}