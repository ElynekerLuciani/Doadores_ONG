package com.example.doacao_ong.ui.usuario.ongs;

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

import com.example.doacao_ong.R;
import com.example.doacao_ong.model.Ong;

import java.util.ArrayList;

public class OngsFragment extends Fragment {

    private OngsViewModel mViewModel;

    public static OngsFragment newInstance() {
        return new OngsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ongs_fragment, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OngsViewModel.class);
        mViewModel.recuperarOngs();

        mViewModel.getOngsLiveData().observe(getActivity(), new Observer<ArrayList<Ong>>() {
            @Override
            public void onChanged(ArrayList<Ong> ongs) {
                configRecyclerView(ongs);
            }
        });
    }

    private void configRecyclerView(ArrayList<Ong> ongs) {
        if (getView() != null) {
            RecyclerView recyclerView = getView().findViewById(R.id.ongs_recycler_view);
            OngsRVAdapter recyclerAdapter = new OngsRVAdapter(getActivity(), ongs);
            RecyclerView.LayoutManager recyclerManager = new LinearLayoutManager(getActivity());

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(recyclerManager);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }
}