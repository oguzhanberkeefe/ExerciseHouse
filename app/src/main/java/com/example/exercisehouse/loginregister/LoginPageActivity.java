package com.example.exercisehouse.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercisehouse.MainActivity;
import com.example.exercisehouse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPageActivity extends AppCompatActivity {

    TextView createnewaccount;
    EditText inputEmail,inputPassword;
    Button loginbutton;

    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        createnewaccount=findViewById(R.id.createNewAccount);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        loginbutton = findViewById(R.id.btnLogin);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() !=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        createnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPageActivity.this,RegisterPageActivity.class));
            }
         });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    inputEmail.setError("Email Boş Bırakılamaz!");
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    inputEmail.setError("Email Biçimi Doğru Değil!");
                    return;
                }


                if (TextUtils.isEmpty(password))
                {
                    inputPassword.setError("Parola Boş Bırakılamaz!");
                    return;
                }

                if (password.length() < 6)
                {
                    inputPassword.setError("Parolanızı Minimum 6 Karakter Uzunluğunda Giriniz!");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(LoginPageActivity.this, "Başarıyla Giriş Yaptınız!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LoginPageActivity.this, "Bir Sorun Oluştu!" + "Hata: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });


            }
        });

    }

}