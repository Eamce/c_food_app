package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class View_Item extends AppCompatActivity {
    ImageView imageView;
    TextView description;
    TextView price;
    Globalvars globalvars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        init();
        Intent intent = getIntent(); // get Intent which we set from Previous Activity
        String des = intent.getStringExtra("description");
        String pri = intent.getStringExtra("price");
        String image = globalvars.get("image");
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        description.setText(des);
        price.setText(pri);
        imageView.setImageBitmap(decodedByte);
    }

        public void init(){
        imageView      = findViewById(R.id.imageView);
        description    = findViewById(R.id.v_description);
        price          = findViewById(R.id.v_price);
        globalvars     = new Globalvars(getApplicationContext(),this);
        }
}