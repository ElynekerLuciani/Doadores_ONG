package com.example.doacao_ong.ui.admin.doacoes_realizadas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.doacao_ong.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DoacoesRealizadasRVAdapter extends RecyclerView.Adapter<DoacoesRealizadasRVAdapter.ViewHolder> {
    private ArrayList<DoacoesRealizadasModel> doacoesRealizadas;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    DoacoesRealizadasRVAdapter(Context context, ArrayList<DoacoesRealizadasModel> doacoesRealizadas) {
        this.mInflater = LayoutInflater.from(context);
        this.doacoesRealizadas = doacoesRealizadas;
    }

    // inflates the row layout from xml when needed
    @NotNull
    @Override
    public DoacoesRealizadasRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.doacoes_realizadas_row, parent, false);
        return new DoacoesRealizadasRVAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(DoacoesRealizadasRVAdapter.ViewHolder holder, int position) {
        DoacoesRealizadasModel doacao = doacoesRealizadas.get(position);

        holder.textViewNome.setText(doacao.getNome());
        holder.textViewData.setText(doacao.getData());
        holder.textViewValor.setText(doacao.getValor());
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
