package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Checkout_Details extends AppCompatActivity {

        Button checkout_btn;
        SQLiteDatabase sqLiteDatabase;
        TextView address;
        List<String> towns;
        Cursor row=null;
        ListView checkout_list;
        String cartId = "";
        String descrption = "";
        String price = "";
        String image = "";
        String quantity = "";
        String total = "";
        String id="";
        TextView text_total;
        float total_payable = 0;
        float float_total = 0;
        Context context = this;
        Checkout checkout;
        ArrayList<Checkout> checkout_Array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_details);
        init();
//            getTown();

        address.setText("Mandaug \n" +
                        "Calape \n" +
                        "Bohol, 6328");

        while (row.moveToNext()) {
            id = row.getString(0);
            cartId = row.getString(1);
            descrption = row.getString(2);
            price = row.getString(3);
            quantity = row.getString(4);
            total = row.getString(5);
            image = row.getString(6);
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            float_total = Float.parseFloat(total);
            total_payable += float_total;
            System.out.println("Rowww" + row.getCount());
            System.out.println("total_: " + total);
            System.out.println("total_payable: " + total_payable);
            text_total.setText("Total: " + total_payable);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            checkout_Array.add(new Checkout(id, decodedByte, descrption, price, total, quantity));
            Checkout_Adapter checkout_adapter = new Checkout_Adapter(getApplicationContext(), checkout_Array, this);
            checkout_list.setAdapter(checkout_adapter);
        }
        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(fullname.getText().toString().isEmpty() || address.getText().toString().isEmpty() ){
//                    Toast.makeText(Checkout_Details.this, "Please fill up information!", Toast.LENGTH_SHORT).show();
//                }else{
//                }
            }
        });
    }

    public void init(){
//        fullname = (EditText) findViewById(R.id.fullname);
        address  = (TextView) findViewById(R.id.address);
        checkout_btn = (Button) findViewById(R.id.checkout_btn);
        checkout_list = (ListView) findViewById(R.id.checkout_list);
        text_total = (TextView) findViewById(R.id.text_total);
        checkout_Array=new ArrayList<Checkout>();
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        row = sqLiteDatabase.rawQuery("select * from cart", null);
    }

    public List<String> getTown(){
        List<String> town = new ArrayList<String>();
        Cursor row=null;
        row = sqLiteDatabase.rawQuery("Select town_name from towns where status = '1'",null);
        System.out.println("sdfsdfsdfsdf"+row);
            while (row.moveToNext()){
                town.add(row.getString(1));
        }
        return town;
    }
}




