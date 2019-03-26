package com.example.anonymouschatroom;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class seeking extends AppCompatActivity {
    ImageView create;
    ImageView head1;
    ListView  select;
    TextView  username;
    private  int Count=0;
    private  String mFileName="chatting.txt";
    private String[] arrayDemo ;
    private String[] arrayDate ;
    private String[] arrayMes ;
    private SimpleAdapter sa;
    private int ident=0;
    private String name="UNLOAD";
    private int url;


    private int[] headimage = {R.drawable.cat01,R.drawable.cat02,R.drawable.cat01,R.drawable.cat03,R.drawable.cat04,R.drawable.cat05,R.drawable.cat06,R.drawable.cat07,R.drawable.cat08,R.drawable.cat09,R.drawable.cat10,R.drawable.cat11,R.drawable.cat12,R.drawable.cat13,R.drawable.cat14,R.drawable.cat15,R.drawable.cat16,R.drawable.cat17,R.drawable.cat18,R.drawable.cat19};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seeking);

        create=findViewById(R.id.create);
        head1=findViewById(R.id.head1);
        select=findViewById(R.id.lv);
        select=findViewById(R.id.lv1);
        select=findViewById(R.id.lv2);
        username=findViewById(R.id.message111);
        ident= getIntent().getIntExtra("ident",1);

        if(ident==0){
            name=getIntent().getStringExtra("name");
            username.setText(name);
            mFileName=name+".txt";
        }




        String allinfo=read1();
        int nu=allinfo.indexOf("num:");
        int end=allinfo.indexOf("end}");
        String  count=allinfo.substring(nu+4,end-1);
        int mcount= Integer.parseInt(String.valueOf(count));
        arrayDemo = new String[mcount];
        arrayMes = new String[mcount];
        arrayDate = new String[mcount];
        Count=0;
       for(int i=0;i<mcount;i++){

           //for(int j=0;j<3;j++) {
               int theme = allinfo.indexOf("theme:", Count);
               int con = allinfo.indexOf("content:", Count);
               int time = allinfo.indexOf("time:", Count);
               int url = allinfo.indexOf("url:", Count);
               Count = url + 1;
               arrayDemo[i] = (allinfo.substring(theme + 6, con - 1));
               arrayMes[i] = (allinfo.substring(con + 8, time - 1));
               arrayDate[i] = (allinfo.substring(time + 5, url - 1));
           //}

       }





        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.item_layout,R.id.name_msg_item, arrayDemo);
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, R.layout.item_layout, R.id.content_msg_item, arrayMes);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.item_layout, R.id.time_msg_item, arrayDate);

        ListView listView = findViewById(R.id.lv);
        ListView listView1 = findViewById(R.id.lv1);
        ListView listView2 = findViewById(R.id.lv2);



        listView.setAdapter(arrayAdapter);
        listView1.setAdapter(arrayAdapter1);
        listView2.setAdapter(arrayAdapter2);


        /*
         *  Update the title on the action to show the total number of student list
         */



            select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),
                        "Open scuuessful!", Toast.LENGTH_LONG)
                        .show();
                Intent intent=new Intent(getApplicationContext(),chatting.class);
                Bundle bundle =new Bundle();
                bundle.putInt("num",position);
                bundle.putString("name",name);
                bundle.putInt("ident",ident);

                intent.putExtras(bundle);
                startActivityForResult(intent,0);

                //startActivityForResult(new Intent(getBaseContext(), chatting.class),0);
                //Toast.makeText(seeking.this,"click pos:"+position,Toast.LENGTH_SHORT).show();
            }
        });
      select.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


               String allinfo=read1();
               int nu=allinfo.indexOf("num:");
               int end=allinfo.indexOf("end}");
               String  count=allinfo.substring(nu+4,end-1);
               int mcount= Integer.parseInt(String.valueOf(count));
               mcount=mcount-1;
               allinfo=allinfo.substring(0,nu+4)+String.valueOf(mcount)+allinfo.substring(end-1,allinfo.length());
               save1(allinfo);

               allinfo=read1();

              for(int i=0;i<=position;i++){
                  Count=end+1;
                   url=allinfo.indexOf("{Url");
                   end=allinfo.indexOf("end}");
              }
               allinfo=allinfo.substring(0,url)+"\t"+allinfo.substring(end+4,allinfo.length());
               save1(allinfo);


               Toast.makeText(getApplicationContext(),
                       "Delete scuuessful!", Toast.LENGTH_LONG)
                       .show();

               Intent intent=new Intent(getApplicationContext(),seeking.class);
               Bundle bundle =new Bundle();
               bundle.putString("name",name);
               bundle.putInt("ident",ident);

               intent.putExtras(bundle);
               startActivityForResult(intent,0);
               //Toast.makeText(seeking.this,"clicking pos:"+position,Toast.LENGTH_SHORT).show();
               return true;
           }
       });




        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent mIntent = new Intent(getBaseContext(), playing.class);
                // startActivity(mIntent);
                Intent intent=new Intent(getApplicationContext(),creating.class);
                Bundle bundle =new Bundle();
                bundle.putString("name",name);
                bundle.putInt("ident",ident);
                intent.putExtras(bundle);
                startActivityForResult(intent,0);
            }
        });

        head1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent mIntent = new Intent(getBaseContext(), playing.class);
                // startActivity(mIntent);
                Toast.makeText(getApplicationContext(),
                        "Used to modify personal information, avatar, username, interface style, etc., which is ignored due to time reasons.", Toast.LENGTH_LONG)
                        .show();

               // startActivityForResult(new Intent(getBaseContext(), myinfo.class),0);
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

}
