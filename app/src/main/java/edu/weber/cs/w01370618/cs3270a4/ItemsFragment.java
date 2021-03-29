package edu.weber.cs.w01370618.cs3270a4;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;

public class ItemsFragment extends Fragment {

    private View root;
    private EditText field1;
    private EditText field2;
    private EditText field3;
    private EditText field4;
    private double val1;
    private double val2;
    private double val3;
    private double val4;
    private double totalValue;
    private OnEditTextChanged mCallback;

    interface OnEditTextChanged {
        void onEditTextUpdate(String value);
    }

    private final TextWatcher itemsWatcher = new TextWatcher() {



        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {


            if (!field1.getText().toString().isEmpty()) {
                val1 = Double.parseDouble(field1.getText().toString());

            } else {
                val1 = 0.0;
            }

            if (!field2.getText().toString().isEmpty()) {
                val2 = Double.parseDouble(field2.getText().toString());
            } else {
                val2 = 0.0;
            }

            if (!field3.getText().toString().isEmpty()) {
                val3 = Double.parseDouble(field3.getText().toString());
            } else {
                val3 = 0.0;
            }

            if (!field4.getText().toString().isEmpty()) {
                val4 = Double.parseDouble(field4.getText().toString());
            } else {
                val4 = 0.0;
            }

            totalValue = val1 + val2 + val3 + val4;
            mCallback.onEditTextUpdate(String.valueOf(totalValue));
        }
    };

    public ItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_items, container, false);


    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnEditTextChanged) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "must implement OnEditTextChanged interface");
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        field1 = root.findViewById(R.id.item_amount_1);
        field1.addTextChangedListener(itemsWatcher);


        field2 = root.findViewById(R.id.item_amount_2);
        field2.addTextChangedListener(itemsWatcher);

        field3 = root.findViewById(R.id.item_amount_3);
        field3.addTextChangedListener(itemsWatcher);

        field4 = root.findViewById(R.id.item_amount_4);
        field4.addTextChangedListener(itemsWatcher);

    }


}