package com.example.doacao_ong.ui.meu_perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doacao_ong.R;

public class MeuPerfilFragment extends Fragment {

    private MeuPerfilViewModel meuPerfilViewModel;
    private Button buttonUpdate;
    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private EditText editTextConfirmSenha;
    private EditText editTextMissaoONG;
    private EditText editTextDescricao;
    private EditText editTextCausa;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        meuPerfilViewModel =
                new ViewModelProvider(this).get(MeuPerfilViewModel.class);
        View root = inflater.inflate(R.layout.meu_perfil_fragment, container, false);

        editTextNome = root.findViewById(R.id.input_mp_nome);
        editTextEmail = root.findViewById(R.id.input_mp_email);
        editTextSenha = root.findViewById(R.id.input_mp_senha);
        editTextConfirmSenha = root.findViewById(R.id.input_mp_confirmacao_senha);
        editTextMissaoONG = root.findViewById(R.id.input_mp_missao_ong);
        editTextDescricao = root.findViewById(R.id.input_mp_descricao);
        editTextCausa = root.findViewById(R.id.input_mp_causa);

        buttonUpdate = root.findViewById(R.id.button_mp_atualizar);
        buttonUpdate.setText("Atualizar");
        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                meuPerfilViewModel.updateNome(editTextNome.getText().toString());
                meuPerfilViewModel.updateEmail(editTextEmail.getText().toString());
                meuPerfilViewModel.updateSenha(editTextSenha.getText().toString());
                meuPerfilViewModel.updateConfirmSenha(editTextConfirmSenha.getText().toString());
                meuPerfilViewModel.updateMissaoONG(editTextMissaoONG.getText().toString());
                meuPerfilViewModel.updateDescricao(editTextDescricao.getText().toString());
                meuPerfilViewModel.updateCausa(editTextCausa.getText().toString());

                clearInputs();
            }
        });

        return root;
    }

    private void clearInputs(){
        editTextNome.getText().clear();
        editTextEmail.getText().clear();
        editTextSenha.getText().clear();
        editTextConfirmSenha.getText().clear();
        editTextMissaoONG.getText().clear();
        editTextDescricao.getText().clear();
        editTextCausa.getText().clear();
    }
}