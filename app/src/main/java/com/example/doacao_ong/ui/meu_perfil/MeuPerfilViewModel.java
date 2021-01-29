package com.example.doacao_ong.ui.meu_perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MeuPerfilViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MeuPerfilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is meu perfil fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}