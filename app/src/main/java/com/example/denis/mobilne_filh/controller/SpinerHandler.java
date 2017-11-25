package com.example.denis.mobilne_filh.controller;

import android.view.View;
import android.widget.AdapterView;

import com.example.denis.mobilne_filh.MainActivity;

import java.text.DecimalFormat;

/**
 * Created by denis on 2017-04-11.
 */

public class SpinerHandler implements AdapterView.OnItemSelectedListener {


    MainActivity activity;

    public SpinerHandler(MainActivity activity) {
        this.activity = activity;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String wartosc = activity.getSpinner_jeden().getItemAtPosition(position).toString().trim();
        double ileratwroku = Double.parseDouble(wartosc);
        double wartosc2 = Double.parseDouble(activity.getSpinner_lata().getSelectedItem().toString().trim());

        String parapa = activity.getEtOproc().getText().toString();
        if(!parapa.isEmpty()) {
            double jakieoproc = Double.parseDouble(parapa);
            double kwota_2 = 1000 * activity.getSbKwota().getProgress() + 1000;

            double prowizja = activity.getSbProwizja().getProgress() + 1;
            double oprocentowanie = prowizja / 100;
            double pro = kwota_2 * oprocentowanie;
            double ile_msc = 12 * wartosc2;
            double ilosc_rat_pom = 12 / ileratwroku;
            double ilosc_rat_cd = 12 / ilosc_rat_pom;
            double qq = (1 + ((jakieoproc/100) / ilosc_rat_cd));
            double ilosc_rat = ile_msc / ilosc_rat_pom;
            double rata = (kwota_2 + pro) * Math.pow(qq, ilosc_rat) * ((qq - 1) / (Math.pow(qq, ilosc_rat) - 1));


            DecimalFormat form = new DecimalFormat("0.00");


            activity.getEtWytnik().setText("do splaty = "+form.format(rata*ilosc_rat) + System.getProperty("line.separator"));
            activity.getEtWytnik().append("\n rata = "+form.format(rata));
            activity.getEtWytnik().append("\n koszt kredytu = "+form.format((rata*ilosc_rat)-kwota_2-pro));
            activity.getEtWytnik().append("\n prowizja = "+form.format(pro));

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
