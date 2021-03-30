package com.example.doacao_ong.ui.admin.despesas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.doacao_ong.R;
import com.example.doacao_ong.model.Despesa;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DespesasRVAdapter extends RecyclerView.Adapter<DespesasRVAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<Despesa> doacoesRealizadas;

    DespesasRVAdapter(Context context, ArrayList<Despesa> doacoesRealizadas) {
        this.mInflater = LayoutInflater.from(context);
        this.doacoesRealizadas = doacoesRealizadas;
    }

    // inflates the row layout from xml when needed
    @NotNull
    @Override
    public DespesasRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.doacoes_realizadas_row, parent, false);
        return new DespesasRVAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(DespesasRVAdapter.ViewHolder holder, int position) {
        Despesa doacao = doacoesRealizadas.get(position);

        holder.textViewData.setText(doacao.getData());
        holder.textViewValor.setText("RS " + doacao.getValor());
        holder.textViewNome.setText(doacao.getDescricao());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return doacoesRealizadas.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewNome, textViewData, textViewValor;

        ViewHolder(View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.doacao_realizada_row_nome);
            textViewData = itemView.findViewById(R.id.doacao_realizada_row_data);
            textViewValor = itemView.findViewById(R.id.doacao_realizada_row_valor);

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
