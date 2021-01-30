package com.example.doacao_ong.ui.lancar_saida;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doacao_ong.R;
import com.example.doacao_ong.ui.meu_perfil.MeuPerfilViewModel;

public class LancarSaidaFragment extends Fragment {

    private LancarSaidaViewModel lancarSaidaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lancarSaidaViewModel =
                new ViewModelProvider(this).get(LancarSaidaViewModel.class);
        View root = inflater.inflate(R.layout.lancar_saida_fragment, container, false);
//        final TextView textView = root.findViewById(R.id.text_meu_perfil);
//        meuPerfilViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}