package com.example.doacao_ong.model;

import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.config.UsuarioFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;

public class Ong implements Serializable {
    private String id;
    private String nome;
    private String missao;
    private String causa;
    private String descricao;
    private double latitude;
    private double longitude;

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
        ongMap.put("nome", getNome());
        ongMap.put("missao", getMissao());
        ongMap.put("causa", getCausa());
        ongMap.put("descricao", getDescricao());
        ongMap.put("latitude", getLatitude());
        ongMap.put("longitude", getLongitude());
        return ongMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
