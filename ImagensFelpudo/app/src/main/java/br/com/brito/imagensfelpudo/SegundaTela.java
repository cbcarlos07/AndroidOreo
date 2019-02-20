package br.com.brito.imagensfelpudo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaTela extends AppCompatActivity {
    TextView meuTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);
        Bundle extras = getIntent().getExtras();

        //meuTexto = findViewById( R.id.meuTexto );

        if( extras != null ){
            String texto = extras.getString( "nome" );
           // meuTexto.setText( texto );
        }
    }
}
