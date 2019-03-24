package brito.com.matdesign;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MeuViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView titulo;
    TextView data_exib;
    public MeuViewHolder (View itemView){
        super(itemView);
        imageView = itemView.findViewById(R.id.imagem_celula);
        titulo = itemView.findViewById(R.id.titulo_celula);
        data_exib = itemView.findViewById(R.id.data_exib);
    }
}
