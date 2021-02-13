package com.example.doacao_ong.ui.meu_perfil;

import androidx.lifecycle.ViewModel;

public class MeuPerfilViewModel extends ViewModel {
   private MeuPerfilModel meuPerfilModel;

    public MeuPerfilViewModel() {
        this.meuPerfilModel = new MeuPerfilModel();
    }

    public MeuPerfilModel getMeuPerfilModel() {
        return meuPerfilModel;
    }

    public void updateNome(String nome){
        this.meuPerfilModel.setNome(nome);
    }

    public void updateEmail(String email){
        this.meuPerfilModel.setEmail(email);
    }

    public void updateSenha(String senha){
        this.meuPerfilModel.setSenha(senha);
    }

    public void updateConfirmSenha(String confirmSenha){
        this.meuPerfilModel.setConfirmSenha(confirmSenha);
    }

    public void updateMissaoONG(String missaoONG){
        this.meuPerfilModel.setMissaoONG(missaoONG);
    }

    public void updateDescricao(String descricao){
        this.meuPerfilModel.setDescricao(descricao);
    }

    public void updateCausa(String causa){
        this.meuPerfilModel.setCausa(causa);
    }
}