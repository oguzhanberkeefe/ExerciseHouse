package com.example.exercisehouse.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercisehouse.MainActivity;
import com.example.exercisehouse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterPageActivity extends AppCompatActivity {


    TextView alreadyhaveaccount;
    Button kayitol;
    EditText inputEmail,inputPassword,inputName,inputSurname,inputPhone;


    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        alreadyhaveaccount=findViewById(R.id.alreadyHaveaccount);
        kayitol = findViewById(R.id.btnRegister);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputName = findViewById(R.id.inputName);
        inputSurname = findViewById(R.id.inputSurname);
        inputPhone = findViewById(R.id.inputPhone);
        fAuth= FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() !=null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        alreadyhaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterPageActivity.this,LoginPageActivity.class));
            }
        });

        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String name = inputName.getText().toString().trim();
                String surname = inputSurname.getText().toString().trim();
                String phone = inputPhone.getText().toString().trim();

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

                if (TextUtils.isEmpty(name))
                {
                    inputName.setError("İsim Boş Bırakılamaz!");
                    return;
                }

                if (TextUtils.isEmpty(surname))
                {
                    inputSurname.setError("Soyisim Boş Bırakılamaz!");
                    return;
                }

                if (TextUtils.isEmpty(phone))
                {
                    inputPhone.setError("Telefon Numarası Boş Bırakılamaz!");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(RegisterPageActivity.this, "Başarıyla Üye Oldunuz", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Uyeler").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Email",email);
                            user.put("Parola",password);
                            user.put("Ad",name);
                            user.put("Soyad",surname);
                            user.put("Telefon",phone);
                            user.put("Userid",userID);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG","Basarili"+userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(RegisterPageActivity.this, "Bir Sorun Oluştu!" + "Hata: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}