package com.feby.asyst.pizzaorderfeby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.feby.asyst.pizzaorderfeby.utility.Constant;

public class ResultActivity extends AppCompatActivity {

    TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTv = findViewById(R.id.textView_Result);

        if (getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            resultTv.setText(bundle.getString(Constant.KEY_RESULT, ""));
        }
    }
}
