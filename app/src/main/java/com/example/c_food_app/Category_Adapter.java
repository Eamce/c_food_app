package com.example.c_food_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;

public class Category_Adapter extends BaseAdapter {

    LayoutInflater inflater;
    Drawable images[];
//    int images[];
    String description[];
    String prices[];
    Context context;
    Globalvars globalvars;
    Activity act;
//    public Category_Adapter(@NonNull Context context, int resource) {
//        super(context, resource);
//    }

    public Category_Adapter(Context context, Drawable[] images, String[] description, String[] prices,Activity act) {
        this.context = context;
        this.inflater = (LayoutInflater.from(context));
        this.images = images;
        this.description = description;
        this.prices = prices;
        this.act=act;
    }
    @Override
    public int getCount() {
        return description.length;
    }
    @Override
    public Object getItem(int i) {
        return i;
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.category_grid,null);
        globalvars=new Globalvars(context,act);
        ImageButton cat_img = view.findViewById(R.id.cat_img);
        TextView img_desc   = view.findViewById(R.id.cat_description);
        TextView price      = view.findViewById(R.id.price);
        cat_img.setImageDrawable(images[position]);
        img_desc.setText(description[position]);
        price.setText(prices[position]);
        cat_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<getCount();i++){
                    if(i==position){
                        Drawable drawable = images[i];
                        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                        String str_img=getStringImage(bitmap);
                        Intent intent = new Intent(context, View_Item.class);
                        intent.putExtra("description",description[i]);
                        intent.putExtra("price", prices[i]);
                        globalvars.set("image",str_img);
                        context.startActivity(intent);
                        System.out.println("DESCRIPTION: " +description[i]);
                        System.out.println("DESCRIPTION: " +prices[i]);
                    }
                }

            }
        });
        return view;
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
