package com.example.c_food_app;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class View_Cart extends AppCompatActivity {
    ListView cartList;
    TextView text_total;
    SQLiteDatabase sqLiteDatabase;
    String cartId = "";
    String descrption = "";
    String price = "";
    String image = "";
    String quantity = "";
    String total = "";
    String id="";
    Globalvars globalvars;
    ArrayList<Cart> cart_list;
    Button order_btn;
    Cursor row=null;
    Msgbox msgbox;
    float finalTotal_payable;
    float total_payable = 0;
    float float_total = 0;
    Context context = this;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        init();
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
            cart_list.add(cart = new Cart(id, decodedByte, descrption, price, total, quantity));
            Cart_Adapter cart_adapter = new Cart_Adapter(getApplicationContext(), cart_list, this);
            cartList.setAdapter(cart_adapter);
        }

            cartList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                    int pos=position+1;
                    AlertDialog.Builder builder = new AlertDialog.Builder(View_Cart.this);
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(View_Cart.this, android.R.layout.simple_list_item_1);
                    arrayAdapter.add("Edit");
                    arrayAdapter.add("Delete");
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String strOption = arrayAdapter.getItem(i);
                            if (strOption.equalsIgnoreCase("Edit")) {
                            } else if (strOption.equalsIgnoreCase("Delete")) {
                                confirmRemove(String.valueOf(pos), cart.getDescription());
//                               Toast.makeText(View_Cart.this, "DELETE BUTTON", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.show();
                    return false;
                }
            });
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View_Cart.this, Checkout_Details.class);
                startActivity(intent);
            }
        });
    }

    public void init() {
        cartList = (ListView) findViewById(R.id.list_item);
        text_total = (TextView) findViewById(R.id.text_total);
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        order_btn = (Button) findViewById(R.id.order_btn);
        cart_list = new ArrayList<Cart>();
        globalvars = new Globalvars(getApplicationContext(), this);
        row = sqLiteDatabase.rawQuery("select * from cart", null);
        msgbox = new Msgbox(context);
    }

    public void confirmRemove(String id, String name) {
        final String fid = id;
        final String fname = name;
        System.out.println("IDIDIDIDIDIDI: "+id);
        msgbox.showyesno( "Warning!","Remove this item?");
        msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
            @Override
            public void onyes() {
                sqLiteDatabase.execSQL("DELETE FROM cart WHERE id="+id);
                //mydatabase.execSQL("DELETE FROM employeeitems_backup WHERE eb_emp_id="+qttext(fid));
                msgtoaster("Successfully removed.");
                refreshList();
//                displayemployeelogs();
            }

            @Override
            public void onno() {
            }
        });
    }

    public void msgtoaster(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 80);
        toast.show();
    }

    public void refreshList() {
        while (row.moveToNext()) {
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
            cart_list.add(cart = new Cart(cartId, decodedByte, descrption, price, total, quantity));
            Cart_Adapter cart_adapter = new Cart_Adapter(getApplicationContext(), cart_list, this);
            cartList.setAdapter(cart_adapter);
        }
            cartList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(View_Cart.this);
                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(View_Cart.this, android.R.layout.simple_list_item_1);
                    arrayAdapter.add("Edit");
                    arrayAdapter.add("Delete");
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String strOption = arrayAdapter.getItem(i);
                            if (strOption.equalsIgnoreCase("Edit")) {
                            } else if (strOption.equalsIgnoreCase("Delete")) {
                                confirmRemove(cart.getCart_id(), cart.getDescription());
//                               Toast.makeText(View_Cart.this, "DELETE BUTTON", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.show();
//
                    return false;
                }
            });

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.menu_main_setting) {
                Toast.makeText(this, "ADASDADADAD", Toast.LENGTH_SHORT).show();
            } else if (item.getItemId() == R.id.menu_main_cart) {
                Intent cart = new Intent(this, My_Account.class);
                startActivity(cart);
                Toast.makeText(this, "SSFJGFGFDG", Toast.LENGTH_SHORT).show();
            }else if(item.getItemId()==R.id.logout){
                Intent logout = new Intent(this, Login.class);
                startActivity(logout);
                globalvars.logout();
            }
            return super.onOptionsItemSelected(item);
        }
}