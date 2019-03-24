package brito.com.matdesign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MeuAdaptador extends RecyclerView.Adapter {

    ArrayList<Episodio> episodios;
    Context context;

    public MeuAdaptador(Context context, ArrayList<Episodio> episodios) {
        this.episodios = episodios;
        this.context   = context;
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
