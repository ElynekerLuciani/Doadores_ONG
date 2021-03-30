package com.example.doacao_ong.ui.admin.doacoes_recebidas;

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
import com.example.doacao_ong.model.Doacao;

import java.util.ArrayList;

public class DoacoesRecebidasFragment extends Fragment {

    private DoacoesRecebidasViewModel mViewModel;

    public static DoacoesRecebidasFragment newInstance() {
        return new DoacoesRecebidasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.doacoes_recebidas_fragment, container, false);

//        Doacao doacao = new Doacao();

//        doacao.setValor("20.00");
//        doacao.setData("30/03/2021");
//        doacao.setIdRecebedor(UsuarioFirebase.getIdentificadorUsuario());
//        doacao.setNomeRecebedor(UsuarioFirebase.getInstance().getNome());
//        doacao.setIdDoador("YyqL7raeXtMTthNWTuiurATVt5w1");
//        doacao.setNomeDoador("Doador");
//        doacao.adicionar();

        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DoacoesRecebidasViewModel.class);
        mViewModel.recuperarDespesas();

        mViewModel.getArrayLiveData().observe(getActivity(), new Observer<ArrayList<Doacao>>() {
            @Override
            public void onChanged(ArrayList<Doacao> despesas) {
                configListView(despesas);
            }
        });
    }

    private void configListView(ArrayList<Doacao> doacoes) {
        if (getView() != null) {
            RecyclerView recyclerView = getView().findViewById(R.id.recycler_lista_doacoes_recebidas);
            DoacoesRecebidasRVAdapter recyclerAdapter = new DoacoesRecebidasRVAdapter(getActivity(), doacoes);
            RecyclerView.LayoutManager recyclerManager = new LinearLayoutManager(getActivity());

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(recyclerManager);

            recyclerAdapter.setClickListener(new DoacoesRecebidasRVAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getActivity(), "OK", Toast.LENGTH_LONG).show();
                }
            });
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

}