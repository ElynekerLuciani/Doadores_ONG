package com.example.doacao_ong.ui.meu_perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doacao_ong.R;
import com.example.doacao_ong.ui.gallery.GalleryViewModel;

public class MeuPerfilFragment extends Fragment {

    private MeuPerfilViewModel meuPerfilViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        meuPerfilViewModel =
                new ViewModelProvider(this).get(MeuPerfilViewModel.class);
        View root = inflater.inflate(R.layout.meu_perfil_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_meu_perfil);
        meuPerfilViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}