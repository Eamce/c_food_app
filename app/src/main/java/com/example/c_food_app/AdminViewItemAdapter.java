package com.example.c_food_app;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class AdminViewItemAdapter extends ArrayAdapter<AdminViewItemClass> {

    public AdminViewItemAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

}
