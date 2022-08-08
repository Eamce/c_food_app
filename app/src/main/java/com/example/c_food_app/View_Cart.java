package com.example.c_food_app;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    EditText quantity_text;
    ArrayList<Cart> cart_list;
    Button order_btn;
    Cursor row=null;
    Msgbox msgbox;
    float finalTotal_payable=0;
    float total_payable = 0;
    float float_total = 0;
    float f_price=0;
    float f_quan=0;
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
            f_price=Float.parseFloat(price);
            f_quan=Float.parseFloat(quantity);
            finalTotal_payable=f_price*f_quan;
            total_payable += finalTotal_payable;
            text_total.setText("Total: " + total_payable);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            cart_list.add(cart = new Cart(id, decodedByte, descrption, price, String.valueOf(finalTotal_payable), quantity));
            System.out.println("Rowww" + row.getCount());
            System.out.println("total_: " + total);
            System.out.println("total_payable: " + total_payable);
        }
            Cart_Adapter cart_adapter = new Cart_Adapter(getApplicationContext(), cart_list, this);
            cartList.setAdapter(cart_adapter);
//            cartList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//                @Override
//                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
//                    int pos=position+1;
//                    AlertDialog.Builder builder = new AlertDialog.Builder(View_Cart.this);
//                    final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(View_Cart.this, android.R.layout.simple_list_item_1);
//                    arrayAdapter.add("Edit");
//                    arrayAdapter.add("Delete");
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    });
//                    builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            String strOption = arrayAdapter.getItem(i);
//                            if (strOption.equalsIgnoreCase("Edit")) {
//                                Toast.makeText(View_Cart.this, "ASDSFHSDUFSDFGSD", Toast.LENGTH_SHORT).show();
//                                confirmEdit(String.valueOf(pos));
//                            } else if (strOption.equalsIgnoreCase("Delete")) {
//                                confirmRemove(cart.getCart_id(), cart.getDescription());
////                               Toast.makeText(View_Cart.this, "DELETE BUTTON", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                    builder.show();
//                    return false;
//                }
//            });

        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgbox.showyesno( "Hello","Are you sure you want to proceed?");
                msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
                    @Override
                    public void onyes() {

                        if(row.getCount()==0){
                            order_btn.setEnabled(false);
                        }else{
                            if(globalvars.get("delivery_address").isEmpty()){
                                Intent intent = new Intent(View_Cart.this, Set_Delivery_Address.class);
                                startActivity(intent);
                            }else {
                                Intent intent = new Intent(View_Cart.this, Checkout_Details.class);
                                startActivity(intent);
                            }
                        }
                    }
                    @Override
                    public void onno() {
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
//        removePreviousDayOrder();    //Delete all orders if not equal 'now' and emp_id = "walapa"...
//        displayemployeeitems();
    }

    public void init() {
        cartList = (ListView) findViewById(R.id.list_item);
        text_total = (TextView) findViewById(R.id.text_total);
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        order_btn = (Button) findViewById(R.id.order_btn);
        cart_list = new ArrayList<Cart>();
        msgbox = new Msgbox(context);
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
                Intent intent =new Intent(View_Cart.this,View_Cart.class);
                startActivity(intent);
                finish();
                refreshList();
            }
            @Override
            public void onno() {
            }
        });
    }

    public void confirmEdit(String iditem){
        AlertDialog.Builder builder = new AlertDialog.Builder(View_Cart.this);
        builder.setIcon(R.drawable.icon_edit);
        builder.setTitle("Are you sure you want to edit quantity?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                AlertDialog.Builder editBuilder = new AlertDialog.Builder(View_Cart.this);
                builder.setTitle("Enter Quantity");
                quantity_text = new EditText(getApplicationContext());
                quantity_text.setInputType(InputType.TYPE_CLASS_NUMBER);
                quantity_text.setHint("Enter Quantity");
                editBuilder.setView(quantity_text);
                editBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqLiteDatabase.execSQL("UPDATE cart set quantity="+quantity_text.getText().toString()+" where id="+iditem+"");
                        Toast.makeText(View_Cart.this, "Success!", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(View_Cart.this,View_Cart.class);
                        startActivity(intent);
                        finish();
                    }
                });
                editBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog editDialog = editBuilder.create();
                editDialog.show();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialogg = builder.create();
        dialogg.show();
    }

    public void msgtoaster(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 80);
        toast.show();
    }

    public void refreshList() {
        ListView cartList = (ListView) findViewById(R.id.list_item);
//        int index = cartList.getFirstVisiblePosition();
//        View v = cartList.getChildAt(0);
//        int top = (v == null) ? 0 : (v.getTop() - cartList.getPaddingTop());
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
            f_price=Float.parseFloat(price);
            f_quan=Float.parseFloat(quantity);
            finalTotal_payable=f_price*f_quan;
            total_payable += finalTotal_payable;
            text_total.setText("Total: " + total_payable);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            cart_list.add(cart = new Cart(id, decodedByte, descrption, price, String.valueOf(finalTotal_payable), quantity));
            Cart_Adapter cart_adapter = new Cart_Adapter(getApplicationContext(), cart_list, this);
            cartList.setAdapter(cart_adapter);
            System.out.println("Rowww" + row.getCount());
            System.out.println("total_: " + total);
            System.out.println("total_payable: " + total_payable);
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
                                Toast.makeText(View_Cart.this, "ASDSFHSDUFSDFGSD", Toast.LENGTH_SHORT).show();
                                confirmEdit(String.valueOf(pos));
                            } else if (strOption.equalsIgnoreCase("Delete")) {
                                System.out.println("IDDDDDDD::::::"+cart.getCart_id());
                                confirmRemove(cart.getCart_id(), cart.getDescription());

//                               Toast.makeText(View_Cart.this, "DELETE BUTTON", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.show();
                    return false;
                }
            });
        registerForContextMenu(cartList);
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.menu_main_setting) {
            } else if(item.getItemId()==R.id.logout){
                msgbox.showyesno( "Hello","Are you sure you want to log out?");
                msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
                    @Override
                    public void onyes() {
                        Intent logout = new Intent(View_Cart.this, Login.class);
                        startActivity(logout);
                        globalvars.logout();
                    }
                    @Override
                    public void onno() {
                    }
                });
            }else if(item.getItemId()==R.id.account){
                Intent intent = new Intent(View_Cart.this, Edit_My_Account.class);
                startActivity(intent);
            }else if(item.getItemId()==R.id.home){
                Intent intent = new Intent(View_Cart.this, Home.class);
                startActivity(intent);
            }
            return super.onOptionsItemSelected(item);
        }
}