package com.example.doacao_ong.ui.doaces_recebidas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doacao_ong.R;

public class DoacesRecebidasFragment extends Fragment {

    private DoacesRecebidasViewModel mViewModel;

    public static DoacesRecebidasFragment newInstance() {
        return new DoacesRecebidasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doaces_recebidas_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DoacesRecebidasViewModel.class);
        // TODO: Use the ViewModel
    }

}