package com.example.doacao_ong.ui.admin.doacoes_recebidas;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doacao_ong.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DoacoesRecebidasFragment extends Fragment {

    private DoacoesRecebidasViewModel mViewModel;

    public static DoacoesRecebidasFragment newInstance() {
        return new DoacoesRecebidasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.doacoes_recebidas_fragment, container, false);

        configListView(rootview);

        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DoacoesRecebidasViewModel.class);
        // TODO: Use the ViewModel
    }

    private void configListView(View view) {
        DoacoesRecebidasModel doacao1 = new DoacoesRecebidasModel("Wilker", "01/01/2021", "50");
        DoacoesRecebidasModel doacao2 = new DoacoesRecebidasModel("Bruno", "01/01/2021", "50");
        DoacoesRecebidasModel doacao3 = new DoacoesRecebidasModel("João", "01/01/2021", "50");

        ArrayList<DoacoesRecebidasModel> doacoes = new ArrayList<>();
        doacoes.add(doacao1);
        doacoes.add(doacao2);
        doacoes.add(doacao3);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_lista_doacoes);
        DoacoesRecebidasRVAdapter recyclerAdapter = new DoacoesRecebidasRVAdapter(getActivity(), doacoes);
        RecyclerView.LayoutManager recyclerManager = new LinearLayoutManager(getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(recyclerManager);

        recyclerAdapter.setClickListener(new DoacoesRecebidasRVAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "OK",Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
    }

}