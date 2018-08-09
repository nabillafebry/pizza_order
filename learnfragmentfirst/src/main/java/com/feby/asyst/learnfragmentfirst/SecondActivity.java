package com.feby.asyst.learnfragmentfirst;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.feby.asyst.learnfragmentfirst.fragment.FourthFragment;
import com.feby.asyst.learnfragmentfirst.fragment.InputBottomSheet;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, FourthFragment.OnSubmitButtonListener, InputBottomSheet.OnSubmitButtonListener{

    TextView tvNama, tvAlamat, tvTglLahir;
    Button inputBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvNama = findViewById(R.id.nama_textview);
        tvAlamat = findViewById(R.id.alamat_textview);
        tvTglLahir = findViewById(R.id.tgl_lahir_textview);
        inputBtn = findViewById(R.id.input_btn);

        inputBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_btn :

                android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                FourthFragment fourthFragment = FourthFragment.newInstance(tvNama.getText().toString(), tvAlamat.getText().toString(), tvTglLahir.getText().toString());

                transaction.replace(android.R.id.content, fourthFragment);
                transaction.addToBackStack(null);


                transaction.commit();

                break;
        }
    }

    @Override
    public void onSubmitButton(String nama, String alamat, String tgl_lahir) {
        tvNama.setText(nama);
        tvAlamat.setText(alamat);
        tvTglLahir.setText(tgl_lahir);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.input_main_menu :

                InputBottomSheet inputBottomSheet = InputBottomSheet.newInstance(tvNama.getText().toString(), tvAlamat.getText().toString(), tvTglLahir.getText().toString());

                inputBottomSheet.show(getSupportFragmentManager(), "inputbottomsheet");

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
