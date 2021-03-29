package com.example.doacao_ong.ui.admin.meu_perfil;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doacao_ong.model.Ong;
import com.example.doacao_ong.ui.config.ConfiguracaoFirebase;
import com.example.doacao_ong.ui.config.UsuarioFirebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

public class MeuPerfilViewModel extends ViewModel {
    private MutableLiveData<Ong> ongLiveData;

    public MeuPerfilViewModel() {
        this.ongLiveData = new MutableLiveData<>();
    }

    public void getDadosOng() {
        String idUsuario = UsuarioFirebase.getIdentificadorUsuario();
        DatabaseReference database = ConfiguracaoFirebase.getFirebaseDatabase();

        database.child("ongs").child(idUsuario).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                ongLiveData.setValue(dataSnapshot.getValue(Ong.class));
            }
        });
    }

    public MutableLiveData<Ong> getOngLiveData() {
        return ongLiveData;
    }

    public void setOngLiveData(MutableLiveData<Ong> ongLiveData) {
        this.ongLiveData = ongLiveData;
    }
}
