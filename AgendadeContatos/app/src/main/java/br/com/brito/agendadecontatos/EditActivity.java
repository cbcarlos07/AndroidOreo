package br.com.brito.agendadecontatos;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    private  ContatoInfo contato;

    private View layout;

    private ImageButton foto;
    private EditText nome;
    private EditText ref;
    private EditText email;
    private EditText fone;
    private EditText end;
    private Button salvar;

    private final int CAMERA = 1;
    private final int GALERIA = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        contato = getIntent().getParcelableExtra("contato");

        layout = findViewById( R.id.mainLayout );

        foto  = findViewById( R.id.fotoContato );
        nome  = findViewById( R.id.nomeContato );
        ref   = findViewById( R.id.refContato );
        email = findViewById( R.id.emailContato );
        fone  = findViewById( R.id.foneContato );
        end   = findViewById( R.id.endContato );

        nome.setText(contato.getNome() );
        ref.setText(contato.getRef() );
        email.setText(contato.getEmail() );
        fone.setText(contato.getFone() );
        end.setText(contato.getEnd() );

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertaImagem();
            }
        });

        salvar = findViewById( R.id.btnSalvar );
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contato.setNome( nome.getText().toString() );
                contato.setRef( ref.getText().toString() );
                contato.setEmail( email.getText().toString() );
                contato.setFone( fone.getText().toString() );
                contato.setEnd( end.getText().toString() );

                if(contato.getNome().equals("") ){
                    Toast.makeText(EditActivity.this, "É necessário um nome para salvar", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent i = new Intent(  );
                i.putExtra( "contato", contato );
                setResult( RESULT_OK, i );
                finish();
            }
        });
    }
    private void alertaImagem(){
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Selecione a fonte da imagem" );
        builder.setPositiveButton("Câmera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clicaTirarFoto();
            }
        });
        builder.setNegativeButton("Galeria", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clicaCarregaImagem();
            }
        });
        builder.create().show();
    }
    private void clicaTirarFoto(){
        if( ActivityCompat.checkSelfPermission( this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ){
            requestCameraPermission();
        }else{
            showCamera();
        }
    }

    private void requestCameraPermission(){
        if( ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.CAMERA ) ){
            Snackbar.make( layout, "É necessário permitir para utilizar a câmera", Snackbar.LENGTH_INDEFINITE ).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityCompat.requestPermissions( EditActivity.this, new String[]{ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, CAMERA );
                }
            });
        }else{
            ActivityCompat.requestPermissions( this, new String[]{ Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, CAMERA );
        }
    }

    private void showCamera(){
        Intent i = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
        startActivityForResult( i, CAMERA );
    }

    private void clicaCarregaImagem(){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA:
                    if( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                        clicaTirarFoto();
                    }
                break;
        }
    }
}
