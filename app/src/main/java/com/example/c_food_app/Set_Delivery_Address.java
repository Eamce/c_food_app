package com.example.c_food_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Set_Delivery_Address extends AppCompatActivity {
    String[] town_name;
    String[] zipcode;
    String[] town_status;
    String[] barangay;
    String[] province=new String[]{"Select Province","Bohol"};
    String[] tw1,tw2,tw3,tw4,tw5,tw6,tw7,tw8,tw9,tw10,tw11,tw12,tw13,tw14,tw15,tw16,tw17,tw18;
    Spinner town_spinner;
    SQLiteDatabase sqLiteDatabase;
    TextView otherdetails;
    Msgbox msgbox;
    Context context = this;
    Spinner town_barangay;
    Globalvars globalvars;
    Button submit_btn;
    Spinner town_province;
    ArrayAdapter<String> tw1_adapter,tw2_adapter,tw3_adapter,tw4_adapter,tw5_adapter,tw6_adapter,tw7_adapter,tw8_adapter,tw9_adapter;
    ArrayAdapter<String> tw10_adapter,tw11_adapter,tw12_adapter,tw13_adapter,tw14_adapter,tw15_adapter,tw16_adapter,tw17_adapter,tw18_adapter;
    ArrayAdapter<String> tw19_adapter,tw20_adapter,tw21_adapter,tw22_adapter,tw23_adapter,tw24_adapter,tw25_adapter,tw26_adapter,tw27_adapter;
    TextView address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_delivery_address);
        init();
        ArrayAdapter<String> townAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, town_name);
        townAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> provinceAdapater = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, province);
        provinceAdapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        town_province.setAdapter(provinceAdapater);
        town_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(town_province.getSelectedItemId()==1){
                    town_spinner.setAdapter(townAdapter);
                }
                address.setText(town_province.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        town_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                System.out.println("getSelectedItemId"+town_spinner.getSelectedItemId());
                if(town_spinner.getSelectedItemId()==1){
                    town_barangay.setAdapter(tw1_adapter);
                }else if(town_spinner.getSelectedItemId()==2){
                    town_barangay.setAdapter(tw2_adapter);
                }else if(town_spinner.getSelectedItemId()==3){
                    town_barangay.setAdapter(tw3_adapter);
                }else if(town_spinner.getSelectedItemId()==4){
                    town_barangay.setAdapter(tw4_adapter);
                }else if(town_spinner.getSelectedItemId()==5){
                    town_barangay.setAdapter(tw5_adapter);
                }else if(town_spinner.getSelectedItemId()==6){
                    town_barangay.setAdapter(tw6_adapter);
                }else if(town_spinner.getSelectedItemId()==7){
                    town_barangay.setAdapter(tw7_adapter);
                }else if(town_spinner.getSelectedItemId()==8){
                    town_barangay.setAdapter(tw8_adapter);
                }else if(town_spinner.getSelectedItemId()==9){
                    town_barangay.setAdapter(tw9_adapter);
                }else if(town_spinner.getSelectedItemId()==10){
                    town_barangay.setAdapter(tw10_adapter);
                }else if(town_spinner.getSelectedItemId()==11){
                    town_barangay.setAdapter(tw11_adapter);
                }else if(town_spinner.getSelectedItemId()==12){
                    town_barangay.setAdapter(tw12_adapter);
                }else if(town_spinner.getSelectedItemId()==13){
                    town_barangay.setAdapter(tw13_adapter);
                }else if(town_spinner.getSelectedItemId()==14){
                    town_barangay.setAdapter(tw14_adapter);
                }else if(town_spinner.getSelectedItemId()==15){
                    town_barangay.setAdapter(tw15_adapter);
                }else if(town_spinner.getSelectedItemId()==16){
                    town_barangay.setAdapter(tw16_adapter);
                }else if(town_spinner.getSelectedItemId()==17){
                    town_barangay.setAdapter(tw17_adapter);
                }else if(town_spinner.getSelectedItemId()==18){
                    town_barangay.setAdapter(tw18_adapter);
                }
                address.setText(town_province.getSelectedItem().toString().trim()+"\n"
                                    +town_spinner.getSelectedItem().toString().trim());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        town_barangay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                address.setText(town_province.getSelectedItem().toString().trim()+"\n"
                        +town_spinner.getSelectedItem().toString().trim()+"\n"
                        +town_barangay.getSelectedItem().toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address.setText(town_barangay.getSelectedItem().toString().trim()+" "
                        +town_spinner.getSelectedItem().toString().trim()+" "
                        +town_province.getSelectedItem().toString().trim()+"\n"
                        +otherdetails.getText().toString());
                globalvars.set("delivery_address",address.getText().toString());
                Intent intent = new Intent(Set_Delivery_Address.this,Checkout_Details.class);
                startActivity(intent);
                finish();
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
        } else if(item.getItemId()==R.id.logout){
            msgbox.showyesno( "Hello","Are you sure you want to log out?");
            msgbox.setMsgboxListener(new Msgbox.MsgboxListener() {
                @Override
                public void onyes() {
                    Intent logout = new Intent(Set_Delivery_Address.this, Login.class);
                    startActivity(logout);
                    globalvars.logout();
                }
                @Override
                public void onno() {
                }
            });
        }else if(item.getItemId()==R.id.account){
            Intent intent = new Intent(Set_Delivery_Address.this, Edit_My_Account.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.home){
            Intent intent = new Intent(Set_Delivery_Address.this, Home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void init(){
        msgbox = new Msgbox(context);
        town_spinner = (Spinner) findViewById(R.id.town_spinner);
        town_barangay = (Spinner) findViewById(R.id.town_barangay);
        town_province = (Spinner) findViewById(R.id.town_province);
        address = (TextView) findViewById(R.id.address);
        submit_btn=(Button) findViewById(R.id.submit_btn);
        otherdetails = (TextView) findViewById(R.id.otherdetails);
        String path = getApplicationContext().getDatabasePath("cfood.db").getPath();
        sqLiteDatabase = openOrCreateDatabase(path, MODE_PRIVATE, null);
        globalvars = new Globalvars(getApplicationContext(),this);
        town_data();
        tw1_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw1);
        tw1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw2_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw2);
        tw2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw3_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw3);
        tw3_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw4_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw4);
        tw4_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw5_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw5);
        tw5_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw6_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw6);
        tw6_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw7_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw7);
        tw7_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw8_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw8);
        tw8_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw9_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw9);
        tw9_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw10_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw10);
        tw10_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw11_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw11);
        tw11_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw12_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw12);
        tw12_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw13_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw13);
        tw13_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw14_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw14);
        tw14_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw15_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw15);
        tw15_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw16_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw16);
        tw16_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw17_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw17);
        tw17_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tw18_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tw18);
        tw18_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public List<String> getTown(){
        List<String> town = new ArrayList<String>();
        /*
                Cursor  row = sqLiteDatabase.query("towns","town_name","status = '1' ",null,null,null);
         */
        String one="1";
        Cursor  row = sqLiteDatabase.rawQuery("Select town_name from towns where status ="+one+" ",null);
        System.out.println("sdfsdfsdfsdf"+row);
        if (row.moveToFirst()) {
            do {
                town.add(row.getString(1));//adding 2nd column data
            } while (row.moveToNext());
        }
//        while (row.moveToNext()){
//            town.add(row.getString(1));
//        }
        return town;
    }
    public void town_data(){
        tw1=new String[]{"Select Barangay", "Bahi", "Basacdacu", "Cantinguib", "Dangay", "East Poblacion", "San Agustin", "Santa Felomina", "Tagbuane", "Toril", "West Poblacion"};
        tw2=new String[]{"Select Barangay","Cayacay","Del Monte","Katipunan","La Hacienda","Mahayag","Napo","Poblacion", "Cabatang", "Cagongcagong", "Cambaol", "Pagahat", "Progreso", "Putlongcam", "Sudlon(Omhor)", "Untaga"};
        tw3=new String[]{"Select Barangay","Almaria", "Bacong\n","Badiang\n","BuenaSuerte\n", "Candabong\n","Casica\n","Katipunan\n","Linawan\n","Lundag\n","Poblacion\n","Suba\n","Talisay\n","Tanod\n","Tawid\n","Virgen\n","Santa Cruz"};
        tw4=new String[]{"Select Barangay","Angilan", "Bantolinao", "Bicahan", "Bitaugan ", "Bungahan", "Canlaas", "Cansibuan", "Can-omay", "Ceiling", "Danao ", "Danicop", "Mag-aso", "Poblacion", "Quinapon-an", "Santo Rosario ", "Tabuan", "Tagubaas", "Tupas", "Obujan", "Viga ", "Villa Aurora"};
        tw5=new String[]{"Select Barangay","Payahan",  "Cambanac" ,  "Dasitam" ,  "Buenaventura" ,  "Guiwanon" ,  "Landican" ,  "Laya " ,  "Libertad" ,  "MontaÃ±a" ,  "Pamilacan" ,  "Poblacion" ,  "San Isidro " ,  "San Roque" ,  "San Vicente" ,  "Santa Cruz" ,  "Taguihon" ,  "Tanday "};
        tw6=new String[]{"Select Barangay","Baucan Norte", "Baucan Sur" , "Boctol" , "Boyog Norte " , "Boyog Proper" , "Boyog Sur" , "Cabad" , "Candasig" , "Cantalid " , "Cantomimbo" , "Cogon" , "Datag Norte" , "Datag Sur" , "Del Carmen Este (Pob.) " , "Del Carmen Norte (Pob.)" , "Del Carmen Weste (Pob.)" , "Del Carmen Sur (Pob.)" , "Del Rosario" , "Dorol " , "Haguilanan Grande" , "Hanopol Este" , "Hanopol Norte" , "Hanopol Weste" , "Magsija" , "Maslog" , "Sagasa" , "Sal-ing" , "San Isidro" , "San Roque" , "Santo Nino" , "Tagustusan" , "Poblacion"};
        tw7=new String[]{"Aloja","Behind The Clouds(San Jose)" , "Cabacnitan" , "Cambacay" , "Cantigdas" , "Garcia" , "Janlud" , "Poblacion Norte" , "Poblacion Sur" , "Poblacion Vieja" , "Quezon" , "Quirino" , "Rizal" , "Rosariohan" , "Santa Cruz"};
        tw8=new String[]{"Bilangbilangan Dako", "Bilangbilangan Diot ", "Hingotanan East ", "Hingotanan West", "Liberty", "Malingin", "Mandawa", "Maomawan", "Nueva Esperanza      ", "Nueva Estrella   ", "Pinamgo     ", "Poblacion (Bien Unido Proper", "Puerto San Pedro (Lawis)  ", "Sagasa   ", "Tuboran"};
        tw9=new String[]{"Bonifacio", "Bugang Norte" , "Bugang Sur" , "Cabacnitan" , "Cambigsi" , "Campagao" , "Cansumbol" , "Dagohoy" , "Owac " , "Poblacion" , "Quezon" , "Riverside" , "Rizal" , "Roxas" , "Subayon" , "Villa Aurora" , "Villa Suerte" , "Yanaya" , "Zamora"};
        tw10=new String[]{"Anonang", "Asinan", "Bago", "Baluarte", "Bantuan", "Bato", "BonotBonot", "Bugaong", "Cambuhat", "Cambus-oc", "Cangawa", "Cantomugcad", "Cantores", "Cantuba", "Catigbian", "Cawag", "Cruz", "Dait", "Eastern Cabuln", "Hunan", "Lapacan Norte", "Lapacan Sur", "Lubang", "Lusong", "Magkaya", "Merryland", "Nueva Granada", "Nueva Montana", "Overland", "Panghagban", "Poblacion", "Putting Bato", "Rufo Hill", "Sweetland", "Western Cabul-an"};
        tw11=new String[]{"Abucayan Norte", "Abucayan Sur", "Banlasan", "Bentig", "Binogawan", "Bonbon", "Cabayugan", "Cabudburan", "Calunasan", "Camias", "Canguha", "Catmonan", "Desamparados", "Kahayag", "Kinabag-an", "Labuon", "Lawis", "Liboron", "Lo-oc", "Lomboy", "Lucob", "Madangog", "Magtongtong", "Mandaug", "Mantatao", "Sampoangon", "San Isidro", "Santa Cruz", "Sojoton", "Talisay", "Tinibgan", "Tultugan", "Ulbujan"};
        tw12=new String[]{"Abilihan","Anoling", "Boyo-an", "Cadapdapan", "Cambane", "Can-olin", "Canawa", "Cogtong", "La Union", "Luan", "Lungsoda-an", "Mahangin", "Pagahat", "Panadtaran", "Panas", "Poblacion", "San Isidro", "Tambongan", "Tawid", "Tugas", "Tubod"};
        tw13=new String[]{"Aguining","Basiao", "Baud", "Bayog", "Bogo", "Bonbonon", "Canmangao", "Campamanog", "Gaus", "Kabangkalan", "Lapinig", "Lipata", "Poblacion", "Popoo", "Saguise", "San Jose", "Santo Rosario", "Tilmobo", "Tugnao", "Villa Milagrosa", "Butan", "San Vicente", "Tugas"};
        tw14=new String[]{"Alegria", "Bica" , "Buenavist" , "Buenos Aire" , "Calatrav" , "El Progres" , "El Salvado" , "Guadalup" , "Katipuna" , "La Liberte" , "La Pa" , "La Salvacio" , "La Victori" , "Matin-a" , "Montehermos" , "MonteSuert" , "Montesuntin" , "Montevide" , "Nueva Fuerz" , "Nueva Vida Est" , "Nueva Vida Su" , "Nueva Vida Nort" , "Poblacion Nort" , "Poblacion Su" , "Tambo-a" , "Vallehermos" , "Villaflo" , "Villafuert" , "Villarcayo"};
        tw15=new String[]{"Alegria", "Ambuan", "Baang", "Bagtic", "Bonbong", "Cambailan", "Candumayao", "Kang-iras", "Causwagan Norte", "Hagbuaya", "Haguilanan", "Libertad Sur", "Liboron", "Mahayag Norte", "Mahayag Sur", "Maitum", "Mantasida", "Poblacion", "Poblacion Weste", "Rizal", "Sinakayanan", "Triple Union"};
        tw16=new String[]{"Bacani", "Bogtongbod", "Bonbon", "Bontud", "Buacao", "Buangan", "Cabog", "Caboy", "Caluwasan", "Candajec", "Cantoyoc", "Comaang", "Danahao", "Katipunan", "Lajog", "Mataub", "Nahawan", "Poblacion Centro", "Poblacion Norte", "Poblacion Sur", "Tangaran", "Tontunan", "Tubod", "Villaflor"};
        tw17=new String[]{"Anislag", "Canangca-an", "Canapnapan", "Cancatac", "Pandol", "Poblacion", "Sambog", "Tanday"};
        tw18=new String[]{"De La Paz", "Fatima", "Loreto", "Lourdes", "Malayo Norte", "Malayo Sur", "Monserrat", "New Lourdes", "Patrocinio", "Poblacion", "Rosario", "Salvador", "San Roque", "Upper De La Paz"};
        tw17=new String[]{""};
        tw17=new String[]{""};
        tw17=new String[]{""};
        tw17=new String[]{""};




        town_name= new String[]{"Select Town","Alburquerque", "Alicia", "Anda", "Antequera", "Baclayon", "Balilihan", "Batuan", "Bien Unido", "Bilar", "Buenavista",
                "Calape", "Candijay", "Carlos P. Garica", "Carmen", "Catigbian", "Clarin", "Corella", "Cortes", "Dagohoy", "Danao", "Dauis", "Dimiao", "Duero", "Garcia Hernandez", "Guindulman",
                "Inabanga", "Jagna" , "Getafe", "Lila", "Loay", "Loboc", "Loon" , "Mabini" , "Maribojoc" , "Panglao" , "Pilar" , "Sagbayan", "San Isidro", "San Miguel","Sevilla", "Sierra Bullones", "Sikatuna",
                "Tagbilaran City", "Talibon", "Talibon", "Trinidad", "Tubigon", "Ubay", "Valencia"};
        zipcode=new String[]{"6302", "6314", "6311", "6335", "6301", "6342", "6318", "6326", "6317", "6333", "6328", "6312", "6346", "6319",
                "6343", "6330", "6337", "6341", "6322", "6344", "6339", "6305", "6309", "6307", "6310", "6332", "6308", "6334", "6304", "6303",
                "6316", "6327", "6313", "6336", "6340", "6321", "6331", "6345", "6323", "6347", "6320", "6338", "6300", "6325", "6325", "6324", "6329", "6315", "6306"};
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
                "Bogtongbod",
                "Bonbon",
                "Bontud",
                "Buacao",
                "Buangan",
                "Cabog",
                "Caboy",
                "Caluwasan",
                "Candajec",
                "Cantoyoc",
                "Comaang",
                "Danahao",
                "Katipunan",
                "Lajog",
                "Mataub",
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
                "Poblacion",
                "San Isidro (Canlong)," +
                "Songculan",
                "Tabalong",
                "Tinago",
                "Totolan",
                "Anislag",
                "Canangca-an",
                "Canapnapan",
                "Cancatac",
                "Pandol",
                "Poblacion",
                "Sambog",
                "Tanday",
                "De La Paz",
                "Fatima",
                "Loreto",
                "Lourdes",
                "Malayo Norte",
                "Malayo Sur",
                "Monserrat",
                "New Lourdes",
                "Patrocinio",
                "Poblacion",
                "Rosario",
                "Salvador",
                "San Roque",
                "Upper De La Paz",
                "Agape",
                "Alegria Norte",
                "Alegria Sur",
                "Bonbon",
                "Botoc Occidental",
                "Botoc Oriental",
                "Calvario",
                "Concepcion",
                "Hinawanan",
                "Las Salinas Norte",
                "Las Salinas Sur",
                "Palo",
                "Poblacion Ibabao",
                "Poblacion Ubos",
                "Sagnap",
                "Tambangan",
                "Tangcasan Norte",
                "Tangcasan Sur",
                "Tayong Occidental",
                "Tayong Oriental",
                "Tocdog Dacu",
                "Tocdog Ilaya",
                "Villalimpia",
                "Yanangan",
                "Danao",
                "Agahay",
                "Aliguay",
                "Bayacabac",
                "Bood",
                "Busao",
                "Cabawan",
                "Candavid",
                "Dipatlong",
                "Guiwanon",
                "Jandig",
                "Lagtangon",
                "Lincod",
                "Pagnitoan",
                "Punsod",
                "San Isidro",
                "San Roque(Aghao)",
                "San Vicente",
                "Tinibgan",
                "Toril",
                "Hanopol Este",
                "Hanopol Norte",
                "Hanopol Weste",
                "Magsija",
                "Maslog",
                "Sagasa",
                "Sal-ing",
                "San Isidro",
                "San Roque",
                "Santo Nino",
                "Tagustusan",
                "Badbad Occidental",
                "Badbad Oriental",
                "Basac",
                "Basdacu",
                "Basdio",
                "Biasong",
                "Bongco",
                "Bugho",
                "Cabacongan",
                "Cabadug",
                "Cabug",
                "Calayugan Norte",
                "Calayugan Sur",
                "Cambaquiz",
                "Campatud",
                "Candaigan",
                "Canhangdon Occident",
                "Canhangdon Oriental",
                "Canigaan",
                "Canmaag",
                "Canmanoc",
                "Cansuagwit",
                "Cansubayon",
                "Cantam-is Bago",
                "Cantam-is Baslay",
                "Cantaongon",
                "Cantumocad",
                "Catagbacan Handig",
                "Catagbacan Norte",
                "Catagbacan Sur",
                "Cogon Norte",
                "Cogon Sur",
                "Cuasi",
                "Genomoan",
                "Looc",
                "Mocpoc Norte",
                "Mocpoc Sur",
                "Moto Norte",
                "Moto Sur",
                "Nagtuang",
                "Napo",
                "Nueva Vida",
                "Panangquilon",
                "Pantudlan",
                "Pig-ot",
                "Pondol",
                "Quinobcoban",
                "Sondol",
                "Song-on",
                "Talisay",
                "Tan Awan",
                "Tangnan",
                "Taytay",
                "Ticugan",
                "Tiwi",
                "Tontonan",
                "Tubodacu",
                "Tubodio",
                "Tubuan",
                "Ubayon",
                "Ubojan",
                "Bagongbanwa",
                "Banlasan",
                "Batasan Island",
                "Bilangbilangan Isla",
                "Bosongon",
                "Buenos Aires",
                "Bunacan",
                "Cabulihan",
                "Cahayag",
                "Cawayanan",
                "Centro",
                "Genonocan",
                "Guiwanon",
                "Ilijan Norte",
                "Ilijan Sur",
                "Libertad",
                "Macaas",
                "Matabao",
                "Mocaboc Island",
                "Panadtaran",
                "Panaytayon",
                "Pandan",
                "Pangapasan Island",
                "Pinayagan Norte",
                "Pinayagan Sur",
                "Pooc Occidental",
                "Pooc Oriental",
                "Potohan",
                "Talenceras",
                "Tan-awan",
                "Tinangnan",
                "Ubay Island",
                "Ubojan",
                "Villanueva",
                "Agape",
                "Alegria",
                "Bagumbayan",
                "Bahian",
                "Bonbon Lower",
                "Bonbon Upper",
                "Buenavista",
                "Bugho",
                "Cabadiangan",
                "Calunasan Norte",
                "Calunasan Sur",
                "Camayaan",
                "Cambance",
                "Candabong",
                "Candasag",
                "Canlasid",
                "Gon-ob",
                "Gotozon",
                "Jimilian",
                "Oy",
                "Poblacion Ondol",
                "Poblacion Sawang",
                "Quinoguitan",
                "Taytay",
                "Tigbao",
                "Ugpong",
                "Valladolid",
                "Villaflor",
                "Banban",
                "Bonkokan IIaya",
                "Bonkokan Ubos",
                "Calvario",
                "Candulang",
                "Catugasan",
                "Cayupo",
                "Cogon",
                "Jambawan",
                "La Fortuna",
                "Lomanoy",
                "Macalingan",
                "Malinao East",
                "Malinao West",
                "Nagsulay",
                "Poblacion",
                "Taug",
                "Tiguis",
                "Lintuan",
                "Lourdes",
                "Poblacion",
                "Cabatang",
                "Cagongcagong",
                "Cambaol",
                "Pagahat",
                "Progreso",
                "Putlongcam",
                "Sudlon(Omhor)",
                "Untaga",
                "Santa Cruz",
                "Aloja",
                "Behind The Clouds(S ,se)\n" +
                "Cabacnitan",
                "Cambacay",
                "Cantigdas",
                "Garcia",
                "Janlud",
                "Poblacion Norte",
                "Poblacion Sur",
                "Poblacion Vieja",
                "Quezon",
                "Quirino",
                "Rizal",
                "Rosariohan",
                "Santa Cruz",
                "Abihid",
                "Alemania",
                "Baguhan",
                "Bakilid",
                "Balbalan",
                "Banban",
                "Bauhugan",
                "Bilisan",
                "Cabagakian",
                "Cabanbanan",
                "Cadap Agan",
                "Cambacol",
                "Cambayaon",
                "Canhayupon",
                "Canlambong",
                "Casingan",
                "Catugasan",
                "Datag",
                "Guindaguitan",
                "Guingoyuran",
                "IIe",
                "Lapsaon",
                "Limokon IIaod",
                "Limokon IIaya",
                "Luyo",
                "Malijao",
                "Oac",
                "Pagsa",
                "Pangihawan",
                "Puangyuta",
                "Sawang",
                "Tangohay",
                "Taongon Cabatuan",
                "Taongon Can-andam",
                "Tawid Bitaog",
                "Alejawan",
                "Angilan",
                "Anibongan",
                "Bangwalog",
                "Cansuhay",
                "Danao",
                "Duay",
                "Imelda",
                "Langkis",
                "Lobogon",
                "Madua Norte",
                "Madua Sur",
                "Mambool",
                "Mawi",
                "Payao",
                "San Antonio",
                "San Isidro",
                "San Pedro",
                "Taytay",
                "Abijilan",
                "Antipolo",
                "Basiao",
                "Cagwang",
                "Calma",
                "Cambuyo",
                "Canayon East",
                "Canayon West",
                "Candanas",
                "Candulao",
                "Catmon",
                "Cayam",
                "Cupa",
                "Datag",
                "Estaca",
                "Libertad",
                "Lungsodaan West",
                "Malinao",
                "Manaba",
                "Pasong",
                "Poblacion East",
                "Poblacion West",
                "Sacaon",
                "Sampong",
                "Tabuan",
                "Togbongon",
                "Ulbujan East",
                "Ulbujan West",
                "Victoria",
                "Bato",
                "Bayong",
                "Biabas",
                "Cansiwang",
                "Casbu",
                "Catungawan Sur",
                "Guio-ang",
                "Lombog",
                "Tabajan",
                "Tabunok",
                "Trinidad",
                "Alejawan",
                "Balili",
                "Boctol",
                "Bunga IIaya",
                "Buyog",
                "Cabunga-an",
                "Calabacita",
                "Cambugason",
                "Can-ipol",
                "Canjulao",
                "Cantagay",
                "Cantuyoc",
                "Can-uba",
                "Can-upao",
                "Faraon",
                "Ipil",
                "Kinagbaan",
                "Laca",
                "Larapan",
                "Lonoy",
                "Looc",
                "Malbog",
                "Mayana",
                "Naatang",
                "Nausok",
                "Odiong",
                "Pagina",
                "Pangdan",
                "Poblacion(Pondol)",
                "Tejero",
                "Tubod Mar",
                "Tubod Monte",
                "Abaca",
                "Abad Santos",
                "Aguipo",
                "Baybayon",
                "Bulawan",
                "Cabidian",
                "Cawayanan",
                "Concepcion(Banlas)",
                "Del Mar",
                "Lungsoda An",
                "Marcelo",
                "Minol",
                "Paraiso",
                "Poblacion I",
                "Poblacion II",
                "San Isidro",
                "San Jose",
                "San Rafael",
                "San Roque(Cabulao)",
                "Tambo",
                "Tangkigan",
                "Valaga",
                "Aurora",
                "Bagacay",
                "Bagumbayan",
                "Bayong",
                "Buenasuerte",
                "Cagawasan",
                "Cansungay",
                "Catagda An",
                "Del Pilar",
                "Estaca",
                "IIaud",
                "Inaghuban",
                "La Suerte",
                "Lumbay",
                "Lundag",
                "Pamacsalan",
                "Poblacion",
                "Rizal",
                "San Carlos",
                "San Isidro",
                "San Vicente",
                "Bayawahan",
                "Cabancalan",
                "Calinga-an",
                "Calinginan Norte",
                "Calinginan Sur",
                "Cambagui",
                "Ewon",
                "Guinob An",
                "Lagtangan",
                "Licolico",
                "Lobgob",
                "Magsaysay",
                "Poblacion",
                "Abachanan",
                "Anibongan",
                "Bugsoc",
                "Cahayag",
                "Canlangit",
                "Canta Ub",
                "Casilay",
                "Danicop",
                "Dusita",
                "La Union",
                "Lataban",
                "Magsaysay",
                "Man-od",
                "Matin-ao",
                "Poblacion",
                "Salvador",
                "San Agustin",
                "San Isidro",
                "San Jose",
                "San Juan",
                "Santa Cruz",
                "Villa Garcia",
                "Adlawan",
                "Anas",
                "Anonang",
                "Anoyon",
                "Balingasao",
                "Banderaahan",
                "Botong",
                "Buyog",
                "Canduao Occidental",
                "Canduao Oriental",
                "Canlusong",
                "Cansibao",
                "Catug A",
                "Cutcutan",
                "Danao",
                "Genoveva",
                "Ginopolan",
                "La Victoria",
                "Lantang",
                "Limocon",
                "Loctob",
                "Magsaysay",
                "Marawis",
                "Maubo",
                "Nailo",
                "Omjon",
                "Pangi An",
                "Poblacion Occidenta",
                "Poblacion Oriental",
                "Simang",
                "Taug",
                "Tausion",
                "Taytay",
                "Ticum",
                "Babag",
                "Cagawasan",
                "Cagawitan",
                "Caluasan",
                "Candelaria",
                "Can Oling",
                "Estaca",
                "La Esperanza",
                "Mahayag",
                "Malitbog",
                "Poblacion",
                "San Miguel",
                "San Vicente",
                "Santa Cruz",
                "Villa Aurora",
                "Cabatuan",
                "Cantubod",
                "Carbon",
                "Concepcion",
                "Dagohoy",
                "Hibale",
                "Magtangtang",
                "Nahud",
                "Poblacion",
                "Remedios",
                "San Carlos",
                "San Miguel",
                "Santa Fe",
                "Santo Nino",
                "Tabok",
                "Taming",
                "Villa Anunciado",
                "Alumar",
                "Banacon",
                "Buyog",
                "Cabasakan",
                "Campao Occidental",
                "Campao Oriental",
                "Cangmundo",
                "Carlos P Garcia",
                "Corte Baud",
                "Handumon",
                "Jagoliao",
                "Jandayan Norte",
                "Jandayan Sur",
                "Mahanay Island",
                "Nasingin",
                "Pandanon",
                "Poblacion",
                "Saguise",
                "Salog",
                "San Jose",
                "Santo Nino",
                "Taytay",
                "Tugas",
                "Tulang",
                "Anonang",
                "Badiang",
                "Bahan",
                "Banahao",
                "Baoga",
                "Bugang",
                "Cagawasan",
                "Cagayan",
                "Cambitoon",
                "Canlinte",
                "Cawayan",
                "Cogon",
                "Cuaming",
                "Dagnawan",
                "Dait Sur",
                "Dagohoy",
                "Datag",
                "Fatima",
                "Hambongan",
                "IIaud",
                "IIaya",
                "IIihan",
                "Lapacan Norte",
                "Lapacan Sur",
                "Lawis",
                "Liloan Norte",
                "Liloan Sur",
                "Lomboy",
                "Lonoy Cainsican",
                "Lonoy Roma",
                "Lutao",
                "Luyo",
                "Mabuhay",
                "Ma Rosario",
                "Nabuad",
                "Napo",
                "Ondol",
                "Poblacion",
                "Riverside",
                "Saa",
                "San Isidro",
                "San Jose",
                "Santo Nino",
                "Santo Rosario",
                "Sua",
                "Tambook",
                "Tungod",
                "Ubujan",
                "U Og",
                "Tugas",
                "Calangahan",
                "Canmao",
                "Canmaya Centro",
                "Canmaya Diot",
                "Dagnawan",
                "Kabasacan",
                "Kagawasan",
                "Katipunan",
                "Langtad",
                "Libertad Norte",
                "Libertad Sur",
                "Mantalongon",
                "Poblacion",
                "Sagbayan Sur",
                "San Agustin",
                "San Antonio",
                "San Isidro",
                "San Ramon",
                "San Roque",
                "San Vicente Norte",
                "San Vicente Sur",
                "Santa Catalina",
                "Santa Cruz",
                "Ubojan",
                "Abehilan",
                "Baryong Daan",
                "Baunos",
                "Cabanugan"};
        //        town_status = new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
//                "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1"};
    }
}