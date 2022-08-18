package com.example.c_food_app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdminViewItemAdapter extends ArrayAdapter<AdminViewItemClass> {
    Context context;
    ArrayList item_list;
    Activity activity;
    public AdminViewItemAdapter(@NonNull Context context, ArrayList item_list, Activity activity) {
        super(context, 0,item_list);
        this.activity = activity;
        this.item_list = item_list;
        this.context = context;
    }

    @Nullable
    @Override
    public AdminViewItemClass getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable AdminViewItemClass item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.admin_view_item_layout, parent,false);
        }

        AdminViewItemClass avic = getItem(position);
        ImageButton cat_img = view.findViewById(R.id.cat_img);
        TextView img_desc   = view.findViewById(R.id.cat_description);
        TextView price      = view.findViewById(R.id.price);
        byte[] decodedString = Base64.decode(avic.getCat_img(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        cat_img.setImageBitmap(decodedByte);
        img_desc.setText(avic.getCat_name());
        price.setText(avic.getPrice());
      return view;
    }
}
