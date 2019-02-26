package br.com.brito.listadetarefas;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTarefa = findViewById( R.id.addTarefa );

        addTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTarefa();
            }
        });
    }
    private void addTarefa(){
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        View alertView = getLayoutInflater().inflate( R.layout.alert_tarefa, null );
        EditText textoTarefa = alertView.findViewById( R.id.textoTarefa );
        EditText textoPrazo  = alertView.findViewById( R.id.textoPrazo );
        builder.setView( alertView );
        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
