package com.example.doacao_ong.ui.usuario;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doacao_ong.R;
import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.config.UsuarioFirebase;
import com.example.doacao_ong.model.Doacao;
import com.example.doacao_ong.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class DoarFragment extends Fragment {

    EditText inputONG;
    EditText inputData;
    EditText inputValor;
    Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_doar, container, false);

        inputONG = root.findViewById(R.id.doar_input_ong);
        inputData = root.findViewById(R.id.doar_input_data);
        inputValor = root.findViewById(R.id.doar_input_valor);
        submitButton = root.findViewById(R.id.doar_submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    String nomeOng = inputONG.getText().toString();
                    String data = inputData.getText().toString();
                    String valor = inputValor.getText().toString();

                    Doacao doacao = new Doacao();
                    doacao.setData(data);
                    doacao.setValor(valor);
                    doacao.setNomeRecebedor(nomeOng);
                    doacao.setIdDoador(UsuarioFirebase.getUsuarioAtual().getUid());
                    doacao.setNomeDoador(UsuarioFirebase.getInstance().getNome());

                    System.out.println("-------> Doacao: " + doacao);

                    getONG(doacao);
                } else {
                    Toast.makeText(getActivity(), "Preencha os campos corretamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    private boolean validarCampos() {
        String nomeOng = inputONG.getText().toString();
        String data = inputData.getText().toString();
        String valor = inputValor.getText().toString();

        return !nomeOng.isEmpty() && !data.isEmpty() && !valor.isEmpty();
    }

    private void getONG(Doacao doacao) {
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();
        database.child("usuarios").orderByChild("nome").equalTo(doacao.getNomeRecebedor())
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Usuario usuario = snapshot.getValue(Usuario.class);
                    if (usuario.getNome().equals(doacao.getNomeRecebedor())) {
                        doacao.setIdRecebedor(snapshot.getKey());
                        doacao.adicionar();
                        Toast.makeText(getActivity(), "Doação cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "ONG não encontrada! Verifique o nome e tente novamente", Toast.LENGTH_SHORT).show();
            }
        });
    }
}