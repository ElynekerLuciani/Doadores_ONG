package com.example.doacao_ong.ui.admin.doacoes_recebidas;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doacao_ong.model.Doacao;
import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.config.UsuarioFirebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class DoacoesRecebidasViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Doacao>> arrayLiveData;

    public DoacoesRecebidasViewModel() {
        this.arrayLiveData = new MutableLiveData<>();
        this.arrayLiveData.setValue(new ArrayList<>());
    }

    public void recuperarDoacoes() {
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();

        database.child("doacoes").orderByChild("idRecebedor").equalTo(UsuarioFirebase.getIdentificadorUsuario())
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                ArrayList<Doacao> doacoesArrayList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    doacoesArrayList.add(snapshot.getValue(Doacao.class));
                }
                getArrayLiveData().setValue(doacoesArrayList);
            }
        });
    }

    public MutableLiveData<ArrayList<Doacao>> getArrayLiveData() {
        return arrayLiveData;
    }

    public void setArrayLiveData(MutableLiveData<ArrayList<Doacao>> arrayLiveData) {
        this.arrayLiveData = arrayLiveData;
    }
}