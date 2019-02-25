package br.com.brito.imagensfelpudo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SegundaTela extends AppCompatActivity {
    TextView meuDescritivo;
    TextView meuNome;
    ImageView meuIcone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);
        Bundle extras = getIntent().getExtras();

        meuNome       = findViewById( R.id.meuNome );
        meuDescritivo = findViewById( R.id.meuDescritivo );
        meuIcone      = findViewById( R.id.meuIcone );

        if( extras != null ){
            String titulo    = extras.getString( "titulo" );
            String descricao = extras.getString( "descricao" );
            int    icone     = extras.getInt( "icone" );
            meuNome.setText( titulo );
            meuDescritivo.setText( descricao );
            meuIcone.setImageResource( icone );
        }
    }
}
