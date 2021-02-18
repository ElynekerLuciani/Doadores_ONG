package com.example.doacao_ong.ui.admin.doacoes_recebidas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.doacao_ong.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class DoacoesRecebidasRVAdapter extends RecyclerView.Adapter<DoacoesRecebidasRVAdapter.ViewHolder> {

    private ArrayList<DoacoesRecebidasModel> doacoesRecebidas;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    DoacoesRecebidasRVAdapter(Context context, ArrayList<DoacoesRecebidasModel> doacoesRecebidas) {
        this.mInflater = LayoutInflater.from(context);
        this.doacoesRecebidas = doacoesRecebidas;
    }

    // inflates the row layout from xml when needed
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.doacoes_recebidas_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DoacoesRecebidasModel doacao = doacoesRecebidas.get(position);

        holder.textViewNome.setText(doacao.getNomeDoador());
        holder.textViewData.setText(doacao.getData());
        holder.textViewValor.setText(doacao.getValor());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return doacoesRecebidas.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewNome, textViewData, textViewValor;

        ViewHolder(View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.doacao_row_nome);
            textViewData = itemView.findViewById(R.id.doacao_row_data);
            textViewValor = itemView.findViewById(R.id.doacao_row_valor);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

