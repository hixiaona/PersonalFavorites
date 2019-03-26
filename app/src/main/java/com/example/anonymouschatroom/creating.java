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
import java.util.Calendar;

public class creating extends AppCompatActivity {
    ImageView btn_confirmCreat;
    TextView  btn_finish;
    EditText e_theme;
    EditText e_content;
    EditText e_url;
    private int ident=1;
    private String name="UNLOAD";

    private  String mFileName="chatting.txt";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creating);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        ident= getIntent().getIntExtra("ident",1);
        if(ident==0){
            name=getIntent().getStringExtra("name");
            mFileName=name+".txt";
        }


        btn_confirmCreat=findViewById(R.id.ivreturn1);
        btn_finish=findViewById(R.id.finish);
        e_theme=findViewById(R.id.theme);
        e_content=findViewById(R.id.content111);
        e_url=findViewById(R.id.url);


        btn_confirmCreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent mIntent = new Intent(getBaseContext(), playing.class);
                // startActivity(mIntent);
                Intent intent=new Intent(getApplicationContext(),seeking.class);
                Bundle bundle =new Bundle();
                bundle.putString("name",name);
                bundle.putInt("ident",ident);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String allinfo=read1();
                int nu=allinfo.indexOf("num:");
                int end=allinfo.indexOf("end}");
                String  count=allinfo.substring(nu+4,end-1);
                int mcount= Integer.parseInt(String.valueOf(count));
                mcount=mcount+1;
                allinfo=allinfo.substring(0,nu+4)+String.valueOf(mcount)+allinfo.substring(end-1,allinfo.length());
                save1(allinfo);

                String time=getTime();
                Save("{Url\ttheme:"+e_theme.getText().toString()+"\tcontent:"+e_content.getText().toString()+"\ttime:"+time+"\turl:"+e_url.getText().toString()+"\tend}");

                Toast.makeText(getApplicationContext(),
                        "Appending...", Toast.LENGTH_LONG)
                        .show();
                Intent intent=new Intent(getApplicationContext(),seeking.class);
                Bundle bundle =new Bundle();
                bundle.putString("name",name);
                bundle.putInt("ident",ident);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
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
    private String getTime() {
        final Calendar c = Calendar.getInstance();
        String year=String.valueOf(c.get(Calendar.YEAR));
        String month=String.valueOf(c.get(Calendar.MONTH));
        String date=String.valueOf(c.get(Calendar.DATE));
        String hour=String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String time=String.valueOf(c.get(Calendar.MINUTE));
        String Time=year+"/"+month+"/"+date+"\t"+hour+":"+time;
        return Time;
    }


}
