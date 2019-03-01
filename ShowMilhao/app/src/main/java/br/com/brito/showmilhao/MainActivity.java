package br.com.brito.showmilhao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    StringBuilder meuJson = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meuJson.append("{");
        meuJson.append(" \"nome\": \"Carlos Bruno\",");
        meuJson.append(" \"sobrenome\": \"Brito\",");
        meuJson.append(" \"idade\": 23,");
        meuJson.append(" \"ativo\": true,");

        meuJson.append("\"cursos\": [{");
        meuJson.append("\"nome\": \"Android\",");
        meuJson.append("\"aulas\": 25,");
        meuJson.append("\"completo\": false");
        meuJson.append("},");
        meuJson.append("{\"nome\": \"iOS\",");
        meuJson.append("\"aulas\": 50,");
        meuJson.append("\"completo\": false");
        meuJson.append("},");
        meuJson.append("{\"nome\": \"3D Studio Max\",");
        meuJson.append("\"aulas\": 25,");
        meuJson.append("\"completo\": false");
        meuJson.append("},");
        meuJson.append("{\"nome\": \"Unity3D\",");
        meuJson.append("\"aulas\": 12,");
        meuJson.append("\"completo\": true");
        meuJson.append("}");
        meuJson.append("]");

        meuJson.append("}");
        Log.i("meuLog", meuJson.toString());

        try {
            JSONObject listaJSON = new JSONObject(meuJson.toString());
            JSONArray listaCursos = listaJSON.getJSONArray("cursos");
            for (int i = 0; i < listaCursos.length(); i++){
                JSONObject curso = listaCursos.getJSONObject(i);
                Log.i("meuLog", curso.getString("nome"));
                Log.i("meuLog", ""+curso.getInt("aulas"));
                Log.i("meuLog", ""+curso.getString("completo"));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
