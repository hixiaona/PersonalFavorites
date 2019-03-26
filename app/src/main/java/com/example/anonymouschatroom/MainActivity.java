package com.example.anonymouschatroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView btn_seek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_seek=findViewById(R.id.btn_start);
        btn_seek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Intent mIntent = new Intent(getBaseContext(), playing.class);
                // startActivity(mIntent);

                startActivityForResult(new Intent(getBaseContext(), login.class),1);
            }
        });
    }
}
