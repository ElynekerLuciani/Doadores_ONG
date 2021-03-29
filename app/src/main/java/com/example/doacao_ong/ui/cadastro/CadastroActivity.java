package com.example.doacao_ong.ui.cadastro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doacao_ong.R;
import com.example.doacao_ong.data.model.Usuario;
import com.example.doacao_ong.ui.config.Base64Custom;
import com.example.doacao_ong.ui.config.ConfiguracaoFirebase;
import com.example.doacao_ong.ui.config.UsuarioFirebase;
import com.example.doacao_ong.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private TextView linkLogin;
    private Button buttonSubmit;
    private Spinner spinnerTipoUsuario;
    private EditText inputNome, inputEmail, inputSenha, inputConfirmacaoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        linkLogin = findViewById(R.id.cadastro_link_login);
        buttonSubmit = findViewById(R.id.cadastro_button_submit);
        inputNome = findViewById(R.id.cadastro_input_nome);
        inputEmail = findViewById(R.id.cadastro_input_email);
        inputSenha = findViewById(R.id.cadastro_input_senha);
        spinnerTipoUsuario = findViewById(R.id.cadastro_spinner_tipo);
        inputConfirmacaoSenha = findViewById(R.id.cadastro_input_confirmacao_senha);

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaLogin();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDadosUsuario(v);
            }
        });

        configSpinnerTipoUsuario();
    }

    private void configSpinnerTipoUsuario() {
        String[] tipos = {"Selecionar...", "Doador", "ONG"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, tipos);

        spinnerTipoUsuario.setAdapter(arrayAdapter);
    }

    private void abrirTelaLogin() {
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void validarDadosUsuario(View view) {
        String textNome = inputNome.getText().toString();
        String textEmail = inputEmail.getText().toString();
        String textSenha = inputSenha.getText().toString();
        String textConfirmacaoSenha = inputConfirmacaoSenha.getText().toString();
        String textTipoUsuario = spinnerTipoUsuario.getSelectedItem().toString();

        if (!textNome.isEmpty() && !textEmail.isEmpty() && textSenha.length() >= 6
                && textSenha.equals(textConfirmacaoSenha) && !textTipoUsuario.equals("Selecionar...")) {
            Usuario usuario = new Usuario();

            usuario.setNome(textNome);
            usuario.setTipo(textTipoUsuario);
            usuario.setEmail(textEmail);
            usuario.setSenha(textSenha);

            cadastrarUsuario(usuario);
        } else {
            Toast.makeText(CadastroActivity.this, "Preencha os campos corretamente",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarUsuario(Usuario usuario) {
        FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        auth.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    try {
//                        String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                        usuario.setIdUser(auth.getUid());
                        usuario.salvar();

                        UsuarioFirebase.atualizarNomeUsuario(usuario.getNome());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, "Sucesso ao realizar o Cadastro",
                            Toast.LENGTH_SHORT).show();
                    finish();
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
                }
            }
        });
    }
}