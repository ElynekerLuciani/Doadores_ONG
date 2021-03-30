package com.example.doacao_ong.ui.loading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.doacao_ong.MainActivity;
import com.example.doacao_ong.R;
import com.example.doacao_ong.model.Usuario;
import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.config.UsuarioFirebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        String userID = ConfiguracaoFirebase.getFirebaseAutenticacao().getUid();
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();

        database.child("usuarios").child(userID).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                Usuario usuarioLogado = dataSnapshot.getValue(Usuario.class);

                UsuarioFirebase.getInstance().setTipo(usuarioLogado.getTipo());
                UsuarioFirebase.getInstance().setNome(usuarioLogado.getNome());
                UsuarioFirebase.getInstance().setEmail(usuarioLogado.getEmail());

                abrirMainActivity();
            }
        });
    }

    private void abrirMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}