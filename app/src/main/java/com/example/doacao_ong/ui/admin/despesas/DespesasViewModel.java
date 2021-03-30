package com.example.doacao_ong.ui.admin.despesas;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doacao_ong.model.Despesa;
import com.example.doacao_ong.ui.config.ConfiguracaoFirebase;
import com.example.doacao_ong.ui.config.UsuarioFirebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class DespesasViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Despesa>> arrayLiveData;

    public DespesasViewModel(){
        this.arrayLiveData = new MutableLiveData<>();
        this.arrayLiveData.setValue(new ArrayList<>());
    }

    public void recuperarDespesas() {
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();

        database.child("ongs_despesas").child(UsuarioFirebase.getIdentificadorUsuario())
                .get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                ArrayList<Despesa> despesaArrayList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    despesaArrayList.add(snapshot.getValue(Despesa.class));
                }
                getArrayLiveData().setValue(despesaArrayList);
            }
        });
    }

    public MutableLiveData<ArrayList<Despesa>> getArrayLiveData() {
        return arrayLiveData;
    }

    public void setArrayLiveData(MutableLiveData<ArrayList<Despesa>> arrayLiveData) {
        this.arrayLiveData = arrayLiveData;
    }
}