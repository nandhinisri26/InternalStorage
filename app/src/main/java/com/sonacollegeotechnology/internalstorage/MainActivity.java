package com.sonacollegeotechnology.internalstorage;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText data;
    Button save,read;
    TextView datafromfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data=findViewById(R.id.data);
        save=findViewById(R.id.save);
        read=findViewById(R.id.read);
        datafromfile = findViewById(R.id.datafromfile);

        save.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                String dataa=data.getText().toString();

                FileOutputStream fos;
                try {
                    fos = openFileOutput("TestFile", Context.MODE_PRIVATE);
                    fos.write(dataa.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(),"TestFile saved", Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}
            }
        });

        read.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                StringBuffer stringBuffer = new StringBuffer();
                try {
                    BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                            openFileInput("TestFile")));
                    String inputString;
                    while ((inputString = inputReader.readLine()) != null) {
                        stringBuffer.append(inputString + "\n");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                datafromfile.setText(stringBuffer.toString());
                Toast.makeText(getApplicationContext(),"Content from TestFile",Toast.LENGTH_LONG).show();
            }
        });
    }
}