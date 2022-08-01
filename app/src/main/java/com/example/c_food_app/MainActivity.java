package com.example.c_food_app;

import android.animation.ValueAnimator;
import android.content.ContentValues;
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
    String[] town_name;
    String[] zipcode;
    String[] town_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteDatabase = openOrCreateDatabase("cfood.db", MODE_PRIVATE,null);
        //  sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase("cfood.db",MODE_PRIVATE,null);
        globalvars = new Globalvars(getApplicationContext(),this);
        town_data();
        createSqliteDatabase();
        insert_town();
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
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS towns(town_id INTEGER PRIMARY KEY,"+
                "town_name TEXT,"+
                "zipcode TEXT,"+
                "status TEXT)");

//        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_order(id INTEGER PRIMARY KEY,"+")");
    }

        public void insert_town(){
            ContentValues cv = new ContentValues();
            for(int count=0; count<town_name.length;count++){
                cv.put("town_name",town_name[count]);
                cv.put("zipcode",zipcode[count]);
                cv.put("status",town_status[count]);
                sqLiteDatabase.insert("towns",null,cv);
            }
        }

        public void town_data(){
            town_name= new String[]{"Alburquerque",
                    "Alicia",
                    "Anda",
                    "Antequera",
                    "Baclayon",
                    "Balilihan",
                    "Batuan",
                    "Bien Unido",
                    "Bilar",
                    "Buenavista",
                    "Calape",
                    "Candijay",
                    "Carlos P. Garica",
                    "Carmen",
                    "Catigbian",
                    "Clarin",
                    "Corella",
                    "Cortes",
                    "Dagohoy",
                    "Danao",
                    "Dauis",
                    "Dimiao",
                    "Duero",
                    "Garcia Hernandez",
                    "Guindulman",
                    "Inabanga",
                    "Jagna" ,
                    "Getafe",
                    "Lila",
                    "Loay",
                    "Loboc",
                    "Loon" ,
                    "Mabini" ,
                    "Maribojoc" ,
                    "Panglao" ,
                    "Pilar" ,
                    "Sagbayan",
                    "San Isidro",
                    "San Miguel",
                    "Sevilla",
                    "Sierra Bullones",
                    "Sikatuna",
                    "Tagbilaran City",
                    "Talibon",
                    "Talibon",
                    "Trinidad",
                    "Tubigon",
                    "Ubay",
                    "Valencia"};

            zipcode=new String[]{"6302",
                    "6314",
                    "6311",
                    "6335",
                    "6301",
                    "6342",
                    "6318",
                    "6326",
                    "6317",
                    "6333",
                    "6328",
                    "6312",
                    "6346",
                    "6319",
                    "6343",
                    "6330",
                    "6337",
                    "6341",
                    "6322",
                    "6344",
                    "6339",
                    "6305",
                    "6309",
                    "6307",
                    "6310",
                    "6332",
                    "6308",
                    "6334",
                    "6304",
                    "6303",
                    "6316",
                    "6327",
                    "6313",
                    "6336",
                    "6340",
                    "6321",
                    "6331",
                    "6345",
                    "6323",
                    "6347",
                    "6320",
                    "6338",
                    "6300",
                    "6325",
                    "6325",
                    "6324",
                    "6329",
                    "6315",
                    "6306"};
            town_status = new String[]{"1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "0",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1"};
        }
}
