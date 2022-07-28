package com.example.c_food_app;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class Cart_Adapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Cart> cart_list;
    private Context context;
    FragmentActivity fragmentActivity;
    SQLiteDatabase.CursorFactory MODE;
    EditText quantity_text;

    public Cart_Adapter(Activity activity, ArrayList<Cart> cart_list, Context context) {
        this.activity = activity;
        this.cart_list = cart_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
