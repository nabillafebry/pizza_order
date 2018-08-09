package com.feby.asyst.pizzaorderfeby;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.feby.asyst.pizzaorderfeby.utility.Constant;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    EditText etNama;
    CheckBox cb_1, cb_2, cb_3;
    ImageButton imgBtn;
    RadioGroup size1,size2,size3;
    RadioButton size1S, size1M, size1L, size2S, size2M, size2L, size3S, size3M, size3L;
    Switch switchUp;
    Button btnNama, btnSave;
    String Snama, pizza="", selectedSize1="", selectedSize2="", selectedSize3="", switchUpsize="No";
    ArrayList<String> listPizza = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.nama);
        btnNama = findViewById(R.id.submit_name);
        imgBtn = findViewById(R.id.img_btn);

        cb_1 = findViewById(R.id.cb1);
        cb_2 = findViewById(R.id.cb2);
        cb_3 = findViewById(R.id.cb3);
        cb_1.setOnCheckedChangeListener(this);
        cb_2.setOnCheckedChangeListener(this);
        cb_3.setOnCheckedChangeListener(this);

        size1S = findViewById(R.id.size_1S);
        size1M = findViewById(R.id.size_1M);
        size1L = findViewById(R.id.size_1L);
        size2S = findViewById(R.id.size_2S);
        size2M = findViewById(R.id.size_2M);
        size2L = findViewById(R.id.size_2L);
        size3S = findViewById(R.id.size_3S);
        size3M = findViewById(R.id.size_3M);
        size3L = findViewById(R.id.size_3L);

        size1 = findViewById(R.id.size_1);
        size2 = findViewById(R.id.size_2);
        size3 = findViewById(R.id.size_3);
        size1.setOnCheckedChangeListener(this);
        size2.setOnCheckedChangeListener(this);
        size3.setOnCheckedChangeListener(this);

        switchUp = findViewById(R.id.switch_up);
        switchUp.setOnCheckedChangeListener(this);

        btnSave = findViewById(R.id.submit_save);

        btnNama.setOnClickListener(this);
        imgBtn.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.submit_name:
                Snama = etNama.getText().toString();
                if (!TextUtils.isEmpty(Snama)){
                    cb_1.setEnabled(true);
                    cb_2.setEnabled(true);
                    cb_3.setEnabled(true);
                    size1S.setEnabled(true);
                    size1M.setEnabled(true);
                    size1L.setEnabled(true);
                    size2S.setEnabled(true);
                    size2M.setEnabled(true);
                    size2L.setEnabled(true);
                    size3S.setEnabled(true);
                    size3M.setEnabled(true);
                    size3L.setEnabled(true);
                    switchUp.setEnabled(true);
                    btnSave.setEnabled(true);
                    etNama.setEnabled(false);
                    imgBtn.setEnabled(false);
            }
                break;

            case R.id.img_btn:
                Snama = etNama.getText().toString();

                etNama.getText().clear();
                break;
            case R.id.submit_save:
                Snama =etNama.getText().toString();

                    pizza = "";
                    for (int i=0;i<listPizza.size();i++){
                        pizza = pizza+ " "+listPizza.get(i);
                    }

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setTitle("Confirmation").setCancelable(false).setMessage("Are You Sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String result ="Nama : "+Snama+"\n"+"Pesanan : "+pizza + "\n" + "Size : " + selectedSize1+ " "+ selectedSize2+ " "+ selectedSize3 + "\n" + "UpSize? " + switchUpsize;
                                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                                intent.putExtra(Constant.KEY_RESULT,result);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", null).show();

                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();

        switch (id){
            case R.id.cb1 :
                if (isChecked){
                    listPizza.add("American Cheesy");

                }
                else {
                    listPizza.remove("American Cheesy");
                }
                break;

            case R.id.cb2 :
                if (isChecked){
                    listPizza.add("MeatLovers");
                }
                else {
                    listPizza.remove("MeatLovers");
                }
                break;

            case R.id.cb3 :
                if (isChecked){
                    listPizza.add("Oriental Pizza");
                }
                else {
                    listPizza.remove("Oriental Pizza");
                }
                break;

            case R.id.switch_up :
                if (switchUp.isChecked())
                {
                    switchUpsize = "Yes";
                }
                else {
                    switchUpsize = "No";
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.size_1S:
            selectedSize1 = "Small";
            break;
            case R.id.size_1M:
                selectedSize1 = "Medium";
                break;
            case R.id.size_1L:
                selectedSize1 = "Large";
                break;
            case R.id.size_2S:
                selectedSize2 = "Small";
                break;
            case R.id.size_2M:
                selectedSize2 = "Medium";
                break;
            case R.id.size_2L:
                selectedSize2 = "Large";
                break;
            case R.id.size_3S:
                selectedSize3 = "Small";
                break;
            case R.id.size_3M:
                selectedSize3 = "Medium";
                break;
            case R.id.size_3L:
                selectedSize3 = "Large";
                break;
        }

    }
}
