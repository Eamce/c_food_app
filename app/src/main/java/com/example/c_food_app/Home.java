package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ajax = new Ajax();
        msgbox = new Msgbox(context);
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        category_view = (GridView) findViewById(R.id.grid_categories);
        FloatingActionButton fab = findViewById(R.id.fab);
        globalvars = new Globalvars(getApplicationContext(),this);
        Drawable shrimp = this.getResources().getDrawable(R.drawable.shrimp);
        Drawable crabs = this.getResources().getDrawable(R.drawable.crabs);
        Drawable fish = this.getResources().getDrawable(R.drawable.fish);
        Drawable squid = this.getResources().getDrawable(R.drawable.squid);
        Drawable lobster = this.getResources().getDrawable(R.drawable.lobster);
        Drawable clamps = this.getResources().getDrawable(R.drawable.clamps);
        Drawable guso = this.getResources().getDrawable(R.drawable.guso);
        Drawable oyster = this.getResources().getDrawable(R.drawable.oyster);
        Drawable frozen_tilapia = this.getResources().getDrawable(R.drawable.frozen_tilapia);
        Drawable dilis = this.getResources().getDrawable(R.drawable.dilis);
        Drawable bisogo = this.getResources().getDrawable(R.drawable.driedfishbisogo);
        Drawable tuna = this.getResources().getDrawable(R.drawable.frozen_tuna);
        Drawable[] images = {shrimp, crabs, fish, squid, lobster, clamps, guso,oyster,frozen_tilapia,dilis,bisogo,tuna};
//        Bitmap bit_shrimp = ((BitmapDrawable) shrimp).getBitmap();
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
        String[] description = {"Shrimp", "Crabs", "Fish", "Squid", "Lobster", "Clamps",
                                "Guso","Oyster","Frozen Tilapia","Dilis Fish","Dried Fish Bisogo",
                                "Frozen Tuna"};
        String[] price = {"400.00", "400.00", "350.00", "380.00", "430.00", "400.00",
                          "120.00","125.00","380.00","130.00","200.00","370.00"};
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
//        else if(item.getItemId()==R.id.action_search){
//            AlertDialog.Builder editBuilder = new AlertDialog.Builder(Home.this);
//            editBuilder.setTitle("Search Item");
//            search_text = new EditText(getApplicationContext());
//            search_text.setInputType(InputType.TYPE_CLASS_NUMBER);
//            search_text.setHint("Search...");
//            editBuilder.setView(search_text);
//            editBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
////                    sqLiteDatabase.execSQL("UPDATE cart set quantity="+search_text.getText().toString()+" where id="+iditem+"");
////                    Toast.makeText(View_Cart.this, "Success!", Toast.LENGTH_SHORT).show();
////                    Intent intent =new Intent(View_Cart.this,View_Cart.class);
////                    startActivity(intent);
////                    finish();
//                }
//            });
//            editBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    dialogInterface.dismiss();
//                }
//            });
//            AlertDialog editDialog = editBuilder.create();
//            editDialog.show();
//        }
        return super.onOptionsItemSelected(item);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void  saveItems(String[] des, String[] pr, String[] img) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading...Please Wait");
        pd.show();
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        ajax = new Ajax();
        for(int x=0; x<des.length;x++) {
            ajax.setCustomObjectListener(new Ajax.MyCustomObjectListener() {
                @Override
                public void onsuccess(String data) {
                    //JSONArray data2;
//                    pd.dismiss();
//                    System.out.println("String image: " + cat_image);
//                    System.out.println("Image length: " + cat_image.length());
                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully added to cart!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 80);
                    toast.show();
                }

                @Override
                public void onerror() {
//                pd.dismiss();
                    Toast toast = Toast.makeText(getApplicationContext(), "Unable to connect. Please check your connection.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 80);
                    toast.show();
                }
            });
//            ajax.adddata("cart_id",AES.encrypt(Server.key,String.valueOf(cartId)).toString());
            ajax.adddata("description", des[x]);
            ajax.adddata("price", pr[x]);
            ajax.adddata("image", img[x]);
            ajax.adddata("availability", "20");
            ajax.execute(Server.address + "saveItems");
            }
        }
    }
