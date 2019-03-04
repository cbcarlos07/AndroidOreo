package br.com.brito.showmilhao;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    StringBuilder meuJSONBuilder = new StringBuilder();
    String meuJSON;
    ProgressBar progressBar;

    RadioGroup  radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    ArrayList<Questao> questoes;
    TextView tvPergunta;
    TextView tvTitulo;
    Button   botaoCofnirma;
    int rodada = 0;
    int acertos = 0;
    int resposta = 0;
    Animation some;
    Animation aparece;
    ConstraintLayout meuLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String minhaUrl = "https://www.json-generator.com/api/json/get/ceaALaXlGq?indent=2";
        questoes = new ArrayList<Questao>();
        radioGroup = findViewById( R.id.meuGrupo );
        radioButton1 = findViewById( R.id.radioButton );
        radioButton2 = findViewById( R.id.radioButton2 );
        radioButton3 = findViewById( R.id.radioButton3 );
        tvPergunta   = findViewById( R.id.tvPergunta );
        tvTitulo     = findViewById( R.id.tvTitulo );
        progressBar  = findViewById( R.id.progressBar );
        botaoCofnirma = findViewById( R.id.botaoConfirma );
        meuLayout     = findViewById( R.id.meuLayout );
        some          = new AlphaAnimation(1,0);
        aparece       = new AlphaAnimation(0,1);

        some.setDuration(1000);
        aparece.setDuration( 1000 );

        meuLayout.setVisibility( View.GONE );

        meuLayout.startAnimation( aparece );


        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                meuLayout.setVisibility( View.VISIBLE );

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                progressBar.setVisibility( View.GONE );
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    some.setAnimationListener(new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            meuLayout.setVisibility( View.VISIBLE );
            progressBar.setVisibility( View.VISIBLE );
        }

        @Override
        public void onAnimationEnd(Animation animation) {

            if(rodada>=questoes.size()){
                fimDeJogo();
            }else{
                atualizaView();
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton:
                            resposta = 1;
                        break;
                    case R.id.radioButton2:
                            resposta = 2;
                        break;
                    case R.id.radioButton3:
                            resposta = 3;
                        break;
                }
                botaoCofnirma.setEnabled( true );
            }
        });


        new JSONTask().execute( minhaUrl );

        botaoCofnirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                proximaPergunta();
            }
        });


    }

    void proximaPergunta(){
        if( resposta == questoes.get(rodada).getCorreta() ){
            acertos++;
        }

        rodada++;
        meuLayout.startAnimation( some );
    }
    void fimDeJogo(){
        meuLayout.setVisibility(View.GONE);
        progressBar.setVisibility( View.GONE );
        //Toast.makeText(this, "Pontuação: "+acertos, Toast.LENGTH_SHORT).show();
        criaAlerta();
    }
    void criaAlerta(){
        AlertDialog.Builder alerta;
        alerta = new AlertDialog.Builder( MainActivity.this );
        alerta.setTitle( "Fim de Jogo" );
        alerta.setMessage("Você marcou "+acertos+" pontos");
        alerta.setIcon( R.drawable.interrogacao );
        alerta.setCancelable( false );
        alerta.setPositiveButton("Jogar novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                acertos = 0;
                rodada = 0;
                atualizaView();
            }
        });
        alerta.create();
        alerta.show();
    }
    public void  atualizaView(){
        progressBar.setVisibility( View.GONE );
        radioGroup.clearCheck();
        botaoCofnirma.setEnabled( false );
        tvPergunta.setText( questoes.get( rodada ).getPergunta() );
        radioButton1.setText( questoes.get( rodada ).getRespA() );
        radioButton2.setText( questoes.get( rodada ).getRespB() );
        radioButton3.setText( questoes.get( rodada ).getRespC() );
        meuLayout.startAnimation( aparece );
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


           // Log.i("meuLog",""+result);

        try {

            JSONObject listaJSON = new JSONObject(result);
            tvTitulo.setText( listaJSON.getString("titulo") );

            JSONArray questionario = listaJSON.getJSONArray("questionario");
            for (int i = 0; i < questionario.length(); i++){
                JSONObject questionary = questionario.getJSONObject(i);
                String pergunta = questionary.getString("pergunta");
                String respA    = questionary.getString("respA");
                String respB    = questionary.getString("respB");
                String respC    = questionary.getString("respC");
                int    correta  = questionary.getInt("correta");
                Questao questao = new Questao( pergunta, respA, respB, respC, correta );
                questoes.add( questao );

            }



            atualizaView();

        }catch (JSONException e){e.printStackTrace();}

        }
    }
}
