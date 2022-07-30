package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Checkout_Details extends AppCompatActivity {
        EditText fullname,address;
        Button checkout_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_details);
        init();
        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullname.getText().toString().isEmpty() || address.getText().toString().isEmpty() ){
                    Toast.makeText(Checkout_Details.this, "Please fill up information!", Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });


    }

    public void init(){
        fullname = (EditText) findViewById(R.id.fullname);
        address  = (EditText) findViewById(R.id.address);
        checkout_btn = (Button) findViewById(R.id.checkout_btn);
    }
}




