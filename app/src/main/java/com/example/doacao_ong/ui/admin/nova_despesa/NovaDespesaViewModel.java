package com.example.doacao_ong.ui.admin.nova_despesa;

import androidx.lifecycle.ViewModel;

import com.example.doacao_ong.model.Despesa;

public class NovaDespesaViewModel extends ViewModel {
    private Despesa despesa;

    public NovaDespesaViewModel() {
        this.despesa = new Despesa();
    }

    public Despesa getDespesa() {
        return despesa;
    }

    public void updateValor(double valor){
        this.despesa.setValor(valor);
    }

    public void updateTipo(String tipo){
        this.despesa.setTipo(tipo);
    }

    public void updateData(String data){
        this.despesa.setData(data);
    }
}