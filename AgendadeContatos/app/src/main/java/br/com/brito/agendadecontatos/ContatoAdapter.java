package br.com.brito.agendadecontatos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContactViewHolder>{
    private List<ContatoInfo> listaContatos;

    ContatoAdapter(List<ContatoInfo> lista){
        listaContatos = lista;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.celula_contato, viewGroup, false );
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder( ContactViewHolder contactViewHolder, int i) {
        ContatoInfo c = listaContatos.get( i );
        contactViewHolder.nome.setText( c.getNome() );
        contactViewHolder.ref.setText( c.getRef() );
        contactViewHolder.fone.setText( c.getFone() );
        File imgFile =  new File( c.getFoto() );
        if( imgFile.exists() ){
            Bitmap bitmap = BitmapFactory.decodeFile( imgFile.getAbsolutePath() );
            contactViewHolder.foto.setImageBitmap( bitmap );
        }
    }

    @Override
    public int getItemCount() {
        return listaContatos.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder{
        ImageView foto;
        TextView nome;
        TextView ref;
        TextView fone;
        ContactViewHolder(View v){
            super(v);
            foto = v.findViewById( R.id.imageFoto );
            nome = v.findViewById( R.id.textoNome );
            ref  = v.findViewById( R.id.textoRef );
            fone = v.findViewById( R.id.textoFone );
        }
    }
}
