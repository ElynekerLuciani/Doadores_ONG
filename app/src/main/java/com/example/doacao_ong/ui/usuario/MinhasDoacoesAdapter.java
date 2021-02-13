package com.example.doacao_ong.ui.usuario;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doacao_ong.R;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MinhasDoacoesAdapter extends ArrayAdapter<Doacao> {
    private final Context context;
    private final int auxResource;

    public MinhasDoacoesAdapter(@NonNls Context context, int resource, @NotNull ArrayList<Doacao> objects) {
        super(context, resource, objects);
        this.context = context;
        auxResource = resource;
    }

    @SuppressLint("ViewHolder")
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id = getItem(position).getId();
        String ong = getItem(position).getOng();
        String valor = getItem(position).getValor();
        String data = getItem(position).getData();

        Doacao doacao = new Doacao(ong, valor, data);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(auxResource, parent, false);

        TextView textOng = convertView.findViewById(R.id.textONG);
        TextView textValor = convertView.findViewById(R.id.textValor);
        TextView textData = convertView.findViewById(R.id.textData);

        textData.setText(data);
        textOng.setText(ong);
        textValor.setText(valor);

        return convertView;
    }

}
