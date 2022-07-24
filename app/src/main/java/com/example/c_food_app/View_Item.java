package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class View_Item extends AppCompatActivity {
    ImageView imageView;
    TextView description;
    TextView price;
    Globalvars globalvars;
    Button addtocart;
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
        price.setText("₱"+pri+"/kl.");
        imageView.setImageBitmap(decodedByte);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Are you sure you want to add this to cart?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Succesfully Added to cart!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

        public void init(){
        imageView      = findViewById(R.id.imageView);
        description    = findViewById(R.id.v_description);
        price          = findViewById(R.id.v_price);
        addtocart      = findViewById(R.id.addtocart);
        globalvars     = new Globalvars(getApplicationContext(),this);
        }
}