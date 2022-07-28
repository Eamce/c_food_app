package com.example.c_food_app;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
public class MainActivity extends AppCompatActivity {
    LottieAnimationView animationView;
    Globalvars globalvars;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteDatabase = openOrCreateDatabase("cfood.db", MODE_PRIVATE,null);
        //  sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase("cfood.db",MODE_PRIVATE,null);
        globalvars = new Globalvars(getApplicationContext(),this);
        createSqliteDatabase();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView=(LottieAnimationView) findViewById(R.id.animationView);
                // Custom animation speed or duration.
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
                animator.addUpdateListener(animation -> { animationView.setProgress((Float) animation.getAnimatedValue()); });
                animator.start();
                LottieAnimationView animationView = findViewById(R.id.animationView);
                animationView.addAnimatorUpdateListener((animation) -> {
                    // Do something.
                    if (isConnected()) {
                        Intent intent = new Intent(MainActivity.this, Home.class);
                        startActivity(intent);
                        finish();
//                        if(globalvars.get("id").isEmpty()){
//                            Intent intent = new Intent(MainActivity.this, Login.class);
//                            startActivity(intent);
//                            finish();
//                        }else{
//                            Intent intent = new Intent(MainActivity.this, Home.class);
//                            startActivity(intent);
//                            finish();
//                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
                animationView.playAnimation();
                if (animationView.isAnimating()) {
//                    if (isConnected()) {
//                        Intent intent = new Intent(MainActivity.this, Login.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
//                    }
                }
                finish();
            }
        },5000);
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    public void createSqliteDatabase(){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS cart(id INTEGER PRIMARY KEY,"+
                "cart_id TEXT,"+
                "description TEXT,"+
                "price TEXT,"+
                "quantity TEXT,"+
                "total TEXT,"+
                "cat_image TEXT,"+
                "prod_id TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_order(id INTEGER PRIMARY KEY,"+
                "description TEXT,"+
                "price TEXT,"+
                "quantity TEXT,"+
                "total TEXT,"+
                "cat_image TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_sub_order(id INTEGER PRIMARY KEY,"+
                "customer_name TEXT,"+
                "customer_address TEXT,"+
                "customer_contact TEXT,"+
                "description TEXT,"+
                "price TEXT,"+
                "quantity TEXT,"+
                "total TEXT,"+
                "cat_image TEXT)");
//        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_order(id INTEGER PRIMARY KEY,"+")");
    }
}
