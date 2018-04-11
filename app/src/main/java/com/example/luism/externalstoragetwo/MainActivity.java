package com.example.luism.externalstoragetwo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText fileName;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        isExtrenalStorageReadable();
        isExtrenalStorageWritable();

        fileName = findViewById(R.id.fileName);
        text = findViewById(R.id.text);

    }

    private boolean isExtrenalStorageWritable(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Log.i("State", "Yes, it is writable");
            return true;
        }else{
            return false;
        }
    }


    public void writeFile(View v){
        if(isExtrenalStorageWritable() && checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            File textFile = new File(Environment.getExternalStorageDirectory(), fileName.getText().toString());

            try{
                FileOutputStream fos = new FileOutputStream(textFile);
                fos.write(text.getText().toString().getBytes());
                fos.close();

                Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
                Toast.makeText(this, "Connot write to External Storage", Toast.LENGTH_SHORT).show();

            }
        }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }


//    private boolean isExtrenalStorageReadable(){
//        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())){
//            Log.i("State", "Yes, it is readable");
//            return true;
//        }else{
//            return false;
//        }
//    }

}
