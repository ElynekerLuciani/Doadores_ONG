package com.example.doacao_ong.ui.usuario.minhas_doacoes;

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
import com.example.doacao_ong.model.Doacao;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MinhasDoacoesAdapter extends ArrayAdapter<Doacao> {

//    private ArrayList<DoacaoModel> doacoes;
//    private LayoutInflater layoutInflater;
//    private DoacoesRecebidasRVAdapter.ItemClickListener itemClickListener;
//
//    public MinhasDoacoesAdapter(Context context, ArrayList<DoacaoModel> doacaoModels) {
//        this.layoutInflater = LayoutInflater.from(context);
//        this.doacoes = doacaoModels;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        DoacaoModel dm = doacoes.get(position);
//
//        holder.textViewOng.setText(dm.getOng());
//        holder.textViewData.setText(dm.getData());
//        holder.textViewValor.setText(dm.getValor());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView textViewOng, textViewData, textViewValor;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//
//            textViewOng = itemView.findViewById(R.id.textONG);
//            textViewData = itemView.findViewById(R.id.textData);
//            textViewValor = itemView.findViewById(R.id.textValor);
//
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (itemClickListener != null) itemClickListener.onItemClick(view, getAdapterPosition());
//        }
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(DoacoesRecebidasRVAdapter.ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }

    private final Context context;
    private final int auxResource;

    public MinhasDoacoesAdapter(@NonNls Context context, int resource, @NotNull ArrayList<Doacao> objects) {
        super(context, resource, objects);
        this.context = context;
        auxResource = resource;
    }

    @SuppressLint("ViewHolder")
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(auxResource, parent, false);

        TextView textOng = convertView.findViewById(R.id.textONG);
        TextView textValor = convertView.findViewById(R.id.textValor);
        TextView textData = convertView.findViewById(R.id.textData);

        textData.setText(getItem(position).getData());
        textOng.setText(getItem(position).getNomeRecebedor());

        Locale ptBr = new Locale("pt", "BR");
        double stringParsed = Double.parseDouble(getItem(position).getValor());
        String valorFormatted = NumberFormat.getCurrencyInstance(ptBr).format(stringParsed);
        textValor.setText(valorFormatted);

        return convertView;
    }

}
