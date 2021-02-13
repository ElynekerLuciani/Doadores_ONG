package com.example.doacao_ong.ui.admin.meu_perfil;

public class MeuPerfilModel {
    private String nome;
    private String email;
    private String senha;
    private String confirmSenha;
    private String missaoONG;
    private String descricao;
    private String causa;

    public MeuPerfilModel() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmSenha() {
        return confirmSenha;
    }

    public void setConfirmSenha(String confirmSenha) {
        this.confirmSenha = confirmSenha;
    }

    public String getMissaoONG() {
        return missaoONG;
    }

    public void setMissaoONG(String missaoONG) {
        this.missaoONG = missaoONG;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

}
