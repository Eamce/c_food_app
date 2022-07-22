package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.SyncFailedException;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    Ajax ajax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ajax = new Ajax();
        GridView category_view;
        ArrayList<Category> categoryArrayList = new ArrayList<Category>();
        category_view = (GridView) findViewById(R.id.grid_categories);
        Drawable shrimp = this.getResources().getDrawable(R.drawable.shrimp);
        Drawable crabs = this.getResources().getDrawable(R.drawable.crabs);
        Drawable fish = this.getResources().getDrawable(R.drawable.fish);
        Drawable squid = this.getResources().getDrawable(R.drawable.squid);
        Drawable lobster = this.getResources().getDrawable(R.drawable.lobster);
        Drawable clamps = this.getResources().getDrawable(R.drawable.clamps);
        Drawable guso = this.getResources().getDrawable(R.drawable.guso);
        Drawable[] images = {shrimp, crabs, fish, squid, lobster, clamps, guso};
        Bitmap bit_shrimp = ((BitmapDrawable) shrimp).getBitmap();
        Bitmap bit_crabs = ((BitmapDrawable) crabs).getBitmap();
        Bitmap bit_fish = ((BitmapDrawable) fish).getBitmap();
        Bitmap bit_squid = ((BitmapDrawable) squid).getBitmap();
        Bitmap bit_lobster = ((BitmapDrawable) lobster).getBitmap();
        Bitmap bit_clamps = ((BitmapDrawable) clamps).getBitmap();
        Bitmap bit_guso = ((BitmapDrawable) guso).getBitmap();
        String str_shrimp = getStringImage(bit_shrimp);
        String str_scrabs = getStringImage(bit_crabs);
        String str_fish = getStringImage(bit_fish);
        String str_squid = getStringImage(bit_squid);
        String str_lobster = getStringImage(bit_lobster);
        String str_clamps = getStringImage(bit_clamps);
        String str_guso = getStringImage(bit_guso);
        //  int[] images={R.drawable.shrimp,R.drawable.crabs,R.drawable.fish,R.drawable.squid,R.drawable.lobster,R.drawable.lobster,R.drawable.clamps,R.drawable.clamps};
        String[] description = {"Shrimp", "Crabs", "Fish", "Squid", "Lobster", "Clamps", "Guso"};
        String[] price = {"400.00", "400.00", "350.00", "380.00", "430.00", "400.00", "120.00"};
        String[] str_images={str_shrimp,str_scrabs,str_fish,str_squid,str_lobster,str_clamps,str_guso};
        Category_Adapter adapter = new Category_Adapter(getApplicationContext(), images, description, price, this);
        category_view.setAdapter(adapter);
//        saveItems(description,price,str_images);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_main_setting) {
            Toast.makeText(this, "ADASDADADAD", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu_main_cart) {
            Intent cart = new Intent(this, Cart.class);
            startActivity(cart);
            Toast.makeText(this, "SSFJGFGFDG", Toast.LENGTH_SHORT).show();
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
//                System.out.println("String image: " + cat_image);
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
