package com.example.c_food_app;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class AdminViewOrderedAdapter extends ArrayAdapter<AdminViewOrderedClass> {

    ArrayList<AdminViewOrderedClass> order_list;
    Activity activity;
    Context context;
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

       return view;
    }
}
