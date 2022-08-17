package com.example.c_food_app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
        return super.getView(position, convertView, parent);
    }
}
