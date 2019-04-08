package brito.com.matdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Episodio> episodios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById( R.id.minharecview );
        toolbar = findViewById( R.id.minhaToolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //recyclerView.setAda
        //Episodio episodio = new Episodio("Carlos Bruno","asdf","HDHD7837","12 de Janeiro de 2017");
        episodios = new ArrayList<Episodio>();
        episodios.add( new Episodio("Carlos Bruno","asdf","HDHD7837","12 de Janeiro de 2017") );
        episodios.add( new Episodio("Charles","asdf","HDHD7837","12 de Janeiro de 2017") );
        episodios.add( new Episodio("Bruno","asdf","HDHD7837","12 de Janeiro de 2017") );
        episodios.add( new Episodio("Brito","asdf","HDHD7837","12 de Janeiro de 2017") );
        episodios.add( new Episodio("Gonçalves","asdf","HDHD7837","12 de Janeiro de 2017") );
        episodios.add( new Episodio("CB","asdf","HDHD7837","12 de Janeiro de 2017") );

        MeuAdaptador meuAdaptador = new MeuAdaptador(getApplicationContext(), episodios, new MeuAdaptador.OnItemClickListener() {
            @Override
            public void onItemClick(Episodio episodio) {
                Toast.makeText( MainActivity.this, ""+episodio.getTitulo(), Toast.LENGTH_LONG ).show();
            }
        });
        recyclerView.setAdapter( meuAdaptador );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager( layoutManager );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.meu_menu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_contato:
                Toast.makeText(this, "Clicou contato", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_favorito:
                Toast.makeText(this, "Clicou favorito", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_home:
                Toast.makeText(this, "Clicou home", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Toast.makeText(this, "Clicou Backbutton", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
