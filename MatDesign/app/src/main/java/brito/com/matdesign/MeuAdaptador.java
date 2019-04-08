package brito.com.matdesign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

public class MeuAdaptador extends RecyclerView.Adapter {

    ArrayList<Episodio> episodios;
    Context context;
    OnItemClickListener listener;

    public MeuAdaptador(Context context, ArrayList<Episodio> episodios, OnItemClickListener listener) {
        this.episodios = episodios;
        this.context   = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.minha_celula, viewGroup, false);
        MeuViewHolder meuViewHolder = new MeuViewHolder(view);
        return meuViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        MeuViewHolder meuViewHolder = (MeuViewHolder) viewHolder;
        Episodio meuEpisodio = episodios.get(i);
        meuViewHolder.titulo.setText( meuEpisodio.getTitulo() );
        meuViewHolder.data_exib.setText( meuEpisodio.getData_exib() );
        meuViewHolder.imageView.setImageResource( R.drawable.interrogacao );

        meuViewHolder.bind( episodios.get(i), listener );

    }

    @Override
    public int getItemCount() {
        return episodios.size();
    }

    public interface OnItemClickListener{
        void onItemClick(Episodio episodio);
    }
}
