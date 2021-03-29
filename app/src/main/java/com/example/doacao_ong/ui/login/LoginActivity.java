package com.example.doacao_ong.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doacao_ong.LoadingActivity;
import com.example.doacao_ong.R;
import com.example.doacao_ong.model.Usuario;
import com.example.doacao_ong.ui.cadastro.CadastroActivity;
import com.example.doacao_ong.ui.config.ConfiguracaoFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    private Button buttonLogin;
    private TextView linkCadastro;
    private EditText inputEmail, inputPassword;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = autenticacao.getCurrentUser();
        if(user != null) {
            abrirTelaPrincipal();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        inputEmail = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        buttonLogin = findViewById(R.id.button_login);
        linkCadastro = findViewById(R.id.login_link_cadastro);

        linkCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaCadastro();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autenticarUsuario(v);
                /*Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                if (editTextEmail.getText().toString().equals("usuario") && editTextPassword.getText().toString().equals("usuario")){
                    intent.putExtra("tipo", "usuario");
                } else {
                    intent.putExtra("tipo", "admin");
                }

                startActivity(intent);*/
            }
        });
    }

    public void abrirTelaCadastro() {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    public void abrirTelaPrincipal() {
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        Intent intent = new Intent(LoginActivity.this, LoadingActivity.class);

        startActivity(intent);
    }

    public void autenticarUsuario(View view) {
        String email = inputEmail.getText().toString();
        String senha = inputPassword.getText().toString();

        if (!email.isEmpty() && senha.length() >= 6) {
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);

            logarUsuario(usuario);
        } else {
            Toast.makeText(LoginActivity.this, "Preencha os campos corretamente",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void logarUsuario(Usuario usuario) {
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirTelaPrincipal();
                } else {
                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Digite uma senha mais forte";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Por favor, digite uma e-mail válido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "Esta conta já utiliza este email";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this, excecao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}