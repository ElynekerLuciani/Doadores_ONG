package com.example.doacao_ong.model;

import com.example.doacao_ong.ui.config.ConfiguracaoFirebase;
import com.example.doacao_ong.ui.config.UsuarioFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;

public class Doacao {
    private String idDoador;
    private String nomeDoador;
    private String idRecebedor;
    private String nomeRecebedor;
    private String data;
    private String valor;

    public Doacao() {
    }

    public void adicionar() {
        String idUsuario = UsuarioFirebase.getIdentificadorUsuario();
        DatabaseReference firebaseDatabase = ConfiguracaoFirebase.getFirebaseDatabase();

        DatabaseReference doacoesRef = firebaseDatabase
                .child("doacoes")
                .child(idUsuario);

        HashMap<String, Object> valoresDoacao = converterParaMap();
        doacoesRef.push().setValue(valoresDoacao);
    }

    @Exclude
    private HashMap<String, Object> converterParaMap() {
        HashMap<String, Object> despesaMap = new HashMap<>();
        despesaMap.put("idDoador", getIdDoador());
        despesaMap.put("nomeDoador", getNomeDoador());
        despesaMap.put("idRecebedor", getIdRecebedor());
        despesaMap.put("nomeRecebedor", getNomeRecebedor());
        despesaMap.put("data", getData());
        despesaMap.put("valor", getValor());
        return despesaMap;
    }

    public String getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(String idDoador) {
        this.idDoador = idDoador;
    }

    public String getIdRecebedor() {
        return idRecebedor;
    }

    public void setIdRecebedor(String idRecebedor) {
        this.idRecebedor = idRecebedor;
    }

    public String getNomeRecebedor() {
        return nomeRecebedor;
    }

    public void setNomeRecebedor(String nomeRecebedor) {
        this.nomeRecebedor = nomeRecebedor;
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
