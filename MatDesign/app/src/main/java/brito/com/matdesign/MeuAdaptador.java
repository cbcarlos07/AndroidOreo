package brito.com.matdesign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
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
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
