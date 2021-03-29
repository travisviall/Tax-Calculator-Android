package edu.weber.cs.w01370618.cs3270a4;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import java.util.Objects;

public class TaxFragment extends Fragment {

    private View root;
    private SeekBar seekBar;
    private TextView taxValue;
    private TextView taxTotal;
    private TextView taxAmount;
    private double percentValue = 1.0;
    private OnSeekChanged mCallback;

    public interface OnSeekChanged {
        void onSeekUpdate(double percent);
    }

    public TaxFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_tax, container, false);


    }

    @Override
    public void onResume() {
        super.onResume();

        seekBar = root.findViewById(R.id.seek_bar);
        taxTotal = root.findViewById(R.id.tax_amount_value);
        taxValue = root.findViewById(R.id.tax_rate_value);

        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        int position = prefs.getInt("seek_position", 0);
        seekBar.setProgress(position);
        taxValue.setText(String.valueOf(position));
        Log.d("seek bar position: ", String.valueOf(position));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                percentValue = (progress / 4.00) / 100 ;
                mCallback.onSeekUpdate(percentValue);

                NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.US);
                taxValue.setText(numberFormat.format(percentValue));
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        try {
                mCallback = (OnSeekChanged) activity;
        } catch(ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnSeekChanged interface");
        }
    }

    @Override
    public void onPause() {
        super.onPause();

            SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = prefs.edit();
            prefsEditor.putInt("seek_position", seekBar.getProgress());
            prefsEditor.apply();
    }

    public void setTaxAmount(String value, double percent) {

        taxAmount = root.findViewById(R.id.tax_amount_value);

        //Log.d("total = ", value);
        BigDecimal tax = new BigDecimal(percent);
        BigDecimal total = new BigDecimal(value);
        BigDecimal taxes = tax.multiply(total);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

        taxAmount.setText(numberFormat.format(taxes));

    }

}