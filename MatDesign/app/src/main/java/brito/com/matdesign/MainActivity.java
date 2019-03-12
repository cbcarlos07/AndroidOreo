package brito.com.matdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById( R.id.minhaToolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
