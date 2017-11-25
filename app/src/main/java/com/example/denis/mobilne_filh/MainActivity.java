package com.example.denis.mobilne_filh;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.denis.mobilne_filh.controller.SbKwotaHandler;
import com.example.denis.mobilne_filh.controller.SbProwizjaHandler;
import com.example.denis.mobilne_filh.controller.SpinerHandler;
import com.example.denis.mobilne_filh.controller.SpinerLataHandler;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    //public static final double OPROCENTOWANIE = 0.12;

    SeekBar sbKwota;
    SeekBar sbProwizja;

    TextView tvKwota;
    TextView tvProw;

    int progressKwota;
    int progressProwizja;
    int kwota;

    EditText etWytnik;
    EditText etOproc;

    Spinner spinner_jeden;
    Spinner spinner_lata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sbKwota = (SeekBar)findViewById(R.id.sbKwota);
        sbProwizja = (SeekBar)findViewById(R.id.sbProwizja);

        tvKwota = (TextView)findViewById((R.id.tvKwota));
        tvProw = (TextView)findViewById((R.id.tvProw));


        etWytnik= (EditText)findViewById(R.id.EtWynik);
        etOproc= (EditText)findViewById(R.id.etOproc);

        spinner_jeden = (Spinner)findViewById(R.id.spinner_jeden);
        spinner_lata = (Spinner)findViewById(R.id.spinner_lata);
        spinner_jeden.setSelection(3);
        spinner_lata.setSelection(2);


        progressKwota= sbKwota.getProgress();
        progressProwizja = sbProwizja.getProgress()+1;

        kwota = progressKwota*1000+1000;
        tvKwota.setText(String.valueOf(kwota));

        tvProw.setText(String.valueOf(progressProwizja));

        SbKwotaHandler sbKwotaHandler = new SbKwotaHandler(this);
        sbKwota.setOnSeekBarChangeListener(sbKwotaHandler);


        SbProwizjaHandler sbProwizjaHandler = new SbProwizjaHandler(this);
        sbProwizja.setOnSeekBarChangeListener(sbProwizjaHandler);


        SpinerHandler spinerHandler = new SpinerHandler(this);
        spinner_jeden.setOnItemSelectedListener(spinerHandler);

        SpinerLataHandler spinerLataHandler = new SpinerLataHandler(this);
        spinner_lata.setOnItemSelectedListener(spinerLataHandler);

        etOproc.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //getEtWytnik().setText(s);
                String parapa = s.toString();
                if(!parapa.isEmpty()) {
                    double jakieoproc = Double.parseDouble(parapa);
                    double wartosc2 = Double.parseDouble(getSpinner_lata().getSelectedItem().toString().trim());
                    double ileratwroku = Double.parseDouble(getSpinner_jeden().getSelectedItem().toString().trim());
                    double kwota_2 = 1000 * getSbKwota().getProgress() + 1000;

                    double prowizja = getSbProwizja().getProgress() + 1;
                    double oprocentowanie = prowizja / 100;
                    double pro = kwota_2 * oprocentowanie;
                    double ile_msc = 12 * wartosc2;
                    double ilosc_rat_pom = 12 / ileratwroku;
                    double ilosc_rat_cd = 12 / ilosc_rat_pom;
                    double qq = (1 + ((jakieoproc/100) / ilosc_rat_cd));
                    double ilosc_rat = ile_msc / ilosc_rat_pom;
                    double rata = (kwota_2 + pro) * Math.pow(qq, ilosc_rat) * ((qq - 1) / (Math.pow(qq, ilosc_rat) - 1));


                    DecimalFormat form = new DecimalFormat("0.00");


                    getEtWytnik().setText("do splaty = "+form.format(rata*ilosc_rat) + System.getProperty("line.separator"));
                    getEtWytnik().append("\n rata = "+form.format(rata));
                    getEtWytnik().append("\n koszt kredytu = "+form.format((rata*ilosc_rat)-kwota_2-pro));
                    getEtWytnik().append("\n prowizja = "+form.format(pro));

                }
            }
        });


    }

    public EditText getEtOproc() {
        return etOproc;
    }

    public void setEtOproc(EditText etOproc) {
        this.etOproc = etOproc;
    }

    public SeekBar getSbKwota() {
        return sbKwota;
    }

    public void setSbKwota(SeekBar sbKwota) {
        this.sbKwota = sbKwota;
    }

    public SeekBar getSbProwizja() {
        return sbProwizja;
    }

    public void setSbProwizja(SeekBar sbProwizja) {
        this.sbProwizja = sbProwizja;
    }

    public TextView getTvKwota() {
        return tvKwota;
    }

    public void setTvKwota(TextView tvKwota) {
        this.tvKwota = tvKwota;
    }

    public int getProgressKwota() {
        return progressKwota;
    }

    public void setProgressKwota(int progressKwota) {
        this.progressKwota = progressKwota;
    }

    public int getProgressProwizja() {
        return progressProwizja;
    }

    public void setProgressProwizja(int progressProwizja) {
        this.progressProwizja = progressProwizja;
    }

    public Spinner getSpinner_lata() {
        return spinner_lata;
    }

    public void setSpinner_lata(Spinner spinner_lata) {
        this.spinner_lata = spinner_lata;
    }

    public TextView getTvProw() {
        return tvProw;
    }

    public void setTvProw(TextView tvProw) {
        this.tvProw = tvProw;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(int kwota) {
        this.kwota = kwota;
    }

    public EditText getEtWytnik() {
        return etWytnik;
    }

    public void setEtWytnik(EditText etWytnik) {
        this.etWytnik = etWytnik;
    }

    public Spinner getSpinner_jeden() {
        return spinner_jeden;
    }

    public void setSpinner_jeden(Spinner spinner_jeden) {
        this.spinner_jeden = spinner_jeden;
    }
}
