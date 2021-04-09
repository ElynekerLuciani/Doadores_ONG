package com.example.doacao_ong.ui.admin.dashboard;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doacao_ong.R;

import java.text.NumberFormat;
import java.util.Locale;

public class DashboardFragment extends Fragment {

    private DashboardViewModel mViewModel;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dashboard_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        mViewModel.somarDoacoes();
        mViewModel.somarDespesas();

        mViewModel.getSaldo().observe(getActivity(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                if(getView() != null){
                    TextView saldo = getView().findViewById(R.id.dashboard_valor_saldo);

                    Locale ptBr = new Locale("pt", "BR");
                    String totalFormatted = NumberFormat.getCurrencyInstance(ptBr).format(aDouble);
                    saldo.setText(totalFormatted);
                }
            }
        });

        mViewModel.getTotalDoacoes().observe(getActivity(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                if(getView() != null){
                    TextView doacoes = getView().findViewById(R.id.dashboard_total_arrecadado);

                    Locale ptBr = new Locale("pt", "BR");
                    String totalFormatted = NumberFormat.getCurrencyInstance(ptBr).format(aDouble);
                    doacoes.setText(totalFormatted);
                }
            }
        });

        mViewModel.getTotalDespesas().observe(getActivity(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                if(getView() != null){
                    TextView despesas = getView().findViewById(R.id.dashboard_total_despesas);

                    Locale ptBr = new Locale("pt", "BR");
                    String totalFormatted = NumberFormat.getCurrencyInstance(ptBr).format(aDouble);
                    despesas.setText(totalFormatted);
                }
            }
        });
    }

}