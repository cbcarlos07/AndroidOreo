package br.com.brito.salvandoecarregandodados;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    TabelaHelper meuHelper;
    SQLiteDatabase meuBanco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meuHelper = new TabelaHelper( this );
        meuBanco  = meuHelper.getWritableDatabase();
        adicionarItem();
        //lerBanco();
        apagaDado( "Michelly" );
    }

    private void adicionarItem(){
        ContentValues values = new ContentValues();
        values.clear();
        values.put( TabelaConfig.Columns.NOME, "Michelly" );
        values.put( TabelaConfig.Columns.IDADE, 28 );
        meuBanco.insertWithOnConflict( TabelaConfig.TABELA, null, values, SQLiteDatabase.CONFLICT_IGNORE );
    }

    private void lerBanco(){
        Cursor cursor = meuBanco.query( TabelaConfig.TABELA, new String[]{TabelaConfig.Columns.NOME, TabelaConfig.Columns.IDADE}, null,null, null, null, null );
        int colunaNome  = cursor.getColumnIndex( TabelaConfig.Columns.NOME );
        int colunaIdade = cursor.getColumnIndex( TabelaConfig.Columns.IDADE );
        if( cursor != null ){
            cursor.moveToFirst();
            while ( cursor != null ) {
                Log.i(  "meuLog",cursor.getString( colunaNome ) );
                Log.i(  "meuLog",cursor.getString( colunaIdade )+"\n\n" );
                cursor.moveToNext();
            }
        }
    }

    private void apagaDado( String dado ){
        String minhaQuery = String.format( "DELETE FROM %s WHERE %s = '%s'",
                                            TabelaConfig.TABELA,
                                            TabelaConfig.Columns.NOME,
                                            dado);
        meuBanco.execSQL( minhaQuery );
        lerBanco();
    }
    void sqliteCmd(){

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

    void sharedPreferencesFn() {
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
    }

}
