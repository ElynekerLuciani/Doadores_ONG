package com.example.doacao_ong.ui.lancar_saida;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LancarSaidaViewModel extends ViewModel {
    private LancarSaidaModel lancarSaidaModel;

    public LancarSaidaViewModel() {
        this.lancarSaidaModel = new LancarSaidaModel();
    }

    public LancarSaidaModel getLancarSaidaModel() {
        return lancarSaidaModel;
    }

    public void updateValor(double valor){
        this.lancarSaidaModel.setValor(valor);
    }

    public void updateTipo(String tipo){
        this.lancarSaidaModel.setTipo(tipo);
    }

    public void updateData(String data){
        this.lancarSaidaModel.setData(data);
    }
}