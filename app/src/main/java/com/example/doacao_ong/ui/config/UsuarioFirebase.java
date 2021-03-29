package com.example.doacao_ong.ui.config;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doacao_ong.data.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

public class UsuarioFirebase {

    private static Usuario usuario;

    public static Usuario getInstance(){
        if(usuario == null) {
            usuario = new Usuario();
        }
        return  usuario;
    }

    public static String getIdentificadorUsuario() {
        FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
//        String email = Objects.requireNonNull(auth.getCurrentUser()).getEmail();
//        assert email != null;

//        return Base64Custom.codificarBase64(email);
        return auth.getUid();
    }

    public static FirebaseUser getUsuarioAtual() {
        FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        return auth.getCurrentUser();
    }

    public static boolean atualizarFotoUsuario(Uri url) {
        try {
            FirebaseUser user = getUsuarioAtual();
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(url)
                    .build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.d("Perfil", "Erro ao atualizar foto de usuário");
                    }
                }
            });
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean atualizarNomeUsuario(String nome) {
        try {
            FirebaseUser user = getUsuarioAtual();
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(nome)
                    .build();
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.d("Perfil", "Erro ao atualizar nome de usuário");
                    }
                }
            });
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void getFirebaseDadosUsuarioLogado() {
        FirebaseUser firebaseUser = getUsuarioAtual();

        getInstance().setEmail(firebaseUser.getEmail());
        getInstance().setNome(firebaseUser.getDisplayName());

        if (firebaseUser.getPhotoUrl() == null) {
            getInstance().setFoto("");
        } else {
            getInstance().setFoto(firebaseUser.getPhotoUrl().toString());
        }
    }
}
