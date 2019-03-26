package com.example.anonymouschatroom;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class myinfo extends AppCompatActivity {
    ImageView btn_confirm;

    private  String mFileName="chatting.txt";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinfo);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        btn_confirm=findViewById(R.id.btn_start1);




        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent mIntent = new Intent(getBaseContext(), playing.class);
                // startActivity(mIntent);
                if (((EditText) findViewById(R.id.username)).getText().toString().length() < 1) {
                    //Toast.makeText(this, "No user input!", Toast.LENGTH_LONG).show();
                } else {

                    EditText mEditText = findViewById(R.id.username);


                    //save1(mEditText.getText().toString());
                    //mFileName=mEditText.getText().toString()+".txt";
                    // Save("\t");

                    Save("{start\tname:"+mEditText.getText().toString()+"\tnum:0\tend}");
                    startActivityForResult(new Intent(getBaseContext(), seeking.class),0);
                }


            }
        });



    }


    private void save1(String content){
        FileOutputStream fileOutputStream = null;
        try {
            File  dir = new File(Environment.getExternalStorageDirectory(),"skypan");
            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(dir,mFileName);
            if(!file.exists()){
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream !=null){
                try{
                    fileOutputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private String  read1(){
        FileInputStream fileInputStream = null;

        try {

            //File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"skypan",mFileName);
            File dir = new File(Environment.getExternalStorageDirectory(),"skypan");
            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(dir,mFileName);
            if(!file.exists()){
                file.createNewFile();
            }
            fileInputStream = new FileInputStream(file);
            byte[]buff=new byte[1024];
            StringBuilder sb=new StringBuilder("");
            int len=0;
            while ((len=fileInputStream.read(buff))>0){
                sb.append(new String(buff,0,len));
            }
            return sb.toString();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    private void  Save(String content){
        String Content=read1();
        Content=Content+"\t"+content;
        save1(Content);
    }

}
