package br.com.brito.imagensfelpudo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] listaNomes     = {"Felpudo","Fofura", "Lesmo", "Bugado", "Uruca", "Racing", "iOS", "Android", "Realidade Aumentada", "SoundFX", "3D Studio Max", "Games" };
    int[]    listaIcones    = {R.drawable.felpudo,R.drawable.fofura,R.drawable.lesmo,R.drawable.bugado,R.drawable.uruca,R.drawable.carrinho,
            R.drawable.ios,R.drawable.android,R.drawable.realidade_aumentada,R.drawable.sound_fx,R.drawable.max,R.drawable.games};
    String[] listaDescricao = {"Felpudo","Fofura", "Lesmo", "Bugado", "Uruca", "Racing", "iOS", "Android", "Realidade Aumentada", "SoundFX", "3D Studio Max", "Games" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clicou( View view ){
        Toast.makeText(this, "Clicou!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent( MainActivity.this, SegundaTela.class );
        intent.putExtra("nome","Carlos Bruno");
        startActivity( intent );
    }


}
