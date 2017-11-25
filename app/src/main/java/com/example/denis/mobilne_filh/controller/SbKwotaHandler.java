package com.example.denis.mobilne_filh.controller;

import android.widget.SeekBar;

import com.example.denis.mobilne_filh.MainActivity;

import java.text.DecimalFormat;

/**
 * Created by denis on 2017-03-28.
 */

public class SbKwotaHandler implements SeekBar.OnSeekBarChangeListener {

    MainActivity activity;

    public SbKwotaHandler(MainActivity mainActivity) {
        this.activity = mainActivity;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        double kwota = 1000 * progress + 1000;
        activity.getTvKwota().setText(String.valueOf(1000 * progress + 1000));


        double wartosc2 = Double.parseDouble(activity.getSpinner_lata().getSelectedItem().toString().trim());
        double ileratwroku = Double.parseDouble(activity.getSpinner_jeden().getSelectedItem().toString().trim());


        String parapa = activity.getEtOproc().getText().toString();
        if(!parapa.isEmpty()) {
            double jakieoproc = Double.parseDouble(parapa);
            double kwota_2 = kwota;

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
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
