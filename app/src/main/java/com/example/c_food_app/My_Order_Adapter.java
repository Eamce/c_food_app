package com.example.c_food_app;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;

public class My_Order_Adapter extends ArrayAdapter<My_Order_Class> {

    private Activity activity;
    private ArrayList<My_Order_Class> cart_list;
    private Context context;
    SQLiteDatabase.CursorFactory MODE;
    EditText quantity_text;
    LayoutInflater inflater;
    ImageView prod_image,edit_btn;
    TextView desc,price,quantity,total;

    public My_Order_Adapter(@NonNull Context context, ArrayList cart_list, Activity activity) {
        super(context, 0,cart_list);
        this.activity = activity;
        this.cart_list = cart_list;
        this.context = context;
    }

    @Nullable
    @Override
    public My_Order_Class getItem(int position) {
        return super.getItem(position);
    }
    @Override
    public int getPosition(@Nullable My_Order_Class item) {
        return super.getPosition(item);
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View cart_view = convertView;
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View total_view = null;
        SQLiteDatabase sqLiteDatabase ;
        String path = getContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path,MODE,null);
        if(cart_view==null){
            cart_view = LayoutInflater.from(getContext()).inflate(R.layout.checkout_layout, parent,false);
//                View child = getLayoutInflater().inflate(R.layout.child, null);
        }
        My_Order_Class my_order_class = getItem(position);
//        total_view = inflater.inflate(R.layout.cart_layout,null);
        ImageView imageView = (ImageView) cart_view.findViewById(R.id.prod_image);
        TextView desc       = (TextView)  cart_view.findViewById(R.id.desc);
        TextView price      = (TextView)  cart_view.findViewById(R.id.price);
        TextView total      = (TextView)  cart_view.findViewById(R.id.total);
        TextView quantity   = (TextView)  cart_view.findViewById(R.id.quantity);
//        ImageView edit_btn  = (ImageView) cart_view.findViewById(R.id.edit_btn);
//        ImageView delete_btn= (ImageView) cart_view.findViewById(R.id.delete_btn);
//        CheckBox checkBox   = (CheckBox)  cart_view.findViewById(R.id.checkBox);
//        Button order_btn =(Button) total_view.findViewById(R.id.order_btn);
        imageView.setImageBitmap(my_order_class.getCat_image());
        desc.setText("Description: "+my_order_class.getDescription());
        price.setText(" Price: "+my_order_class.getPrice());
        total.setText("Total: "+my_order_class.getTotal());
        quantity.setText("Quantity: "+my_order_class.getQuantity());
        return cart_view;
    }
}
