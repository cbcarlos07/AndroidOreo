package br.com.brito.showmilhao;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    StringBuilder meuJSONBuilder = new StringBuilder();
    String meuJSON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meuJSONBuilder.append("{");
        meuJSONBuilder.append(" \"nome\": \"Carlos Bruno\",");
        meuJSONBuilder.append(" \"sobrenome\": \"Brito\",");
        meuJSONBuilder.append(" \"idade\": 23,");
        meuJSONBuilder.append(" \"ativo\": true,");

        meuJSONBuilder.append("\"cursos\": [{");
        meuJSONBuilder.append("\"nome\": \"Android\",");
        meuJSONBuilder.append("\"aulas\": 25,");
        meuJSONBuilder.append("\"completo\": false");
        meuJSONBuilder.append("},");
        meuJSONBuilder.append("{\"nome\": \"iOS\",");
        meuJSONBuilder.append("\"aulas\": 50,");
        meuJSONBuilder.append("\"completo\": false");
        meuJSONBuilder.append("},");
        meuJSONBuilder.append("{\"nome\": \"3D Studio Max\",");
        meuJSONBuilder.append("\"aulas\": 25,");
        meuJSONBuilder.append("\"completo\": false");
        meuJSONBuilder.append("},");
        meuJSONBuilder.append("{\"nome\": \"Unity3D\",");
        meuJSONBuilder.append("\"aulas\": 12,");
        meuJSONBuilder.append("\"completo\": true");
        meuJSONBuilder.append("}");
        meuJSONBuilder.append("]");

        meuJSONBuilder.append("}");

        meuJSON = meuJSONBuilder.toString();
        meuJSON = "";
        //Log.i("meuLog", meuJSON);


       /* try {
            JSONObject listaJSON = new JSONObject(meuJSON);
            JSONArray listaCursos = listaJSON.getJSONArray("cursos");
            for (int i = 0; i < listaCursos.length(); i++){
                JSONObject curso = listaCursos.getJSONObject(i);
                Log.i("meuLog", curso.getString("nome"));
                Log.i("meuLog", ""+curso.getInt("aulas"));
                Log.i("meuLog", ""+curso.getString("completo"));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }*/
        new JSONTask().execute("http://www.json-generator.com/api/json/get/bZeGzhedbC?indent=2");
    }

    private class JSONTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);
                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            Log.i("meuLog",""+result);
            /*
        try {
            JSONObject listaJson = new JSONObject(result);
           // titulo.setText(listaJson.getString("titulo"));
            JSONArray questionario = listaJson.getJSONArray("questionario");

            for(int i=0; i<questionario.length(); i++){
                JSONObject questao = questionario.getJSONObject(i);

                String perg = questao.getString("Pergunta");
                String ra = questao.getString("respA");
                String rb = questao.getString("respB");
                String rc = questao.getString("respC");
                int correta = questao.getInt("correta");

               // Questao minhaQuestao = new Questao(perg,ra,rb,rc,correta);
               // questoes.add(minhaQuestao);

            }

            //atualizaView();

        }catch (JSONException e){e.printStackTrace();}
        */
        }
    }
}
