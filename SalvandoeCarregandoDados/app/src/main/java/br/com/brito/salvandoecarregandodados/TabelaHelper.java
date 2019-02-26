package br.com.brito.salvandoecarregandodados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TabelaHelper extends SQLiteOpenHelper {
    public TabelaHelper(Context context) {
        super(context, TabelaConfig.NOME, null, TabelaConfig.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String minhaQuery = String.format( "CREATE TABLE IF NOT EXISTS %s (%s VARCHAR, %s INT(3) )",
                                            TabelaConfig.TABELA,
                                            TabelaConfig.Columns.NOME,
                                            TabelaConfig.Columns.IDADE);
        db.execSQL( minhaQuery );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TabelaConfig.TABELA );
    }
}
