package com.example.doacao_ong.ui.admin.doacoes_realizadas;

public class DoacoesRealizadasModel {
    private String nome;
    private String data;
    private String valor;

    public DoacoesRealizadasModel(){}

    public DoacoesRealizadasModel(String nome, String data, String valor){
        this.nome = nome;
        this.data = data;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
