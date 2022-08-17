package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.airbnb.lottie.animation.content.Content;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    Ajax ajax;
    Globalvars globalvars;
    Msgbox msgbox;
    EditText search_text;
    Context context = this;
    SQLiteDatabase sqLiteDatabase;
    Category_Adapter2 category_adapter2;
    GridView category_view;
    FloatingActionButton fab;
    ContentValues values;
    Drawable[] images;
    String[] description;
    String[] price;
    Drawable shrimp,crabs,fish,squid, lobster ,clamps ,guso, oyster, frozen_tilapia ,dilis ,bisogo ,tuna ,sardines, scallops ,froze_scallops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        Category_Adapter adapter = new Category_Adapter(getApplicationContext(), images, description, price, this);
        category_view.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, View_Cart.class);
                startActivity(intent);
            }
        });
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
                    Intent logout = new Intent(Home.this, Login.class);
                    startActivity(logout);
                    finish();
                    globalvars.logout();
                }
                @Override
                public void onno() {
                }
            });
        }else if(item.getItemId()==R.id.account){
            Intent intent = new Intent(Home.this, My_Account.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.my_order){
            Intent intent = new Intent(Home.this, MyOrder.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void init(){
        ajax = new Ajax();
        msgbox = new Msgbox(context);
        category_view = (GridView) findViewById(R.id.grid_categories);
        fab = findViewById(R.id.fab);
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        globalvars = new Globalvars(getApplicationContext(),this);
        values = new ContentValues();
        shrimp         = this.getResources().getDrawable(R.drawable.shrimp);
        crabs          = this.getResources().getDrawable(R.drawable.crabs);
        fish           = this.getResources().getDrawable(R.drawable.fish);
        squid          = this.getResources().getDrawable(R.drawable.squid);
        lobster        = this.getResources().getDrawable(R.drawable.lobster);
        clamps         = this.getResources().getDrawable(R.drawable.clamps);
        guso           = this.getResources().getDrawable(R.drawable.guso);
        oyster         = this.getResources().getDrawable(R.drawable.oyster);
        frozen_tilapia = this.getResources().getDrawable(R.drawable.frozen_tilapia);
        dilis          = this.getResources().getDrawable(R.drawable.dilis);
        bisogo         = this.getResources().getDrawable(R.drawable.driedfishbisogo);
        tuna           = this.getResources().getDrawable(R.drawable.frozen_tuna);
        sardines       = this.getResources().getDrawable(R.drawable.frozen_sardines);
        scallops       = this.getResources().getDrawable(R.drawable.scallops);
        froze_scallops = this.getResources().getDrawable(R.drawable.frozen_scallops);
        images = new Drawable[]{shrimp, crabs, fish, squid, lobster, clamps, guso, oyster, frozen_tilapia, dilis, bisogo, tuna, sardines, scallops, froze_scallops};
        description = new String[]{"Shrimp", "Crabs", "Fish", "Squid", "Lobster", "Clamps", "Guso", "Oyster", "Frozen Tilapia", "Dilis Fish", "Dried Fish Bisogo", "Frozen Tuna", "Frozen Sardines", "Scallops", "Frozen Scallops"};
        price = new String[]{"400.00", "400.00", "350.00", "380.00", "430.00", "400.00", "120.00", "125.00", "380.00", "130.00", "200.00", "370.00", "280.00", "250.00", "320.00"};
    }
}


//    Bitmap bit_shrimp = ((BitmapDrawable) shrimp).getBitmap();
//        Bitmap bit_crabs = ((BitmapDrawable) crabs).getBitmap();
//        Bitmap bit_fish = ((BitmapDrawable) fish).getBitmap();
//        Bitmap bit_squid = ((BitmapDrawable) squid).getBitmap();
//        Bitmap bit_lobster = ((BitmapDrawable) lobster).getBitmap();
//        Bitmap bit_clamps = ((BitmapDrawable) clamps).getBitmap();
//        Bitmap bit_guso = ((BitmapDrawable) guso).getBitmap();
//        String str_shrimp = getStringImage(bit_shrimp);
//        String str_scrabs = getStringImage(bit_crabs);
//        String str_fish = getStringImage(bit_fish);
//        String str_squid = getStringImage(bit_squid);
//        String str_lobster = getStringImage(bit_lobster);
//        String str_clamps = getStringImage(bit_clamps);
//        String str_guso = getStringImage(bit_guso);
//  int[] images={R.drawable.shrimp,R.drawable.crabs,R.drawable.fish,R.drawable.squid,R.drawable.lobster,R.drawable.lobster,R.drawable.clamps,R.drawable.clamps};
