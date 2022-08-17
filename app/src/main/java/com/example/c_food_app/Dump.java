package com.example.c_food_app;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.Gravity;
import android.widget.Toast;

public class Dump {

//    byte[] decodedString = Base64.decode(str_img, Base64.DEFAULT);
//    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//    public void  saveItems(String[] des, String[] pr, String[] img) {
//        final ProgressDialog pd = new ProgressDialog(this);
//        pd.setMessage("Uploading...Please Wait");
//        pd.show();
//        pd.setCancelable(false);
//        pd.setCanceledOnTouchOutside(false);
//        ajax = new Ajax();
//        for(int x=0; x<des.length;x++) {
//            ajax.setCustomObjectListener(new Ajax.MyCustomObjectListener() {
//                @Override
//                public void onsuccess(String data) {
//                    Toast toast = Toast.makeText(getApplicationContext(), "Successfully added to cart!", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 80);
//                    toast.show();
//                }
//                @Override
//                public void onerror() {
//                    Toast toast = Toast.makeText(getApplicationContext(), "Unable to connect. Please check your connection.", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 80);
//                    toast.show();
//                }
//            });
//            ajax.adddata("description", des[x]);
//            ajax.adddata("price", pr[x]);
//            ajax.adddata("image", img[x]);
//            ajax.adddata("availability", "20");
//            ajax.execute(Server.address + "saveItems");
//        }
//    }
}
