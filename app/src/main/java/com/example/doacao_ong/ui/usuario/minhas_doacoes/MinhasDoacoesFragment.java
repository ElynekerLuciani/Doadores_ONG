package com.example.doacao_ong.ui.usuario.minhas_doacoes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.doacao_ong.R;
import com.example.doacao_ong.model.Doacao;

import java.util.ArrayList;


public class MinhasDoacoesFragment extends Fragment {

    private MinhasDoacoesViewModel viewModel;

    ListView listViewMinhasDoacoes;

    public MinhasDoacoesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_doacao, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(MinhasDoacoesViewModel.class);
        viewModel.recuperarMinhasDoacoes();

        viewModel.getDoacoesLiveData().observe(getActivity(), new Observer<ArrayList<Doacao>>() {
            @Override
            public void onChanged(ArrayList<Doacao> doacoes) {
                configListView(doacoes);
            }
        });
    }

    private void configListView(ArrayList<Doacao> doacoes) {
        if (getView() != null) {
            listViewMinhasDoacoes = (ListView) getView().findViewById(R.id.listView);

            MinhasDoacoesAdapter carsAdapter = new MinhasDoacoesAdapter(this.getActivity(), R.layout.adapter_view_layout, doacoes);
            listViewMinhasDoacoes.setAdapter(carsAdapter);
        }
    }
}