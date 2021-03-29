package edu.weber.cs.w01370618.cs3270a4;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.Locale;

public class TotalsFragment extends Fragment {

    private View root;
    private TextView total;
    private double totalAmount = 0;


    private final TextWatcher totalWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {



        }

        @Override
        public void afterTextChanged(Editable s) {

            //Log.d("Total value is: ", s.toString());
        }
    };

    public TotalsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_totals, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        total = root.findViewById(R.id.total_amount_value);
        total.addTextChangedListener(totalWatcher);
    }



    public void setTotal(String value, double percent){

        totalAmount = Double.parseDouble(value);

        BigDecimal amount = new BigDecimal(totalAmount);
        BigDecimal tax = new BigDecimal(1 + percent);
        MathContext m = new MathContext(2);
        amount.round(m);
        BigDecimal grandTotal = amount.multiply(tax);

        NumberFormat numberFormat =NumberFormat.getCurrencyInstance(Locale.US);

        total.setText(numberFormat.format(grandTotal.doubleValue()));
    }
}