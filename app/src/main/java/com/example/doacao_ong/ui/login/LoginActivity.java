package com.example.doacao_ong.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doacao_ong.MainActivity;
import com.example.doacao_ong.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.input_username);
        editTextPassword = findViewById(R.id.input_password);
        buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                if (editTextUsername.getText().toString().equals("usuario") && editTextPassword.getText().toString().equals("usuario")){
                    intent.putExtra("tipo", "usuario");
                } else {
                    intent.putExtra("tipo", "admin");
                }

                startActivity(intent);
            }
        });
    }
}