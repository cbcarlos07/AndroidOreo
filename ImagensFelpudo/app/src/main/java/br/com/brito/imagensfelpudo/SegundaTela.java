package br.com.brito.imagensfelpudo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SegundaTela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);
        Bundle extras = getIntent().getExtras();
        if( extras != null ){
            //String texto
        }
    }
}
