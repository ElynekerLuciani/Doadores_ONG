package com.example.doacao_ong.ui.admin.dashboard;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.config.UsuarioFirebase;
import com.example.doacao_ong.model.Despesa;
import com.example.doacao_ong.model.Doacao;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<Double> saldo;
    private MutableLiveData<Double> totalDoacoes;
    private MutableLiveData<Double> totalDespesas;

    public DashboardViewModel() {
        this.saldo = new MutableLiveData<>();
        this.saldo.setValue(0.0);
        this.totalDoacoes = new MutableLiveData<>();
        this.totalDoacoes.setValue(0.0);
        this.totalDespesas = new MutableLiveData<>();
        this.totalDespesas.setValue(0.0);
    }

    public void somarDoacoes() {
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();

        database.child("doacoes").child(UsuarioFirebase.getIdentificadorUsuario())
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                double total = 0.0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Doacao doacao = snapshot.getValue(Doacao.class);

                    assert doacao != null;
                    total += Double.parseDouble(doacao.getValor());
                }
                getSaldo().setValue(getSaldo().getValue() + total);
                getTotalDoacoes().setValue(total);
            }
        });
    }

    public void somarDespesas() {
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();

        database.child("ongs_despesas").child(UsuarioFirebase.getIdentificadorUsuario())
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                double total = 0.0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Despesa despesa = snapshot.getValue(Despesa.class);

                    assert despesa != null;
                    total += Double.parseDouble(despesa.getValor());
                }
                getSaldo().setValue(getSaldo().getValue() - total);
                getTotalDespesas().setValue(total);
            }
        });
    }

    public MutableLiveData<Double> getSaldo() {
        return saldo;
    }

    public void setSaldo(MutableLiveData<Double> saldo) {
        this.saldo = saldo;
    }

    public MutableLiveData<Double> getTotalDoacoes() {
        return totalDoacoes;
    }

    public void setTotalDoacoes(MutableLiveData<Double> totalDoacoes) {
        this.totalDoacoes = totalDoacoes;
    }

    public MutableLiveData<Double> getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(MutableLiveData<Double> totalDespesas) {
        this.totalDespesas = totalDespesas;
    }
}