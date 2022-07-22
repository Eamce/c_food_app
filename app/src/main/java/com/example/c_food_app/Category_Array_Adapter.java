//package com.example.c_food_app;
//
//import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;
//
//import android.app.AlertDialog;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.text.InputType;
//import android.util.Base64;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import java.io.ByteArrayOutputStream;
//import java.util.ArrayList;
//
//public class Category_Array_Adapter extends ArrayAdapter<Category> {
//    Ajax ajax = new Ajax();
//    SQLiteDatabase.CursorFactory MODE;
//    int cartId = 0;
//    EditText quantity_text;
//    Context context;
//    int finalQuan=0;
//    String str_final_quan;
//    ContentValues cv = new ContentValues();
//    public Category_Array_Adapter(@NonNull Context context, ArrayList<Category> categoryArrayList) {
// //      super(context,R.layout.fragment_home,categoryArrayList);
////      this.context = context;
//        super(context,0, categoryArrayList);
//        this.context = context;
//    }
//    @Override
//    public int getCount() {
//        return super.getCount();
//    }
//    @Nullable
//    @Override
//    public Category getItem(int position) {
//        return super.getItem(position);
//    }
//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View categoryListView = convertView;
//        SQLiteDatabase sqLiteDatabase ;
////        View cart_view = LayoutInflater.from(getContext()).inflate(R.layout.cart_fragment, parent, false);
////        Home_Fragment home_fragment = new Home_Fragment();
//        String path = getContext().getDatabasePath("cfood.db").getPath();
//        sqLiteDatabase = openOrCreateDatabase(path,MODE,null);
//        Bundle args = new Bundle();
//        if(categoryListView==null){
//            categoryListView = LayoutInflater.from(getContext()).inflate(R.layout.category_grid,parent,false);
//        }
//        Category category   = getItem(position);
//        ImageButton cat_img = categoryListView.findViewById(R.id.cat_img);
//        TextView img_desc   = categoryListView.findViewById(R.id.cat_description);
//        TextView price      = categoryListView.findViewById(R.id.price);
//        ImageButton cart    = categoryListView.findViewById(R.id.add_to_cart);
//        cart.setImageResource(category.getCart_icon_add());
//        cat_img.setImageDrawable(category.getImg());
//        img_desc.setText(category.getCat_name());
//        price.setText(category.getPrice());
//        cart.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                   for(int x=0;x<getCount();x++){
//                       if(x==position){
//                           AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                           builder.setTitle("Enter how many kilo/s");
//                           quantity_text = new EditText(getContext());
//                           quantity_text.setInputType(InputType.TYPE_CLASS_NUMBER);
//                           builder.setView(quantity_text);
//                           builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                               @Override
//                               public void onClick(DialogInterface dialog, int which)
//                               {
//                                   int inputted_quan = Integer.parseInt(quantity_text.getText().toString());
//                                   Cursor row =sqLiteDatabase.rawQuery("select * from cart where prod_id = "+category.getProd_id()+"",null);
//                                  if(row.getCount()>0){
//                                      Toast.makeText(context, "Item is already in the cart.", Toast.LENGTH_SHORT).show();
////                                      while (row.moveToNext()){
////                                          String quan = row.getString(4);
////                                          int int_quan = Integer.parseInt(quan);
////                                          finalQuan=int_quan+inputted_quan;
////                                          str_final_quan=String.valueOf(finalQuan);
////                                          System.out.println("FINALQUAN" +str_final_quan);
////                                          System.out.println(" String quan = row.getString(4); " +row.getString(4));
////                                          sqLiteDatabase.rawQuery("UPDATE cart set quantity = "+str_final_quan+" where prod_id = "+category.getProd_id()+"",null);
////                                      }
//                                  }else{
//                                      Drawable drawable = category.getImg();
//                                      Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//                                      int quant= Integer.parseInt(quantity_text.getText().toString());
//                                      double price=Double.parseDouble(category.getPrice());
//                                      double total = quant *price;
//                                      cv.put("cart_id",cartId);
//                                      cv.put("description",category.getCat_name());
//                                      cv.put("price",category.getPrice());
//                                      cv.put("cat_image",getStringImage(bitmap));
//                                      cv.put("quantity",quantity_text.getText().toString());
//                                      cv.put("total",total);
//                                      cv.put("prod_id",category.getProd_id());
//                                      sqLiteDatabase.insert("cart",null,cv);
//                                      Toast.makeText(context, "Successfully added to cart!", Toast.LENGTH_SHORT).show();
//                                      cartId++;
//                                  }
//                               }
//                           });
//                           builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
//                           {
//                               @Override
//                               public void onClick(DialogInterface dialog, int which) {
//                                   dialog.cancel();
//                               }
//                           });
//                           builder.show();
//                       }
//                   }
//            }
//        });
//        return categoryListView;
//    }
//    public String getStringImage(Bitmap bmp){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        return encodedImage;
//    }
//}
