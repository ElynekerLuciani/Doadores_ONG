package com.example.doacao_ong.ui.admin.despesas;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doacao_ong.R;
import com.example.doacao_ong.model.Despesa;

import java.util.ArrayList;

public class DespesasFragment extends Fragment {

    private DespesasViewModel mViewModel;

    public static DespesasFragment newInstance() {
        return new DespesasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.doacoes_realizadas_fragment, container, false);

        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(DespesasViewModel.class);
        mViewModel.recuperarDespesas();

        mViewModel.getArrayLiveData().observe(getActivity(), new Observer<ArrayList<Despesa>>() {
            @Override
            public void onChanged(ArrayList<Despesa> despesas) {
                configListView(despesas);
            }
        });
    }

    private void configListView(ArrayList<Despesa> despesas) {
        if (getView() != null) {
            RecyclerView recyclerView = getView().findViewById(R.id.recycler_lista_doacoes_realizadas);
            DespesasRVAdapter recyclerAdapter = new DespesasRVAdapter(getActivity(), despesas);
            RecyclerView.LayoutManager recyclerManager = new LinearLayoutManager(getActivity());

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(recyclerManager);

            recyclerAdapter.setClickListener(new DespesasRVAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getActivity(), "OK", Toast.LENGTH_LONG).show();
                }
            });
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

}