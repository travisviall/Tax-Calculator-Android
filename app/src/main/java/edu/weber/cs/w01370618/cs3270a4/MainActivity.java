package edu.weber.cs.w01370618.cs3270a4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ItemsFragment.OnEditTextChanged,
        TaxFragment.OnSeekChanged{

    private FragmentManager fm;
    private TotalsFragment totalsFragment;
    private TaxFragment taxFragment;
    private double percent;
    private String itemsTotal;  //total Items box value


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.totals_fragment, new TotalsFragment(), "totals")
                .commit();

    }

    //String value is total value of Items Boxes
    @Override
    public void onEditTextUpdate(String value) {

        if(totalsFragment == null)
        totalsFragment = (TotalsFragment) fm.findFragmentByTag("totals");

        if(totalsFragment != null) {

            this.itemsTotal = value;

            //sends the value to from the ItemsFragment listener to TotalsFragment
            totalsFragment.setTotal(value, percent);

        }

        if(taxFragment == null)
            taxFragment = (TaxFragment) fm.findFragmentById(R.id.tax_fragment);

        if(taxFragment != null)
            taxFragment.setTaxAmount(value, percent);

    }

    @Override
    public void onSeekUpdate(double percent) {

        this.percent = percent;

        if(totalsFragment == null)
            totalsFragment = (TotalsFragment) fm.findFragmentByTag("totals");

        if(totalsFragment != null)
            totalsFragment.setTotal(itemsTotal, percent);

        if(taxFragment != null)
            taxFragment.setTaxAmount(itemsTotal, percent);
    }

}