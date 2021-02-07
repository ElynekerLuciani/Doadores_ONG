package com.example.doacao_ong.ui.doacoes_realizadas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doacao_ong.R;

public class DaocoesRealizadasFragment extends Fragment {

    private DaocoesRealizadasViewModel mViewModel;

    public static DaocoesRealizadasFragment newInstance() {
        return new DaocoesRealizadasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daocoes_realizadas_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DaocoesRealizadasViewModel.class);
        // TODO: Use the ViewModel
    }

}