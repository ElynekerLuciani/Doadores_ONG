package com.example.doacao_ong.ui.lancar_saida;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LancarSaidaViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public LancarSaidaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is lançar saída fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}