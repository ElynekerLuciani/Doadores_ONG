package com.example.doacao_ong.ui.admin.doacoes_realizadas;

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
import com.example.doacao_ong.ui.admin.doacoes_recebidas.DoacoesRecebidasRVAdapter;

import java.util.ArrayList;

public class DoacoesRealizadasFragment extends Fragment {

    private DoacoesRealizadasViewModel mViewModel;

    public static DoacoesRealizadasFragment newInstance() {
        return new DoacoesRealizadasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.doacoes_realizadas_fragment, container, false);

        configListView(rootview);

        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DoacoesRealizadasViewModel.class);
        // TODO: Use the ViewModel
    }

    private void configListView(View view) {
        DoacoesRealizadasModel doacao1 = new DoacoesRealizadasModel("José", "01/01/2021", "50");
        DoacoesRealizadasModel doacao2 = new DoacoesRealizadasModel("Maria", "01/01/2021", "50");
        DoacoesRealizadasModel doacao3 = new DoacoesRealizadasModel("Joaquim", "01/01/2021", "50");

        ArrayList<DoacoesRealizadasModel> doacoes = new ArrayList<>();
        doacoes.add(doacao1);
        doacoes.add(doacao2);
        doacoes.add(doacao3);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_lista_doacoes_realizadas);
        DoacoesRealizadasRVAdapter recyclerAdapter = new DoacoesRealizadasRVAdapter(getActivity(), doacoes);
        RecyclerView.LayoutManager recyclerManager = new LinearLayoutManager(getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(recyclerManager);

        recyclerAdapter.setClickListener(new DoacoesRealizadasRVAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "OK",Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
    }

}