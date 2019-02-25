package br.com.brito.salvandoecarregandodados;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences( this );
       /* SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "nome", "Carlos Bruno" );
        editor.putInt( "idade", 33 );
        editor.putBoolean( "estado", false );
        editor.apply();*/
        /*
        String nome = sharedPreferences.getString( "nome", "" );
        int idade   = sharedPreferences.getInt( "idade", 0 );
        boolean estado = sharedPreferences.getBoolean( "estado", false );

        Log.i( "meuLog", ""+nome );
        Log.i( "meuLog", ""+idade);
        Log.i( "meuLog", ""+estado );*/


        try{
            Log.i("meuLog","dentro do try");
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase( "Meu Banco", MODE_PRIVATE, null );
            sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS cadastro( nome VARCHAR, idade INT(3) )" );
            sqLiteDatabase.execSQL("INSERT INTO cadastro VALUES('Carlos Bruno', 33)");
            sqLiteDatabase.execSQL("INSERT INTO cadastro VALUES('CHarles Hercules', 32)");
            sqLiteDatabase.execSQL("INSERT INTO cadastro VALUES('Carmem Michelly', 29)");
            sqLiteDatabase.execSQL("INSERT INTO cadastro VALUES('Wilson', 18)");

            Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM cadastro", null );
            int indiceNome  = cursor.getColumnIndex( "nome" );
            int indiceIdade = cursor.getColumnIndex( "idade" );

            cursor.moveToFirst();
            while ( cursor != null ){
                Log.i("meuLog", ""+cursor.getString(indiceNome) );
                Log.i("meuLog", ""+cursor.getString(indiceIdade) );
                cursor.moveToNext();
            }
        }catch ( Exception e ){
            e.printStackTrace();
        }


    }
}
