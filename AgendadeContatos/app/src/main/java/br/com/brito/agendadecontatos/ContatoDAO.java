package br.com.brito.agendadecontatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO extends SQLiteOpenHelper {
    private static final int VERSAO = 1;
    private final String TABELA = "Contatos";
    private static final String DATABASE = "DadosAgenda";
    public ContatoDAO( Context context ) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append( "CREATE TABLE "+ TABELA +"( ");
        builder.append( " id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        builder.append( " nome TEXT NOT NULL, ");
        builder.append( " ref TEXT, ");
        builder.append( " email TEXT, ");
        builder.append( " end TEXT, ");
        builder.append( " foto TEXT); ");
        String sql = builder.toString();
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<ContatoInfo> getList( String order ){
        List<ContatoInfo> contatos = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery( "SELECT * FROM " + TABELA + " ORDER BY nome "+ order + ";", null );
        while ( cursor.moveToNext() ){
            ContatoInfo c = new ContatoInfo();
            c.setId( cursor.getLong(cursor.getColumnIndex( "id" )) );
            c.setNome( cursor.getString(cursor.getColumnIndex( "nome" )) );
            c.setRef( cursor.getString(cursor.getColumnIndex( "ref" )) );
            c.setEmail( cursor.getString(cursor.getColumnIndex( "email" )) );
            c.setEnd( cursor.getString(cursor.getColumnIndex( "end" )) );
            c.setFoto( cursor.getString(cursor.getColumnIndex( "foto" )) );
            contatos.add( c );
        }
        cursor.close();
        return contatos;

    }

    public void inserirContato( ContatoInfo c ){
        ContentValues values = new ContentValues();
        values.put( "nome", c.getNome() );
        values.put( "ref", c.getRef() );
        values.put( "email", c.getEmail() );
        values.put( "end", c.getEnd() );
        values.put( "foto", c.getFoto() );
        getWritableDatabase().insert( TABELA, null, values );
    }

    public void alteraContato( ContatoInfo c ){
        ContentValues values = new ContentValues();
        values.put("id", c.getId());
        values.put("nome", c.getNome());
        values.put("ref", c.getRef());
        values.put("email", c.getEmail());
        values.put("end", c.getEnd());
        values.put("foto", c.getFoto());
        String[] idParaAlterado = { c.getId().toString() };
        getWritableDatabase().update( TABELA, values, "id=?", idParaAlterado );
    }

    public void apagarContato( ContatoInfo c){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {c.getId().toString()};
        db.delete(TABELA, "id=?", args);
    }
}
