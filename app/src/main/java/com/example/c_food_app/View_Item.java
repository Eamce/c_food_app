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
    TextView description,quantity,total;
    TextView price;
    Globalvars globalvars;
    Button addtocart,minus,add;
    String des,pri,image;
    byte[] decodedString;
    Bitmap decodedByte;
    int quan=0;
    double total_amt= 0,price_amt=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        init();
        description.setText(des);
        price.setText("₱"+pri+"/kl.");
        imageView.setImageBitmap(decodedByte);

                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                             quan= quan+1;
                             total_amt=price_amt*quan;
                             quantity.setText(""+quan);
                             total.setText("Total: "+total_amt);
                            if(quan>0){
                                minus.setEnabled(true);
                            }
                        }
                    });

                    minus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            quan= quan-1;
                            total_amt=price_amt*quan;
                            quantity.setText(""+quan);
                            total.setText("Total: "+total_amt);
                            if(quan<=0){
                                minus.setEnabled(false);
                            }
                        }
                    });

                    addtocart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
    }

        public void init(){
            Intent intent  = getIntent(); // get Intent which we set from Previous Activity
            imageView      = findViewById(R.id.imageView);
            description    = findViewById(R.id.v_description);
            price          = findViewById(R.id.v_price);
            addtocart      = findViewById(R.id.addtocart);
            minus          = findViewById(R.id.minus);
            add            = findViewById(R.id.plus);
            quantity       = findViewById(R.id.quantity);
            total          = findViewById(R.id.total);
            globalvars     = new Globalvars(getApplicationContext(),this);
            quan           = Integer.parseInt(quantity.getText().toString().trim());
            des            = intent.getStringExtra("description");
            pri            = intent.getStringExtra("price");
            image          = globalvars.get("image");
            price_amt      = Double.parseDouble(pri);
            decodedString  = Base64.decode(image, Base64.DEFAULT);
            decodedByte    = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                if(quan<=0){
                    minus.setEnabled(false);
                }
        }
}