package com.example.exercisehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercisehouse.loginregister.LoginPageActivity;
import com.example.exercisehouse.loginregister.RegisterPageActivity;
import com.google.firebase.auth.FirebaseAuth;

public class FirstScreenActivity extends AppCompatActivity {

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        TextView kayitoltxt = findViewById(R.id.kayitol);
        TextView girisyaptxt = findViewById(R.id.girisyap);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() !=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        kayitoltxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstScreenActivity.this, RegisterPageActivity.class));
            }
        });

        girisyaptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstScreenActivity.this, LoginPageActivity.class));
            }
        });
    }
}