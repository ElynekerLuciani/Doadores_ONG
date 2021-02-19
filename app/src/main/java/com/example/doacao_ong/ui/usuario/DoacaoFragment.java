package com.example.doacao_ong.ui.usuario;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doacao_ong.R;
import com.example.doacao_ong.ui.admin.doacoes_recebidas.DoacoesRecebidasRVAdapter;

import java.util.ArrayList;
import java.util.List;


public class DoacaoFragment extends Fragment {

    ArrayList<DoacaoModel> listaDoacao;
    ListView listViewDoacao;
    MinhasDoacoesAdapter adapter;
    EditText editTextCodigo;

    public DoacaoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.adapter_view_layout, container, false);
        View rootView = inflater.inflate(R.layout.fragment_doacao, container, false);

        DoacaoModel doacao1 = new DoacaoModel("19/01/2021", "Sentinela", "100.00");
        DoacaoModel doacao2 = new DoacaoModel("18/01/2021", "Popular", "50.00");
        DoacaoModel doacao3 = new DoacaoModel("17/01/2021", "Farias", "120.00");

        listaDoacao = new ArrayList<>();
        listaDoacao.add(doacao1);
        listaDoacao.add(doacao2);
        listaDoacao.add(doacao3);

        // Aqui você instancia sua ListView
        ListView carsList = (ListView) rootView.findViewById(R.id.listView);
        ArrayList<DoacaoModel> cars = listaDoacao;

        MinhasDoacoesAdapter carsAdapter = new MinhasDoacoesAdapter(this.getActivity(), R.layout.adapter_view_layout, cars);
        carsList.setAdapter(carsAdapter);


//        configListView(rootView);

        return rootView;
    }

//    @SuppressLint("CutPasteId")
//    private void configListView(View rootView) {
//        DoacaoModel doacao1 = new DoacaoModel("19/01/2021", "Sentinela", "100.00");
//        DoacaoModel doacao2 = new DoacaoModel("18/01/2021", "Popular", "50.00");
//        DoacaoModel doacao3 = new DoacaoModel("17/01/2021", "Farias", "120.00");
//
//        listaDoacao = new ArrayList<>();
//        listaDoacao.add(doacao1);
//        listaDoacao.add(doacao2);
//        listaDoacao.add(doacao3);
//
//        RecyclerView recyclerView = rootView.findViewById(R.id.listView);
//        adapter = new MinhasDoacoesAdapter(getActivity(), listaDoacao);
//        RecyclerView.LayoutManager recLayoutManager = new LinearLayoutManager(getActivity());
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(recLayoutManager);
//
//        adapter.setClickListener(new DoacoesRecebidasRVAdapter.ItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(getActivity(), "OK",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        recyclerView.setAdapter(adapter);
//
//
//
//
//
//    }
}