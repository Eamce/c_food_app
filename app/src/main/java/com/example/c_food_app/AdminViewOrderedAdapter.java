package com.example.c_food_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AdminViewOrderedAdapter extends ArrayAdapter<AdminViewOrderedClass> {

    ArrayList<AdminViewOrderedClass> order_list;
    Activity activity;
    Context context;
    ImageView prod_image;
    TextView desc,price,quantity,total;
    public AdminViewOrderedAdapter(@NonNull Context context, ArrayList order_list, Activity activity) {
        super(context, 0,order_list);
        this.context=context;
        this.activity=activity;
        this.order_list=order_list;
    }

    @Nullable
    @Override
    public AdminViewOrderedClass getItem(int position) {
        return super.getItem(position);
    }
    @Override
    public int getPosition(@Nullable AdminViewOrderedClass item) {
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
            view = LayoutInflater.from(getContext()).inflate(R.layout.admin_view_ordered_layout, parent,false);
        }
            AdminViewOrderedClass avdc = getItem(position);
                 prod_image = (ImageView) view.findViewById(R.id.prod_image);
                 desc       = (TextView) view.findViewById(R.id.desc);
                 price      = (TextView) view.findViewById(R.id.price);
                 quantity   = (TextView) view.findViewById(R.id.quantity);
                 total      = (TextView) view.findViewById(R.id.total);
                 total.setText("Total :"+avdc.getTotal());
                 desc.setText(avdc.getDescription());
                 price.setText(avdc.getPrice());
                 quantity.setText(avdc.getQuantity());
        return view;
    }
}
