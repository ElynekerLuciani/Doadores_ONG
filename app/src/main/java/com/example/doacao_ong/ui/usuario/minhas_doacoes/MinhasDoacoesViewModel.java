package com.example.doacao_ong.ui.usuario.minhas_doacoes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.config.UsuarioFirebase;
import com.example.doacao_ong.model.Doacao;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MinhasDoacoesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Doacao>> doacoesLiveData;

    public MinhasDoacoesViewModel() {
        this.doacoesLiveData = new MutableLiveData<>();
        this.doacoesLiveData.setValue(new ArrayList<>());
    }

    public void recuperarMinhasDoacoes() {
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();

        database.child("doacoes").orderByChild("idDoador").equalTo(UsuarioFirebase.getUsuarioAtual().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                ArrayList<Doacao> doacoesArrayList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    doacoesArrayList.add(snapshot.getValue(Doacao.class));
                }
                getDoacoesLiveData().setValue(doacoesArrayList);
            }
        });
    }

    public MutableLiveData<ArrayList<Doacao>> getDoacoesLiveData() {
        return doacoesLiveData;
    }

    public void setDoacoesLiveData(MutableLiveData<ArrayList<Doacao>> doacoesLiveData) {
        this.doacoesLiveData = doacoesLiveData;
    }
}
