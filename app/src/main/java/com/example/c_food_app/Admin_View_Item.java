package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.widget.GridView;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Admin_View_Item extends AppCompatActivity {
    ArrayList<AdminViewItemClass> adminViewItemClasses;
    Bitmap bitmap_image;
    Drawable[] images;
    String[] description;
    String[] price;
    String str_img;
    ListView list_item;
    GridView gridView;
    Drawable shrimp,crabs,fish,squid, lobster ,clamps ,guso, oyster, frozen_tilapia ,dilis ,bisogo ,tuna ,sardines, scallops ,froze_scallops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_item);
        init();
        for(int i=0;i<images.length;i++){
            Drawable drawable = images[i];
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            str_img= getStringImage(bitmap);
            //String cat_name, String cat_img, String price, String prod_id)
            adminViewItemClasses.add(new AdminViewItemClass(description[i],str_img,price[i],String.valueOf(i)));
            AdminViewItemAdapter adapter = new AdminViewItemAdapter(getApplicationContext(),adminViewItemClasses,this);
            list_item.setAdapter(adapter);
        }
    }

    public void init(){
        adminViewItemClasses = new ArrayList<AdminViewItemClass>();
        list_item = (ListView) findViewById(R.id.list_item);
//        GridView gridView = (GridView) findViewById(R.id.gridview);
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
        images         = new Drawable[]{shrimp, crabs, fish, squid, lobster, clamps, guso, oyster, frozen_tilapia, dilis, bisogo, tuna, sardines, scallops, froze_scallops};
        description    = new String[]{"Shrimp", "Crabs", "Fish", "Squid", "Lobster", "Clamps", "Guso", "Oyster", "Frozen Tilapia", "Dilis Fish", "Dried Fish Bisogo", "Frozen Tuna", "Frozen Sardines", "Scallops", "Frozen Scallops"};
        price          = new String[]{"400.00", "400.00", "350.00", "380.00", "430.00", "400.00", "120.00", "125.00", "380.00", "130.00", "200.00", "370.00", "280.00", "250.00", "320.00"};
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}