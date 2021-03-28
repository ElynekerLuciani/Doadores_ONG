package com.example.doacao_ong.ui.usuario;

public class DoacaoModel {

    private String data;
    private String ong;
    private String valor;

    public DoacaoModel(String data, String ong, String valor) {
        this.data = data;
        this.ong = ong;
        this.valor = valor;
    }

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public String getOng() { return ong; }

    public void setOng(String ong) { this.ong = ong; }

    public String getValor() { return valor; }

    public void setValor(String valor) { this.valor = valor; }
}