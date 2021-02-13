package com.example.doacao_ong.ui.admin.lancar_saida;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.doacao_ong.R;

public class LancarSaidaFragment extends Fragment {

    private LancarSaidaViewModel lancarSaidaViewModel;
    private Button buttonAtualizar;
    private EditText editTextValor;
    private EditText editTextTipo;
    private EditText editTextData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lancarSaidaViewModel =
                new ViewModelProvider(this).get(LancarSaidaViewModel.class);
        View root = inflater.inflate(R.layout.lancar_saida_fragment, container, false);

        editTextValor = root.findViewById(R.id.input_ls_valor);
        editTextTipo = root.findViewById(R.id.input_ls_tipo);
        editTextData = root.findViewById(R.id.input_ls_data);

        buttonAtualizar = root.findViewById(R.id.button_ls_atualizar);
        buttonAtualizar.setText("Atualizar");

        buttonAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancarSaidaViewModel.updateValor(Double.parseDouble(editTextValor.getText().toString()));
                lancarSaidaViewModel.updateTipo(editTextTipo.getText().toString());
                lancarSaidaViewModel.updateData(editTextData.getText().toString());

                clearInputs();
            }
        });

        return root;
    }

    private void clearInputs(){
        editTextValor.getText().clear();
        editTextTipo.getText().clear();
        editTextData.getText().clear();
    }
}