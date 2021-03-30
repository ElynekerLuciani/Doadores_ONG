package com.example.doacao_ong.model;

import com.example.doacao_ong.ui.config.ConfiguracaoFirebase;
import com.example.doacao_ong.ui.config.UsuarioFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;

public class Despesa {
    private String data;
    private String tipo;
    private String valor;
    private String descrição;

    public Despesa() {
    }

    public void adicionar() {
        String idUsuario = UsuarioFirebase.getIdentificadorUsuario();
        DatabaseReference firebaseDatabase = ConfiguracaoFirebase.getFirebaseDatabase();

        DatabaseReference despesasRef = firebaseDatabase
                .child("ongs_despesas")
                .child(idUsuario);

        HashMap<String, Object> valoresDespesa = converterParaMap();
        despesasRef.push().setValue(valoresDespesa);
    }


    @Exclude
    private HashMap<String, Object> converterParaMap() {
        HashMap<String, Object> despesaMap = new HashMap<>();
        despesaMap.put("data", getData());
        despesaMap.put("tipo", getTipo());
        despesaMap.put("valor", getValor());
        despesaMap.put("descricao", getDescrição());
        return despesaMap;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
