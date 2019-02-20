package br.com.brito.cardsfelpudo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String[] listaNomes     = {"Felpudo","Fofura", "Lesmo", "Bugado", "Uruca", "Racing", "iOS", "Android", "Realidade Aumentada", "SoundFX", "3D Studio Max", "Games" };
    int[]    listaIcones    = {R.drawable.felpudo,R.drawable.felpudo,R.drawable.felpudo,R.drawable.felpudo,R.drawable.felpudo,R.drawable.felpudo,
                               R.drawable.felpudo,R.drawable.felpudo,R.drawable.felpudo,R.drawable.felpudo,R.drawable.felpudo,R.drawable.felpudo};
    String[] listaDescricao = {"Felpudo","Fofura", "Lesmo", "Bugado", "Uruca", "Racing", "iOS", "Android", "Realidade Aumentada", "SoundFX", "3D Studio Max", "Games" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*ArrayAdapter<String> meuAdaptador = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, listaNomes);
        ListView minhaLista = findViewById( R.id.minhaLista );
        minhaLista.setAdapter( meuAdaptador );

        minhaLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, ""+listaNomes[ position ], Toast.LENGTH_SHORT).show();
            }
        });*/
        ListView minhaLista = findViewById( R.id.minhaLista );
        MeuAdaptador meuAdaptador;
        meuAdaptador = new MeuAdaptador( getApplicationContext(), R.layout.minha_celula );
        int i = 0;
        for ( String nome: listaNomes ){
            DadosPersonagem dadosPersonagem;
            dadosPersonagem = new DadosPersonagem( listaIcones[i], nome, listaDescricao[i] );
            meuAdaptador.add( dadosPersonagem );
            i++;
        }

        minhaLista.setAdapter( meuAdaptador );

    }
}

class ViewPersonagem{
    ImageView icone;
    TextView  titulo;
    TextView  descricao;
}

class DadosPersonagem{
    private int icone;
    private String titulo;
    private String descricao;

    public DadosPersonagem(int icone, String titulo, String descricao) {
        this.icone = icone;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getIcone() {
        return icone;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}

class MeuAdaptador extends ArrayAdapter{
    public MeuAdaptador(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View minhaView;
        minhaView = convertView;
        ViewPersonagem viewPersonagem;
        if( convertView == null ){
            LayoutInflater inflater = ( LayoutInflater ) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            minhaView = inflater.inflate( R.layout.minha_celula, parent, false );

            viewPersonagem = new ViewPersonagem();
            viewPersonagem.icone      = ( ImageView ) minhaView.findViewById( R.id.meuIcone );
            viewPersonagem.titulo     = ( TextView )  minhaView.findViewById( R.id.meuTitulo );
            viewPersonagem.descricao  = ( TextView )  minhaView.findViewById( R.id.meuDescricao );

            minhaView.setTag( viewPersonagem );
        }else{
            viewPersonagem = ( ViewPersonagem ) minhaView.getTag();
        }

        DadosPersonagem dadosPersonagem;
        dadosPersonagem = ( DadosPersonagem ) this.getItem( position );
        viewPersonagem.icone.setImageResource( dadosPersonagem.getIcone() );
        viewPersonagem.titulo.setText( dadosPersonagem.getTitulo() );
        viewPersonagem.descricao.setText( dadosPersonagem.getDescricao() );

        return minhaView;
    }
}
