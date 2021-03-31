package com.example.doacao_ong.ui.usuario.ongs;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doacao_ong.config.ConfiguracaoFirebase;
import com.example.doacao_ong.config.UsuarioFirebase;
import com.example.doacao_ong.model.Doacao;
import com.example.doacao_ong.model.Ong;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class OngsViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Ong>> ongsLiveData;

    public OngsViewModel() {
        this.ongsLiveData = new MutableLiveData<>();
        this.ongsLiveData.setValue(new ArrayList<>());
    }

    public void recuperarOngs() {
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();

        database.child("ongs").get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                ArrayList<Ong> ongsArrayList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ongsArrayList.add(snapshot.getValue(Ong.class));
                }
                getOngsLiveData().setValue(ongsArrayList);
            }
        });
    }

    public MutableLiveData<ArrayList<Ong>> getOngsLiveData() {
        return ongsLiveData;
    }

    public void setOngsLiveData(MutableLiveData<ArrayList<Ong>> ongsLiveData) {
        this.ongsLiveData = ongsLiveData;
    }
}