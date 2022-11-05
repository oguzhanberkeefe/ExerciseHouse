package com.example.exercisehouse.urunler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercisehouse.R;

import java.util.Random;

public class EvdeEgzersizDetay extends AppCompatActivity {


    private Button sepeteekle;
    private TextView adetbirim,id2,isim;
    private ImageView adeteksilt,adetarttır,backarrow;
    int adet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evdeegzersizdetay);

        backarrow = findViewById(R.id.backarrow);
        id2 = findViewById(R.id.id);
        isim = findViewById(R.id.name);
        TextView name = (TextView) findViewById(R.id.name);

        adet = 1;

        sepeteekle = (Button) findViewById(R.id.sepeteekle);
        adetbirim = (TextView) findViewById(R.id.adetbirim);
        adeteksilt = (ImageView) findViewById(R.id.adeteksilt);
        adetarttır = (ImageView) findViewById(R.id.adetarttır);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (adet == 1)
        {
            adetbirim.setText(adet + " " + "Adet");
        }

        if (adet == 1)
        {
            adetbirim.setText(adet + " " + "Adet");
        }


        adetarttır.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adet++;
                adetbirim.setText(adet + " " + "Adet");
            }
        });

        adeteksilt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adet--;
                adetbirim.setText(adet + " " + "Adet");

                if (adet <1)
                {
                    adet = 1;
                    adetbirim.setText(adet + " " + "Adet");
                }
            }
        });

        sepeteekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int minimum = 1;
                int maksimum = 2147483647;

                int randomSayi = new Random().nextInt((maksimum - minimum) + 1) + minimum;

                id2.setText(String.valueOf(randomSayi));

                CharSequence randomid=id2.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.
                CharSequence ismigonder= isim.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.
                CharSequence adetgonder = adetbirim.getText();//Yazı dizisi oluşturup kullanıcının yazdığı yazıyı buraya attık.

            }
        });



    }
}