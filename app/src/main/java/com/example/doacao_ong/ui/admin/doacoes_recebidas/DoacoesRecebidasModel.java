package com.example.doacao_ong.ui.admin.doacoes_recebidas;

public class DoacoesRecebidasModel {
    private String nomeDoador;
    private String data;
    private String valor;

    public DoacoesRecebidasModel(){}

    public DoacoesRecebidasModel(String nomeDoador, String data, String valor){
        this.nomeDoador = nomeDoador;
        this.data = data;
        this.valor = valor;
    }

    public String getNomeDoador() {
        return nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
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
