package com.example.doacao_ong.ui.admin.nova_despesa;

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

public class NovaDespesaFragment extends Fragment {

    private NovaDespesaViewModel novaDespesaViewModel;
    private Button buttonAtualizar;
    private EditText editTextValor;
    private EditText editTextTipo;
    private EditText editTextData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        novaDespesaViewModel =
                new ViewModelProvider(this).get(NovaDespesaViewModel.class);
        View root = inflater.inflate(R.layout.lancar_saida_fragment, container, false);

        editTextTipo = root.findViewById(R.id.input_ls_tipo);
        editTextData = root.findViewById(R.id.input_ls_data);
        editTextValor = root.findViewById(R.id.input_ls_valor);
        buttonAtualizar = root.findViewById(R.id.button_ls_atualizar);


        buttonAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novaDespesaViewModel.updateValor(Double.parseDouble(editTextValor.getText().toString()));
                novaDespesaViewModel.updateTipo(editTextTipo.getText().toString());
                novaDespesaViewModel.updateData(editTextData.getText().toString());

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