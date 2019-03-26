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

public class register extends AppCompatActivity {
    ImageView r_register;
    EditText username;
    EditText password ;
    EditText conpassword ;
    private  String mFileName="chatting.txt";
    private  String mFileNameInfo="nameinfo.txt";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        r_register=findViewById(R.id.r_register);
        username = findViewById(R.id.r_username);
        password = findViewById(R.id.r_password);
        conpassword = findViewById(R.id.r_password2);



        r_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent mIntent = new Intent(getBaseContext(), playing.class);
                // startActivity(mIntent);


                if (username.getText().toString().length()<1) {
                    Toast.makeText(getApplicationContext(),
                            "No user input!", Toast.LENGTH_LONG)
                            .show();
                } else {

                   //String info=read1();
                   if(password.getText().toString().equals("")||conpassword.getText().toString().equals("")){

                       Toast.makeText(getApplicationContext(),
                               "No password input!", Toast.LENGTH_LONG)
                               .show();
                   }else{
                         String nameinfo=read2();
                       if(nameinfo.indexOf(username.getText().toString())<0){

                           if(password.getText().toString().equals(conpassword.getText().toString())){
                               Save2(username.getText().toString());
                               mFileName=username.getText().toString()+".txt";
                               Save("{start\tname:"+username.getText().toString()+"\tpassword:"+password.getText().toString()+"\tnum:0\tend}");
                               Toast.makeText(getApplicationContext(),
                                       "Successful registration!", Toast.LENGTH_LONG)
                                       .show();
                               startActivityForResult(new Intent(getBaseContext(), login.class),0);
                           }
                           else{
                               Toast.makeText(getApplicationContext(),
                                       "Password not equal Conpassword!", Toast.LENGTH_LONG)
                                       .show();
                           }
                       }
                       else{
                           Toast.makeText(getApplicationContext(),
                                   "Username is exist!", Toast.LENGTH_LONG)
                                   .show();
                       }
                   }




                }


            }
        });


    }


    private void save1(String content){
        FileOutputStream fileOutputStream = null;
        try {
            File  dir = new File(Environment.getExternalStorageDirectory(),"a_chatting");
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
            File dir = new File(Environment.getExternalStorageDirectory(),"a_chatting");
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
    private void save2(String content){
        FileOutputStream fileOutputStream = null;
        try {
            File  dir = new File(Environment.getExternalStorageDirectory(),"a_chatting");
            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(dir,mFileNameInfo);
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
    private String  read2(){
        FileInputStream fileInputStream = null;

        try {

            //File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"skypan",mFileName);
            File dir = new File(Environment.getExternalStorageDirectory(),"a_chatting");
            if(!dir.exists()){
                dir.mkdir();
            }
            File file = new File(dir,mFileNameInfo);
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
    private void  Save2(String content){
        String Content=read2();
        Content=Content+"\t"+content;
        save2(Content);
    }

}

