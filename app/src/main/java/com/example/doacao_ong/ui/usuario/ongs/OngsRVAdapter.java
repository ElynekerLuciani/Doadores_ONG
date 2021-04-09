package com.example.doacao_ong.ui.usuario.ongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doacao_ong.R;
import com.example.doacao_ong.model.Ong;
import com.example.doacao_ong.ui.usuario.doar.DoarFragment;

import java.util.ArrayList;

public class OngsRVAdapter extends RecyclerView.Adapter<OngsRVAdapter.ViewHolder> {
    private ArrayList<Ong> ongs;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    OngsRVAdapter(Context context, ArrayList<Ong> ongs) {
        this.ongs = ongs;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.ong_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ong ong = ongs.get(position);

        holder.labelNome.setText(ong.getNome());
        holder.labelCausa.setText(ong.getCausa());
        holder.labelDescricao.setText(ong.getDescricao());
    }

    @Override
    public int getItemCount() {
        return ongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView labelNome, labelCausa, labelDescricao, linkDoar, linkDetalhes;

        ViewHolder(View itemView) {
            super(itemView);

            labelNome = itemView.findViewById(R.id.ong_row_nome);
            labelCausa = itemView.findViewById(R.id.ong_row_causa);
            labelDescricao = itemView.findViewById(R.id.ong_row_descricao);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
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
