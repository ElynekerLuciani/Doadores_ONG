package com.example.doacao_ong.model;

import com.example.doacao_ong.ui.config.ConfiguracaoFirebase;
import com.example.doacao_ong.ui.config.UsuarioFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;

public class Ong {
    private String id;
    private String missao;
    private String causa;
    private String descricao;

    public Ong() {
    }

    public void atualizar() {
        String idUsuario = UsuarioFirebase.getIdentificadorUsuario();
        DatabaseReference firebaseDatabase = ConfiguracaoFirebase.getFirebaseDatabase();

        DatabaseReference ongsRef = firebaseDatabase
                .child("ongs")
                .child(idUsuario);

        HashMap<String, Object> valoresOng = converterParaMap();
        ongsRef.updateChildren(valoresOng);
    }


    @Exclude
    private HashMap<String, Object> converterParaMap() {
        HashMap<String, Object> ongMap = new HashMap<>();
        ongMap.put("missao", getMissao());
        ongMap.put("causa", getCausa());
        ongMap.put("descricao", getDescricao());
        return ongMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
