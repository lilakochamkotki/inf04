package com.example.aplikacja;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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

        Log.d(TAG, "onCreate() - uruchamianie aplikacji");

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

        Log.d(TAG, "Domyślny numer ustawiony: 000");

        aktualizujObrazy();

        numer.setOnFocusChangeListener((v, hasFocus) -> {
            Log.d(TAG, "Zmiana focusu pola numer. hasFocus = " + hasFocus);

            if (!hasFocus) {
                aktualizujObrazy();
            }
        });

        ok.setOnClickListener(v -> {
            Log.d(TAG, "Kliknięto przycisk OK");
            wyswietlDane();
        });

        Log.d(TAG, "onCreate() - zakończono konfigurację");
    }


    /*
    **********************************************
    nazwa funkcji:
    aktualizujObrazy

    opis funkcji:
    Aktualizuje obrazy osoby i odcisku palca na podstawie numeru
    wpisanego w polu „Numer”.

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

        Log.d(TAG, "aktualizujObrazy() - numer osoby: " + numerOsoby);

        if (numerOsoby.isEmpty()) {

            Log.d(TAG, "Numer jest pusty - czyszczenie obrazów");

            zdjecie.setImageDrawable(null);
            odcisk.setImageDrawable(null);

            return;
        }

        String nazwaZdjecia = numerOsoby + "-zdjecie.jpg";
        String nazwaOdcisku = numerOsoby + "-odcisk.jpg";

        Log.d(TAG, "Próba załadowania zdjęcia: " + nazwaZdjecia);
        Log.d(TAG, "Próba załadowania odcisku: " + nazwaOdcisku);

        zaladujObraz(zdjecie, nazwaZdjecia);
        zaladujObraz(odcisk, nazwaOdcisku);
    }


    private void zaladujObraz(ImageView imageView, String nazwaPliku) {

        Log.d(TAG, "zaladujObraz() - rozpoczęcie ładowania: " + nazwaPliku);

        try {

            InputStream inputStream = getAssets().open(nazwaPliku);

            imageView.setImageBitmap(
                    BitmapFactory.decodeStream(inputStream)
            );

            inputStream.close();

            imageView.setVisibility(View.VISIBLE);

            Log.d(TAG, "Obraz załadowany poprawnie: " + nazwaPliku);

        } catch (Exception e) {

            imageView.setImageDrawable(null);
            imageView.setVisibility(View.INVISIBLE);

            Log.e(
                    TAG,
                    "Nie udało się załadować obrazu: " + nazwaPliku,
                    e
            );
        }
    }


    private void wyswietlDane() {

        Log.d(TAG, "wyswietlDane() - pobieranie danych z formularza");

        String imieOsoby = imie.getText().toString().trim();
        String nazwiskoOsoby = nazwisko.getText().toString().trim();

        Log.d(TAG, "Imię: " + imieOsoby);
        Log.d(TAG, "Nazwisko: " + nazwiskoOsoby);

        if (imieOsoby.isEmpty() || nazwiskoOsoby.isEmpty()) {

            Log.w(TAG, "Nie podano imienia lub nazwiska");

            Toast.makeText(
                    this,
                    "Wprowadź dane",
                    Toast.LENGTH_SHORT
            ).show();

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

        Log.d(TAG, "Wybrany kolor oczu: " + kolorOczu);

        String komunikat =
                imieOsoby + " " +
                        nazwiskoOsoby +
                        " kolor oczu " +
                        kolorOczu;

        Log.d(TAG, "Wyświetlany komunikat: " + komunikat);

        Toast.makeText(
                this,
                komunikat,
                Toast.LENGTH_SHORT
        ).show();
    }
}
