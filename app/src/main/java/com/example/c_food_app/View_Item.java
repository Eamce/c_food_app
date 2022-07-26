package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class View_Item extends AppCompatActivity {
    ImageView imageView;
    TextView description,quantity,total;
    TextView price;
    Globalvars globalvars;
    Button addtocart,minus,add;
    String des,pri,image,id,count;
    byte[] decodedString;
    Bitmap decodedByte;
    int quan=0;
    Msgbox msgbox;
    Context context = this;
    double total_amt= 0,price_amt=0;
    ContentValues cv;
    SQLiteDatabase sqLiteDatabase ;
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
                    addtocart.setEnabled(true);
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
                    addtocart.setEnabled(false);
                }
            }
        });
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.put("cart_id",String.valueOf(count));
                cv.put("description",des);
                cv.put("price",pri);
                cv.put("cat_image",globalvars.get("image"));
                cv.put("quantity",quantity.getText().toString());
                cv.put("total",String.valueOf(total_amt));
                cv.put("prod_id",id);
                sqLiteDatabase.insert("cart",null,cv);
                Toast.makeText(View_Item.this, "Successfully added to cart!", Toast.LENGTH_SHORT).show();
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
        id             = intent.getStringExtra("id");
        count          = intent.getStringExtra("count");
        image          = globalvars.get("image");
        price_amt      = Double.parseDouble(pri);
        decodedString  = Base64.decode(image, Base64.DEFAULT);
        cv             = new ContentValues();
        msgbox         = new Msgbox(context);
        String path    = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path,MODE_PRIVATE,null);
        decodedByte    = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        if(quan<=0){
            minus.setEnabled(false);
            addtocart.setEnabled(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_main_setting) {
        } else if(item.getItemId()==R.id.logout){
            msgbox.showyesno( "Hello","Are you sure you want to log out?");
            msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
                @Override
                public void onyes() {
                    Intent logout = new Intent(View_Item.this, Login.class);
                    startActivity(logout);
                    globalvars.logout();
                }
                @Override
                public void onno() {
                }
            });
        }else if(item.getItemId()==R.id.account){
            Intent intent = new Intent(View_Item.this, My_Account.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.home){
            Intent intent = new Intent(View_Item.this, Home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}