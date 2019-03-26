package com.example.anonymouschatroom;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class chatting extends AppCompatActivity {
   ImageView btn_return;
    private WebView webView;
    ImageView menu;
    private  String mFileName="chatting.txt";
    private  int Count;
    private    String URL;
    TextView    tv_url;
    private int ident=0;
    private String name="UNLOAD";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatting);
        //setContentView(R.layout.top_panel_return_doubletitle_ivmenu);
        final int Num= getIntent().getIntExtra("num",1);
        ident= getIntent().getIntExtra("ident",1);
        if(ident==0){
            name=getIntent().getStringExtra("name");
            mFileName=name+".txt";
        }

        btn_return=findViewById(R.id.ivreturn);
        webView = findViewById(R.id.view1);
        menu=findViewById(R.id.ivmenu);
        tv_url=findViewById(R.id.tv_url);



        String allinfo=read1();
        Count=0;
        int  Url = allinfo.indexOf("{Url", Count);
        Count=Url;
        for(int i=0;i<=Num;i++){

            int url = allinfo.indexOf("url:", Count);
            int end = allinfo.indexOf("end}", Count);
            Count = end + 1;
            URL=allinfo.substring(url + 4, end - 1);
        }

        tv_url.setText(URL);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.getSettings().setDomStorageEnabled(true);
        //webView.loadUrl("http://tianqi.moji.com/");

        //是否可以后退
        webView.canGoBack();
//后退网页
        webView.goBack();

//是否可以前进
        webView.canGoForward();
//前进网页
        webView.goForward();


        webView.loadUrl("https://"+URL);
//以当前的index为起始点前进或者后退到历史记录中指定的steps
//如果steps为负数则为后退，正数则为前进
        int intsteps=1;
        webView.goBackOrForward(intsteps);





        btn_return.setOnClickListener(new View.OnClickListener() {
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

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent mIntent = new Intent(getBaseContext(), playing.class);
                // startActivity(mIntent);
                Toast.makeText(getApplicationContext(),
                        Num, Toast.LENGTH_LONG)
                        .show();


            }
        });

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

}
