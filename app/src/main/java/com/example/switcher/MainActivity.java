package com.example.switcher;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint({"MissingInflatedId","UseSwitchCompatOrMaterialCode"})
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final String BASE_URL = "https://my-json-server.typicode.com//saad-jaghlal/Pharmacie_Api/";
    private Retrofit retrofit;
    private ApiService apiService;
    Spinner spinner ;
    ArrayAdapter<String> adapter;
    List<String> lstQuart;
    TextView nom,adress,tele;
    Switch swpara;
    List<Pharmacie> pharmacieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner_Quart);
        nom = findViewById(R.id.txtNom);
        adress = findViewById(R.id.txtAdress);
        tele = findViewById(R.id.txtTel);
        swpara = findViewById(R.id.swPara);

//        ConstraintLayout layout = findViewById(R.id.r_layout);
//        LinearLayout layout1 = findViewById(R.id.l_Layout);
//        Switch sb = new Switch(this);
//        sb.setTextOff("OFF");
//        sb.setTextOn("ON");
//        sb.setChecked(false);
//        sb.setTextSize(800f);
//        layout1.addView(sb);
//
//        sb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    Toast.makeText(MainActivity.this, "the switcher is "+sb.getTextOn(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "the switcher is "+sb.getTextOff(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        lstQuart = new ArrayList<>();
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
        pharmacieList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstQuart);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
        getPharmacies();



    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String quartier = spinner.getSelectedItem().toString();
        Toast.makeText(this, quartier, Toast.LENGTH_SHORT).show();
        if(!pharmacieList.isEmpty()){
        for (Pharmacie ph :pharmacieList ){
            if (ph.getQuartier().equals(quartier)){
                Toast.makeText(this, ph.getNom(), Toast.LENGTH_SHORT).show();
                nom.setText(ph.getNom());
                adress.setText(ph.getAdress());
                tele.setText(ph.getTel());
                swpara.setChecked(ph.getPara());
            }
        }
        }else {
            Toast.makeText(this, "the list is empty", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public List<Pharmacie> getPharmacies(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        Call<List<Pharmacie>> call = apiService.getAllPharmacies();
        call.enqueue(new Callback<List<Pharmacie>>() {
            @Override
            public void onResponse(Call<List<Pharmacie>> call , Response<List<Pharmacie>> response){
                if(response.isSuccessful() && response.body() != null ){
                    pharmacieList.addAll(response.body());
                    for(Pharmacie ph: response.body()){
                        lstQuart.add(ph.getQuartier());
                    }
                }
                spinner.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<Pharmacie>> call ,Throwable t){
                Toast.makeText(MainActivity.this,""+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("MyTag",t.getMessage());
            }

        });
        return pharmacieList ;
    }


}