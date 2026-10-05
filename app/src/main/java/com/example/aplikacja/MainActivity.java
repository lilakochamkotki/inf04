package com.example.aplikacja;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private EditText numer;
    private EditText imie;
    private EditText nazwisko;
    private ImageView zdjecie;
    private ImageView odcisk;
    private RadioButton niebieskie;
    private RadioButton zielone;
    private Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numer = findViewById(R.id.numer);
        imie = findViewById(R.id.imie);
        nazwisko = findViewById(R.id.nazwisko);

        zdjecie = findViewById(R.id.zdjecie);
        odcisk = findViewById(R.id.odcisk);

        niebieskie = findViewById(R.id.niebieskie);
        zielone = findViewById(R.id.zielone);
        RadioButton piwne;
        piwne = findViewById(R.id.piwne);

        ok = findViewById(R.id.ok);

        numer.setText("000");
        aktualizujObrazy();

        numer.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                aktualizujObrazy();
            }
        });

        ok.setOnClickListener(v -> wyswietlDane());
    }


    /*

**********************************************
nazwa funkcji:
aktualizujObrazy

opis funkcji:
Aktualizuje obrazy osoby i odcisku palca na podstawie numeru wpisanego w polu „Numer”.

parametry:
brak

zwracany typ i opis:
void - funkcja nie zwraca żadnej wartości

autor:
0000000000000000
**********************************************
*/


    private void aktualizujObrazy() {
        String numerOsoby = numer.getText().toString().trim();

        if (numerOsoby.isEmpty()) {
            zdjecie.setImageDrawable(null);
            odcisk.setImageDrawable(null);
            return;
        }

        zaladujObraz(zdjecie, numerOsoby + "-zdjecie.jpg");
        zaladujObraz(odcisk, numerOsoby + "-odcisk.jpg");
    }


    private void zaladujObraz(ImageView imageView, String nazwaPliku) {
        try {
            InputStream inputStream = getAssets().open(nazwaPliku);
            imageView.setImageBitmap(BitmapFactory.decodeStream(inputStream));
            inputStream.close();
            imageView.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            imageView.setImageDrawable(null);
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    private void wyswietlDane() {
        String imieOsoby = imie.getText().toString().trim();
        String nazwiskoOsoby = nazwisko.getText().toString().trim();

        if (imieOsoby.isEmpty() || nazwiskoOsoby.isEmpty()) {
            Toast.makeText(this, "Wprowadź dane", Toast.LENGTH_SHORT).show();
            return;
        }

        String kolorOczu;

        if (niebieskie.isChecked()) {
            kolorOczu = "niebieskie";
        } else if (zielone.isChecked()) {
            kolorOczu = "zielone";
        } else {
            kolorOczu = "piwne";
        }

        Toast.makeText(
                this,
                imieOsoby + " " + nazwiskoOsoby + " kolor oczu " + kolorOczu,
                Toast.LENGTH_SHORT
        ).show();
    }
}
