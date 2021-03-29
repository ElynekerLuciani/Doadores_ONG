package com.example.doacao_ong.ui.admin.meu_perfil;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doacao_ong.R;
import com.example.doacao_ong.model.Ong;
import com.example.doacao_ong.ui.config.ConfiguracaoFirebase;
import com.example.doacao_ong.ui.config.UsuarioFirebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;

public class MeuPerfilFragment extends Fragment {

    private MeuPerfilViewModel meuPerfilViewModel;
    private Button buttonUpdate;
    private EditText inputNome;
    private EditText inputMissaoONG;
    private EditText inputDescricaoONG;
    private EditText inputCausaONG;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.meu_perfil_fragment, container, false);

        meuPerfilViewModel = new MeuPerfilViewModel();

        inputNome = root.findViewById(R.id.meu_perfil_input_nome);
        inputMissaoONG = root.findViewById(R.id.meu_perfil_input_missao_ong);
        inputDescricaoONG = root.findViewById(R.id.meu_perfil_input_descricao_ong);
        inputCausaONG = root.findViewById(R.id.meu_perfil_input_causa_ong);
        buttonUpdate = root.findViewById(R.id.meu_perfil_submit_button);

        String nomeUsuario = UsuarioFirebase.getInstance().getNome();
        inputNome.setText(nomeUsuario);

        meuPerfilViewModel.getDadosOng();

        meuPerfilViewModel.getOngLiveData().observe(getViewLifecycleOwner(), new Observer<Ong>() {
            @Override
            public void onChanged(Ong ong) {
                inputMissaoONG.setText(ong.getMissao());
                inputCausaONG.setText(ong.getCausa());
                inputDescricaoONG.setText(ong.getDescricao());
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    UsuarioFirebase.getInstance().setNome(inputNome.getText().toString());
                    UsuarioFirebase.getInstance().atualizar();

                    Ong ong = new Ong();
                    ong.setMissao(inputMissaoONG.getText().toString());
                    ong.setCausa(inputCausaONG.getText().toString());
                    ong.setDescricao(inputDescricaoONG.getText().toString());
                    ong.atualizar();

                    Toast.makeText(getActivity(), "Perfil atualizado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Campos inválidos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    private boolean validarCampos() {
        String nome = inputNome.getText().toString();
        String missao = inputMissaoONG.getText().toString();
        String causa = inputCausaONG.getText().toString();
        String descricao = inputDescricaoONG.getText().toString();

        return !nome.isEmpty() && !missao.isEmpty() && !causa.isEmpty() && !descricao.isEmpty();
    }
}