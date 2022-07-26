package com.example.c_food_app;

import android.animation.ValueAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    LottieAnimationView animationView;
    Globalvars globalvars;
    SQLiteDatabase sqLiteDatabase;
    String[] town_name;
    String[] zipcode;
    String[] town_status;
    String[] barangay;
    String[] town_id;
    ContentValues values;
    Drawable[] images;
    String[] description;
    String[] price;
    Drawable shrimp,crabs,fish,squid, lobster ,clamps ,guso, oyster, frozen_tilapia ,dilis ,bisogo ,tuna ,sardines, scallops ,froze_scallops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteDatabase = openOrCreateDatabase("cfood.db", MODE_PRIVATE,null);
        //  sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase("cfood.db",MODE_PRIVATE,null);
        globalvars = new Globalvars(getApplicationContext(),this);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView=(LottieAnimationView) findViewById(R.id.animationView);
                // Custom animation speed or duration.
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
                animator.addUpdateListener(animation -> { animationView.setProgress((Float) animation.getAnimatedValue()); });
                animator.start();
                LottieAnimationView animationView = findViewById(R.id.animationView);
                createSqliteDatabase();
                init();
                town_data();
                insert_town();
                deleteitems();
                insertItems();
                animationView.addAnimatorUpdateListener((animation) -> {
                    // Do something.
                    if (isConnected()) {
                        if(globalvars.get("id").isEmpty()){
                            if(globalvars.get("user").isEmpty()){
                                Intent intent = new Intent(MainActivity.this, Login.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Intent intent = new Intent(MainActivity.this, Admin_Home.class);
                                startActivity(intent);
                                finish();
                            }
                        }else{
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            startActivity(intent);
                            finish();
                        }
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

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void init(){
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        globalvars = new Globalvars(getApplicationContext(),this);
        values = new ContentValues();
        shrimp         = this.getResources().getDrawable(R.drawable.shrimp);
        crabs          = this.getResources().getDrawable(R.drawable.crabs);
        fish           = this.getResources().getDrawable(R.drawable.fish);
        squid          = this.getResources().getDrawable(R.drawable.squid);
        lobster        = this.getResources().getDrawable(R.drawable.lobster);
        clamps         = this.getResources().getDrawable(R.drawable.clamps);
        guso           = this.getResources().getDrawable(R.drawable.guso);
        oyster         = this.getResources().getDrawable(R.drawable.oyster);
        frozen_tilapia = this.getResources().getDrawable(R.drawable.frozen_tilapia);
        dilis          = this.getResources().getDrawable(R.drawable.dilis);
        bisogo         = this.getResources().getDrawable(R.drawable.driedfishbisogo);
        tuna           = this.getResources().getDrawable(R.drawable.frozen_tuna);
        sardines       = this.getResources().getDrawable(R.drawable.frozen_sardines);
        scallops       = this.getResources().getDrawable(R.drawable.scallops);
        froze_scallops = this.getResources().getDrawable(R.drawable.frozen_scallops);
        images = new Drawable[]{shrimp, crabs, fish, squid, lobster, clamps, guso, oyster, dilis, tuna, sardines, scallops, froze_scallops};
        description = new String[]{"Shrimp", "Crabs", "Fish", "Squid", "Lobster", "Clamps", "Guso", "Oyster", "Dilis Fish", "Frozen Tuna", "Frozen Sardines", "Scallops", "Frozen Scallops"};
        price = new String[]{"400.00", "400.00", "350.00", "380.00", "430.00", "400.00", "120.00", "125.00", "130.00", "370.00", "280.00", "250.00", "320.00"};
    }
    public void insertItems(){
        for(int i =0;i<images.length;i++){
            Drawable drawable = images[i];
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            values.put("image",getStringImage(bitmap));
            values.put("description",description[i]);
            values.put("price",price[i]);
            values.put("quantity","20");
            sqLiteDatabase.insert("items",null,values);
        }
    }

    public void deleteitems(){
        sqLiteDatabase.delete("items",null,null);
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
                "user_id TEXT,"+
                "username TEXT,"+
                "address TEXT,"+
                "contact TEXT,"+
                "status TEXT,"+
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
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS user(user_id INTEGER PRIMARY KEY,"+
                "email TEXT,"+
                "name TEXT,"+
                "phone TEXT,"+
                "password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS items(id INTEGER PRIMARY KEY,"+
                "description TEXT,"+
                "price TEXT,"+
                "quantity TEXT,"+
                "image TEXT)");
//        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tbl_order(id INTEGER PRIMARY KEY,"+")");
    }
        public void insert_town(){
            sqLiteDatabase.rawQuery("DELETE FROM towns",null);
            ContentValues cv = new ContentValues();
            for(int count=0; count<town_name.length;count++){
                cv.put("town_name",town_name[count]);
                cv.put("zipcode",zipcode[count]);
                cv.put("status",town_status[count]);
                sqLiteDatabase.insert("towns",null,cv);
            }
        }

        public void town_data(){
            town_id=new String[]{"1", "1", "1", "1", "1", "6", "1", "1", "1", "1", "1", "42", "42", "2", "2", "2", "2", "2", "2", "42", "2", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3",
                    "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "5", "5", "5",
                    "4", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5", "5",
                    "6",
                    "6",
                    "6",
                    "6",
                    "6",
                    "6",
                    "6", "6", "6", "6", "6", "6", "6", "6", "6", "6",
                    "6", "6", "6",
                    "6",
                    "25",
                    "25",
                    "25",
                    "25",
                    "25",
                    "23",
                    "23",
                    "25",
                    "25",
                    "25",
                    "25",
                    "49",
                    "49", "49",
                    "8",
                    "8", "8", "8", "0", "0", "0", "0", "8", "8",
                    "8", "8","8", "8",
                    "8",
                    "8",
                    "8",
                    "8",
                    "8",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9",
                    "9", "9", "9", "9", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11",
                    "11",
                    "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11", "12", "12", "12", "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "12",
                    "13",
                    "13",
                    "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "13", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "14", "15", "15", "15", "15", "15", "15", "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "15",
                    "16",
                    "16",
                    "16",
                    "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "16", "35", "35", "35", "35", "35", "35", "35", "35", "35", "43", "43", "43", "43", "43", "27", "24", "34", "34", "34", "43", "43", "43", "43", "43", "43", "43", "43", "43", "43", "1", "42", "42", "42", "42", "42", "42", "42", "42", "21", "21", "21", "21", "21", "21", "21", "21", "21", "21", "21", "21", "17", "17", "17", "17", "17", "17", "17", "17", "18", "18", "18", "18", "18", "18", "18", "18", "18", "18", "18", "18", "18", "18", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "35", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "34", "6", "6", "6", "6", "6", "6", "6", "6", "6", "6", "6",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32",
                    "32","32",
                    "32", "32", "32", "32", "32", "32", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47", "47",
                    "47", "47", "47", "47", "47", "47", "47", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31", "31","31", "31", "31", "31", "31", "29", "29",
                    "29", "29", "29", "29", "29", "29", "29", "29", "29", "29", "29", "29", "29", "29", "29", "29", "32", "35", "35", "2", "2", "2", "2", "2", "2", "2", "2", "3",
                    "7", "7", "7", "7", "7", "7", "7", "7", "7", "7", "7", "7", "7", "7", "7", "22", "22", "22", "22", "22", "22", "22", "22", "22", "22", "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "22",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "23",
                    "24",
                    "24",
                    "24",
                    "24",
                    "24",
                    "24",
                    "24",
                    "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "24", "25", "25",
                    "25", "25", "25", "25", "25", "25", "25", "25", "25", "27", "27", "27", "27", "27", "27", "27", "27", "27", "27", "27", "27", "27", "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "27",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "33",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "36",
                    "40",
                    "40",
                    "40",
                    "40",
                    "40",
                    "40",
                    "40",
                    "40",
                    "40",
                    "40",
                    "40",
                    "40",
                    "40",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "41",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "49",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "19",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "20",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "28",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26",
                    "26", "26", "26", "26", "26",
                    "26", "26", "26", "26", "26", "26", "26","26", "26", "26", "26", "26", "26", "26", "13", "37", "37", "37", "37", "37", "37",
                    "37", "37", "37", "37", "37", "37", "37", "37", "37", "37", "37", "37", "37", "37", "37", "37", "37", "37", "38", "38", "38", "38"};
            barangay=new String[]{"Bahi",
                    "Basacdacu",
                    "Cantinguib",
                    "Dangay",
                    "East Poblacion",
                    "Poblacion",
                    "San Agustin",
                    "Santa Felomina",
                    "Tagbuane",
                    "Toril",
                    "West Poblacion" ,
                    "Abucay Norte" ,
                    "Abucay Sur" ,
                    "Cayacay" ,
                    "Del Monte" ,
                    "Katipunan" ,
                    "La Hacienda" ,
                    "Mahayag",
                    "Napo",
                    "Poblacion",
                    "Poblacion",
                    "Almaria",
                    "Bacong",
                    "Badiang",
                    "BuenaSuerte",
                    "Candabong",
                    "Casica",
                    "Katipunan",
                    "Linawan",
                    "Lundag",
                    "Poblacion",
                    "Suba",
                    "Talisay",
                    "Tanod",
                    "Tawid",
                    "Virgen",
                    "Angilan",
                    "Bantolinao",
                    "Bicahan",
                    "Bitaugan ",
                    "Bungahan",
                    "Canlaas",
                    "Cansibuan",
                    "Can-omay",
                    "Ceiling",
                    "Danao ",
                    "Danicop",
                    "Mag-aso",
                    "Poblacion",
                    "Quinapon-an",
                    "Santo Rosario ",
                    "Tabuan",
                    "Tagubaas",
                    "Tupas",
                    "Obujan",
                    "Viga ",
                    "Villa Aurora",
                    "Payahan",
                    "Cambanac",
                    "Dasitam",
                    "sdfsdfsd",
                    "Buenaventura",
                    "Guiwanon",
                    "Landican",
                    "Laya ",
                    "Libertad",
                    "MontaÃƒÂ±a",
                    "Pamilacan",
                    "Poblacion",
                    "San Isidro ",
                    "San Roque",
                    "San Vicente",
                    "Santa Cruz",
                    "Taguihon",
                    "Tanday ",
                    "Baucan Norte",
                    "Baucan Sur",
                    "Boctol",
                    "Boyog Norte ",
                    "Boyog Proper",
                    "Boyog Sur",
                    "Cabad",
                    "Candasig",
                    "Cantalid ",
                    "Cantomimbo",
                    "Cogon",
                    "Datag Norte",
                    "Datag Sur",
                    "Del Carmen Este (Pob.) ",
                    "Del Carmen Norte (Pob.)",
                    "Del Carmen Weste (Pob.)",
                    "Del Carmen Sur (Pob.)",
                    "Del Rosario",
                    "Dorol ",
                    "Haguilanan Grande",
                    "Cabantian",
                    "Canhaway",
                    "Guinacot",
                    "Sawang",
                    "Basdio",
                    "Itum",
                    "Guinsularan",
                    "Catungawan Norte",
                    "Mayuga",
                    "Bulawan",
                    "Bayabas",
                    "Canmanico",
                    "Cantagay",
                    "Poblacion",
                    "Bilangbilangan Dako",
                    "Bilangbilangan Diot ",
                    "Hingotanan East",
                    "Hingotanan West",
                    "Liberty",
                    "Malingin",
                    "Mandawa",
                    "Maomawan",
                    "Liberty",
                    "Malingin",
                    "Mandawa",
                    "Maomawan",
                    "Nueva Esperanza",
                    "Nueva Estrella",
                    "Pinamgo",
                    "Poblacion (Bien Unido Proper",
                    "Puerto San Pedro (Lawis)",
                    "Sagasa",
                    "Tuboran",
                    "Bonifacio",
                    "Bugang Norte",
                    "Bugang Sur",
                    "Cabacnitan",
                    "Cambigsi",
                    "Campagao",
                    "Cansumbol",
                    "Dagohoy",
                    "Owac ",
                    "Poblacion",
                    "Quezon",
                    "Riverside",
                    "Rizal",
                    "Roxas",
                    "Subayon",
                    "Villa Aurora",
                    "Villa Suerte",
                    "Yanaya",
                    "Zamora",
                    "Anonang",
                    "Asinan",
                    "Bago",
                    "Baluarte",
                    "Bantuan",
                    "Bato",
                    "BonotBonot",
                    "Bugaong",
                    "Cambuhat",
                    "Cambus-oc",
                    "Cangawa",
                    "Cantomugcad",
                    "Cantores",
                    "Cantuba",
                    "Catigbian",
                    "Cawag",
                    "Cruz",
                    "Dait",
                    "Eastern Cabul-an",
                    "Hunan",
                    "Lapacan Norte",
                    "Lapacan Sur",
                    "Lubang",
                    "Lusong",
                    "Magkaya",
                    "Merryland",
                    "Nueva Granada",
                    "Nueva Montana",
                    "Overland",
                    "Panghagban",
                    "Poblacion",
                    "Putting Bato",
                    "Rufo Hill",
                    "Sweetland",
                    "Western Cabul-an",
                    "Abucayan Norte",
                    "Abucayan Sur",
                    "Banlasan",
                    "Bentig",
                    "Binogawan",
                    "Bonbon",
                    "Cabayugan",
                    "Cabudburan",
                    "Calunasan",
                    "Camias",
                    "Canguha",
                    "Catmonan",
                    "Desamparados",
                    "Kahayag",
                    "Kinabag-an",
                    "Labuon",
                    "Lawis",
                    "Liboron",
                    "Lo-oc",
                    "Lomboy",
                    "Lucob",
                    "Madangog",
                    "Magtongtong",
                    "Mandaug",
                    "Mantatao",
                    "Sampoangon",
                    "San Isidro",
                    "Santa Cruz",
                    "Sojoton",
                    "Talisay",
                    "Tinibgan",
                    "Tultugan",
                    "Ulbujan",
                    "Abilihan",
                    "Anoling",
                    "Boyo-an",
                    "Cadapdapan",
                    "Cambane",
                    "Can-olin",
                    "Canawa",
                    "Cogtong",
                    "La Union",
                    "Luan",
                    "Lungsoda-an",
                    "Mahangin",
                    "Pagahat",
                    "Panadtaran",
                    "Panas",
                    "Poblacion",
                    "San Isidro",
                    "Tambongan",
                    "Tawid",
                    "Tugas",
                    "Tubod",
                    "Aguining",
                    "Basiao",
                    "Baud",
                    "Bayog",
                    "Bogo",
                    "Bonbonon",
                    "Canmangao",
                    "Campamanog",
                    "Gaus",
                    "Kabangkalan",
                    "Lapinig",
                    "Lipata",
                    "Poblacion",
                    "Popoo",
                    "Saguise",
                    "San Jose",
                    "Santo Rosario",
                    "Tilmobo",
                    "Tugnao",
                    "Villa Milagrosa",
                    "Butan",
                    "San Vicente",
                    "Alegria",
                    "Bicao",
                    "Buenavista",
                    "Buenos Aires",
                    "Calatrava",
                    "El Progreso",
                    "El Salvador",
                    "Guadalupe",
                    "Katipunan",
                    "La Liberted",
                    "La Paz",
                    "La Salvacion",
                    "La Victoria",
                    "Matin-ao",
                    "Montehermoso",
                    "MonteSuerte",
                    "Montesunting",
                    "Montevideo",
                    "Nueva Fuerza",
                    "Nueva Vida Este",
                    "Nueva Vida Sur",
                    "Nueva Vida Norte",
                    "Poblacion Norte",
                    "Poblacion Sur",
                    "Tambo-an",
                    "Vallehermoso",
                    "Villaflor",
                    "Villafuerte",
                    "Villarcayo",
                    "Alegria",
                    "Ambuan",
                    "Baang",
                    "Bagtic",
                    "Bonbong",
                    "Cambailan",
                    "Candumayao",
                    "Kang-iras",
                    "Causwagan Norte",
                    "Hagbuaya",
                    "Haguilanan",
                    "Libertad Sur",
                    "Liboron",
                    "Mahayag Norte",
                    "Mahayag Sur",
                    "Maitum",
                    "Mantasida",
                    "Poblacion",
                    "Poblacion Weste",
                    "Rizal",
                    "Sinakayanan",
                    "Triple Union",
                    "Bacani",
                    "Bogtongbod", "Bonbon", "Bontud", "Buacao", "Buangan", "Cabog", "Caboy", "Caluwasan", "Candajec", "Cantoyoc", "Comaang", "Danahao",
                    "Katipunan",
                    "Lajog", "Mataub",
                    "Nahawan",
                    "Poblacion Centro",
                    "Poblacion Norte",
                    "Poblacion Sur",
                    "Tangaran",
                    "Tontunan",
                    "Tubod",
                    "Villaflor",
                    "Libaong",
                    "Tawala",
                    "Bolod",
                    "Doljo",
                    "Tangnan",
                    "Cascajo",
                    "Looc",
                    "Balicasag",
                    "Bil-isan",
                    "Dao",
                    "Cogon",
                    "Poblacion I",
                    "Poblacion II",
                    "Poblacion III",
                    "Bunga Mar",
                    "Lungsodaan East",
                    "Poblacion",
                    "Punta Cruz",
                    "Anislag",
                    "Mansasa",
                    "Booy",
                    "Bool",
                    "Dampas",
                    "Cabawan",
                    "Tiptip",
                    "Ubujan",
                    "Manga",
                    "Taloto",
                    "San Isidro",
                    "SampleÃƒâ€˜",
                    "Badiang",
                    "Bahaybahay",
                    "Cambuac Norte",
                    "Cambuac Sur",
                    "Canagong",
                    "Libjo",
                    "Poblacion I",
                    "Poblacion II",
                    "Biking",
                    "Bingag",
                    "Catarman",
                    "Dao",
                    "Mariveles",
                    "Mayacabac",
                    "Poblacion", "San Isidro (Canlong),","Songculan", "Tabalong", "Tinago", "Totolan", "Anislag", "Canangca-an", "Canapnapan", "Cancatac", "Pandol", "Poblacion", "Sambog", "Tanday", "De La Paz", "Fatima", "Loreto", "Lourdes", "Malayo Norte", "Malayo Sur", "Monserrat", "New Lourdes", "Patrocinio", "Poblacion", "Rosario", "Salvador", "San Roque", "Upper De La Paz", "Agape", "Alegria Norte", "Alegria Sur", "Bonbon", "Botoc Occidental", "Botoc Oriental", "Calvario", "Concepcion", "Hinawanan", "Las Salinas Norte", "Las Salinas Sur", "Palo", "Poblacion Ibabao", "Poblacion Ubos", "Sagnap", "Tambangan", "Tangcasan Norte", "Tangcasan Sur", "Tayong Occidental", "Tayong Oriental", "Tocdog Dacu", "Tocdog Ilaya", "Villalimpia", "Yanangan", "Danao", "Agahay", "Aliguay", "Bayacabac", "Bood", "Busao", "Cabawan", "Candavid", "Dipatlong", "Guiwanon", "Jandig", "Lagtangon", "Lincod", "Pagnitoan", "Punsod", "San Isidro", "San Roque(Aghao)", "San Vicente", "Tinibgan", "Toril", "Hanopol Este", "Hanopol Norte", "Hanopol Weste", "Magsija", "Maslog", "Sagasa", "Sal-ing", "San Isidro", "San Roque", "Santo Nino", "Tagustusan", "Badbad Occidental", "Badbad Oriental", "Basac", "Basdacu", "Basdio", "Biasong", "Bongco", "Bugho", "Cabacongan", "Cabadug", "Cabug", "Calayugan Norte", "Calayugan Sur", "Cambaquiz", "Campatud", "Candaigan", "Canhangdon Occident", "Canhangdon Oriental", "Canigaan", "Canmaag", "Canmanoc", "Cansuagwit", "Cansubayon", "Cantam-is Bago", "Cantam-is Baslay", "Cantaongon", "Cantumocad", "Catagbacan Handig", "Catagbacan Norte", "Catagbacan Sur", "Cogon Norte", "Cogon Sur", "Cuasi", "Genomoan", "Looc", "Mocpoc Norte", "Mocpoc Sur", "Moto Norte", "Moto Sur", "Nagtuang", "Napo", "Nueva Vida", "Panangquilon", "Pantudlan", "Pig-ot", "Pondol", "Quinobcoban", "Sondol", "Song-on", "Talisay", "Tan Awan", "Tangnan", "Taytay", "Ticugan", "Tiwi", "Tontonan", "Tubodacu", "Tubodio", "Tubuan", "Ubayon", "Ubojan", "Bagongbanwa", "Banlasan", "Batasan Island", "Bilangbilangan Isla", "Bosongon", "Buenos Aires", "Bunacan", "Cabulihan", "Cahayag", "Cawayanan", "Centro", "Genonocan", "Guiwanon", "Ilijan Norte", "Ilijan Sur", "Libertad", "Macaas", "Matabao", "Mocaboc Island", "Panadtaran", "Panaytayon", "Pandan", "Pangapasan Island", "Pinayagan Norte", "Pinayagan Sur", "Pooc Occidental", "Pooc Oriental", "Potohan", "Talenceras", "Tan-awan", "Tinangnan", "Ubay Island", "Ubojan", "Villanueva", "Agape", "Alegria", "Bagumbayan", "Bahian", "Bonbon Lower", "Bonbon Upper", "Buenavista", "Bugho", "Cabadiangan", "Calunasan Norte", "Calunasan Sur", "Camayaan", "Cambance", "Candabong", "Candasag", "Canlasid", "Gon-ob", "Gotozon", "Jimilian", "Oy", "Poblacion Ondol", "Poblacion Sawang", "Quinoguitan", "Taytay", "Tigbao", "Ugpong", "Valladolid", "Villaflor", "Banban", "Bonkokan IIaya", "Bonkokan Ubos", "Calvario", "Candulang", "Catugasan", "Cayupo", "Cogon", "Jambawan", "La Fortuna", "Lomanoy", "Macalingan", "Malinao East", "Malinao West", "Nagsulay", "Poblacion", "Taug", "Tiguis", "Lintuan", "Lourdes", "Poblacion", "Cabatang", "Cagongcagong", "Cambaol", "Pagahat", "Progreso", "Putlongcam", "Sudlon(Omhor)", "Untaga", "Santa Cruz", "Aloja", "Behind The Clouds(S ,se)\n" +
                    "Cabacnitan", "Cambacay", "Cantigdas", "Garcia", "Janlud", "Poblacion Norte", "Poblacion Sur", "Poblacion Vieja", "Quezon", "Quirino", "Rizal", "Rosariohan", "Santa Cruz", "Abihid", "Alemania", "Baguhan", "Bakilid", "Balbalan", "Banban", "Bauhugan", "Bilisan", "Cabagakian", "Cabanbanan", "Cadap Agan", "Cambacol", "Cambayaon", "Canhayupon", "Canlambong", "Casingan", "Catugasan", "Datag", "Guindaguitan", "Guingoyuran", "IIe", "Lapsaon", "Limokon IIaod", "Limokon IIaya", "Luyo", "Malijao", "Oac", "Pagsa", "Pangihawan", "Puangyuta", "Sawang", "Tangohay", "Taongon Cabatuan", "Taongon Can-andam", "Tawid Bitaog", "Alejawan", "Angilan", "Anibongan", "Bangwalog", "Cansuhay", "Danao", "Duay", "Imelda", "Langkis", "Lobogon", "Madua Norte", "Madua Sur", "Mambool", "Mawi", "Payao", "San Antonio", "San Isidro", "San Pedro", "Taytay", "Abijilan", "Antipolo", "Basiao", "Cagwang", "Calma", "Cambuyo", "Canayon East", "Canayon West", "Candanas", "Candulao", "Catmon", "Cayam", "Cupa", "Datag", "Estaca", "Libertad", "Lungsodaan West", "Malinao", "Manaba", "Pasong", "Poblacion East", "Poblacion West", "Sacaon", "Sampong", "Tabuan", "Togbongon", "Ulbujan East", "Ulbujan West", "Victoria", "Bato", "Bayong", "Biabas", "Cansiwang", "Casbu", "Catungawan Sur", "Guio-ang", "Lombog", "Tabajan", "Tabunok", "Trinidad", "Alejawan", "Balili", "Boctol", "Bunga IIaya", "Buyog", "Cabunga-an", "Calabacita", "Cambugason", "Can-ipol", "Canjulao", "Cantagay", "Cantuyoc", "Can-uba", "Can-upao", "Faraon", "Ipil", "Kinagbaan", "Laca", "Larapan", "Lonoy", "Looc", "Malbog", "Mayana", "Naatang", "Nausok", "Odiong", "Pagina", "Pangdan", "Poblacion(Pondol)", "Tejero", "Tubod Mar", "Tubod Monte", "Abaca", "Abad Santos", "Aguipo", "Baybayon", "Bulawan", "Cabidian", "Cawayanan", "Concepcion(Banlas)", "Del Mar", "Lungsoda An", "Marcelo", "Minol", "Paraiso", "Poblacion I", "Poblacion II", "San Isidro", "San Jose", "San Rafael", "San Roque(Cabulao)", "Tambo", "Tangkigan", "Valaga", "Aurora", "Bagacay", "Bagumbayan", "Bayong", "Buenasuerte", "Cagawasan", "Cansungay", "Catagda An", "Del Pilar", "Estaca", "IIaud", "Inaghuban", "La Suerte", "Lumbay", "Lundag", "Pamacsalan", "Poblacion", "Rizal", "San Carlos", "San Isidro", "San Vicente", "Bayawahan", "Cabancalan", "Calinga-an", "Calinginan Norte", "Calinginan Sur", "Cambagui", "Ewon", "Guinob An", "Lagtangan", "Licolico", "Lobgob", "Magsaysay", "Poblacion", "Abachanan", "Anibongan", "Bugsoc", "Cahayag", "Canlangit", "Canta Ub", "Casilay", "Danicop", "Dusita", "La Union", "Lataban", "Magsaysay", "Man-od", "Matin-ao", "Poblacion", "Salvador", "San Agustin", "San Isidro", "San Jose", "San Juan", "Santa Cruz", "Villa Garcia", "Adlawan", "Anas", "Anonang", "Anoyon", "Balingasao", "Banderaahan", "Botong", "Buyog", "Canduao Occidental", "Canduao Oriental", "Canlusong", "Cansibao", "Catug A", "Cutcutan", "Danao", "Genoveva", "Ginopolan", "La Victoria", "Lantang", "Limocon", "Loctob", "Magsaysay", "Marawis", "Maubo", "Nailo", "Omjon", "Pangi An", "Poblacion Occidenta", "Poblacion Oriental", "Simang", "Taug", "Tausion", "Taytay", "Ticum", "Babag", "Cagawasan", "Cagawitan", "Caluasan", "Candelaria", "Can Oling", "Estaca", "La Esperanza", "Mahayag", "Malitbog", "Poblacion", "San Miguel", "San Vicente", "Santa Cruz", "Villa Aurora", "Cabatuan", "Cantubod", "Carbon", "Concepcion", "Dagohoy", "Hibale", "Magtangtang", "Nahud", "Poblacion", "Remedios", "San Carlos", "San Miguel", "Santa Fe", "Santo Nino", "Tabok", "Taming", "Villa Anunciado", "Alumar", "Banacon", "Buyog", "Cabasakan", "Campao Occidental", "Campao Oriental", "Cangmundo", "Carlos P Garcia", "Corte Baud", "Handumon", "Jagoliao", "Jandayan Norte", "Jandayan Sur", "Mahanay Island", "Nasingin", "Pandanon", "Poblacion", "Saguise", "Salog", "San Jose", "Santo Nino", "Taytay", "Tugas", "Tulang", "Anonang", "Badiang", "Bahan", "Banahao", "Baoga", "Bugang", "Cagawasan", "Cagayan", "Cambitoon", "Canlinte", "Cawayan", "Cogon", "Cuaming", "Dagnawan", "Dait Sur", "Dagohoy", "Datag", "Fatima", "Hambongan", "IIaud", "IIaya", "IIihan", "Lapacan Norte", "Lapacan Sur", "Lawis", "Liloan Norte", "Liloan Sur", "Lomboy", "Lonoy Cainsican", "Lonoy Roma", "Lutao", "Luyo", "Mabuhay", "Ma Rosario", "Nabuad", "Napo", "Ondol", "Poblacion", "Riverside", "Saa", "San Isidro", "San Jose", "Santo Nino", "Santo Rosario", "Sua", "Tambook", "Tungod", "Ubujan", "U Og", "Tugas", "Calangahan", "Canmao", "Canmaya Centro", "Canmaya Diot", "Dagnawan", "Kabasacan", "Kagawasan", "Katipunan", "Langtad", "Libertad Norte", "Libertad Sur", "Mantalongon", "Poblacion", "Sagbayan Sur", "San Agustin", "San Antonio", "San Isidro", "San Ramon", "San Roque", "San Vicente Norte", "San Vicente Sur", "Santa Catalina", "Santa Cruz", "Ubojan", "Abehilan", "Baryong Daan", "Baunos", "Cabanugan"};

            town_name= new String[]{"Alburquerque", "Alicia", "Anda", "Antequera", "Baclayon", "Balilihan", "Batuan", "Bien Unido", "Bilar", "Buenavista", "Calape", "Candijay", "Carlos P. Garica", "Carmen", "Catigbian", "Clarin", "Corella", "Cortes", "Dagohoy", "Danao", "Dauis", "Dimiao", "Duero", "Garcia Hernandez", "Guindulman", "Inabanga", "Jagna" , "Getafe", "Lila", "Loay", "Loboc", "Loon" , "Mabini" , "Maribojoc" , "Panglao" , "Pilar" , "Sagbayan", "San Isidro", "San Miguel", "Sevilla", "Sierra Bullones", "Sikatuna", "Tagbilaran City", "Talibon", "Talibon", "Trinidad", "Tubigon", "Ubay", "Valencia"};
            zipcode=new String[]{"6302","6314","6311","6335","6301","6342","6318","6326","6317","6333","6328","6312","6346","6319","6343","6330","6337","6341","6322","6344","6339","6305","6309","6307", "6310","6332", "6308", "6334", "6304", "6303","6316", "6327", "6313", "6336", "6340", "6321", "6331", "6345", "6323", "6347", "6320", "6338", "6300", "6325", "6325", "6324", "6329", "6315", "6306"};
            town_status = new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1"};
        }
}
