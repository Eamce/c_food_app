package com.example.c_food_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Set_Delivery_Address extends AppCompatActivity {
    String[] town_name;
    String[] zipcode;
    String[] town_status;
    Spinner town_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_delivery_address);
        town_spinner = (Spinner) findViewById(R.id.town_spinner);
        town_data();
        ArrayAdapter<String> townAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, town_name);
        townAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        town_spinner.setAdapter(townAdapter);
    }

    public void town_data(){
        town_name= new String[]{"Select Town","Alburquerque", "Alicia", "Anda", "Antequera", "Baclayon", "Balilihan", "Batuan", "Bien Unido", "Bilar", "Buenavista",
                "Calape", "Candijay", "Carlos P. Garica", "Carmen", "Catigbian", "Clarin", "Corella", "Cortes", "Dagohoy", "Danao", "Dauis", "Dimiao", "Duero", "Garcia Hernandez", "Guindulman",
                "Inabanga", "Jagna" , "Getafe", "Lila", "Loay", "Loboc", "Loon" , "Mabini" , "Maribojoc" , "Panglao" , "Pilar" , "Sagbayan", "San Isidro", "San Miguel","Sevilla", "Sierra Bullones", "Sikatuna",
                "Tagbilaran City", "Talibon", "Talibon", "Trinidad", "Tubigon", "Ubay", "Valencia"};
        zipcode=new String[]{"6302", "6314", "6311", "6335", "6301", "6342", "6318", "6326", "6317", "6333", "6328", "6312", "6346", "6319",
                "6343", "6330", "6337", "6341", "6322", "6344", "6339", "6305", "6309", "6307", "6310", "6332", "6308", "6334", "6304", "6303",
                "6316", "6327", "6313", "6336", "6340", "6321", "6331", "6345", "6323", "6347", "6320", "6338", "6300", "6325", "6325", "6324", "6329", "6315", "6306"};
        town_status = new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
                "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "1", "1", "1", "1", "1"};
    }
}