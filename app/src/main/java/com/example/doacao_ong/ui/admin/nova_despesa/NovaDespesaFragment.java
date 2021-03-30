package com.example.doacao_ong.ui.admin.nova_despesa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doacao_ong.R;
import com.example.doacao_ong.model.Despesa;

public class NovaDespesaFragment extends Fragment {

    private Button submitButton;
    private Spinner spinnerTipo;
    private EditText inputData;
    private EditText inputValor;
    private EditText inputDescricao;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.nova_despesa_fragment, container, false);

        inputDescricao = root.findViewById(R.id.nova_despesa_input_descricao);
        spinnerTipo = root.findViewById(R.id.nova_despesa_spinner_tipo);
        inputData = root.findViewById(R.id.nova_despesa_input_data);
        inputValor = root.findViewById(R.id.nova_despesa_input_valor);
        submitButton = root.findViewById(R.id.nova_despesa_submit_button);

        configSpinnerTipoDespesa();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    Despesa despesa = new Despesa();
                    despesa.setData(inputData.getText().toString());
                    despesa.setValor(inputValor.getText().toString());
                    despesa.setTipo(spinnerTipo.getSelectedItem().toString());
                    despesa.setDescrição(inputDescricao.getText().toString());
                    despesa.adicionar();
                    clearInputs();

                    Toast.makeText(getActivity(), "Despesa cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Campos inválidos!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return root;
    }

    private void configSpinnerTipoDespesa() {
        String[] tipos = {"Selecionar...", "Administrativa", "Doação"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tipos);

        spinnerTipo.setAdapter(arrayAdapter);
    }

    private boolean validarCampos() {
        String data = inputData.getText().toString();
        String valor = inputValor.getText().toString();
        String descricao = inputDescricao.getText().toString();
        String tipo = spinnerTipo.getSelectedItem().toString();

        return !data.isEmpty() && !valor.isEmpty() && !descricao.isEmpty() && !tipo.equals("Selecionar...");
    }

    private void clearInputs() {
        inputValor.getText().clear();
        inputDescricao.getText().clear();
        inputData.getText().clear();
        spinnerTipo.setSelection(0);
    }
}