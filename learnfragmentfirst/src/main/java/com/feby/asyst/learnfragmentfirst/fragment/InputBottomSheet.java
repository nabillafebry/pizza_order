package com.feby.asyst.learnfragmentfirst.fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.feby.asyst.learnfragmentfirst.R;
import com.feby.asyst.learnfragmentfirst.utility.DateUtils;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputBottomSheet extends BottomSheetDialogFragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    public interface OnSubmitButtonListener{
        void onSubmitButton(String nama, String alamat, String tgl_lahir);
    }

    EditText etNama, etAlamat;
    Button submitBtn;
    TextView tvTglLahir;
    ImageView ivDate;
    OnSubmitButtonListener listener;

    DatePickerDialog datePickerDialog;


    public InputBottomSheet() {
        // Required empty public constructor
    }
    public static InputBottomSheet newInstance(String nama, String alamat, String tgl_lahir) {

        InputBottomSheet fragment = new InputBottomSheet();

        Bundle bundle = new Bundle();
        bundle.putString("nama", nama);
        bundle.putString("alamat", alamat);
        bundle.putString("tgl_lahir", tgl_lahir);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fourth, container, false);

        etNama = v.findViewById(R.id.nama_edittext);
        etAlamat = v.findViewById(R.id.alamat_edittext);
        tvTglLahir = v.findViewById(R.id.date_textview);
        ivDate = v.findViewById(R.id.date_imageview);

        submitBtn = v.findViewById(R.id.submit_button);

        if (getArguments() != null) {
            etNama.setText(getArguments().getString("nama", ""));
            etAlamat.setText(getArguments().getString("alamat", ""));
            tvTglLahir.setText(getArguments().getString("tgl_lahir", ""));
        }

        Calendar calendar = Calendar.getInstance();
        String selectedDate = tvTglLahir.getText().toString();

        int year = 2000;
        int month = 0;
        int day = 1;

        if (!selectedDate.equalsIgnoreCase("")){

            calendar.setTime(DateUtils.dateFromString("dd MMMM yyyy", selectedDate));

            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }

        datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);


        submitBtn.setOnClickListener(this);
        ivDate.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        String Snama = etNama.getText().toString();
        String Salamat = etAlamat.getText().toString();
        switch (v.getId()){
            case R.id.submit_button :
                listener.onSubmitButton(etNama.getText().toString(), etAlamat.getText().toString(), tvTglLahir.getText().toString());

                dismiss();
                break;

            case R.id.date_imageview :

                datePickerDialog.show();

                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth+" "+(month+1)+" "+year;

        tvTglLahir.setText(DateUtils.formatDate("dd MM yyyy", "dd MMMM yyyy", date));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof InputBottomSheet.OnSubmitButtonListener){
            listener = (InputBottomSheet.OnSubmitButtonListener)context;
        }
        else {
            throw new RuntimeException(context.toString()+ "activity harus implemen OnSubmitListener");
        }
    }

}
