package br.com.brito.twittercurso;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    private String uid;
    private String usuario;

    private ArrayList<String> seguindo;

    private ListView listaTweets;

    private ArrayList<Tweet> tweets;

    private ChildEventListener tweetEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        tweets = new ArrayList<>();
        seguindo = new ArrayList<>();

        //adicionando cabeçalho à lista
        listaTweets = findViewById( R.id.listTweets );
        View header = getLayoutInflater().inflate( R.layout.list_header, null );
        listaTweets.addHeaderView( header );

        Button btnTweet = findViewById( R.id.btnTweet );
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoTweet();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if( user == null ) finish();

        getUserInfo();
    }

    private void getUserInfo(){
        String uid = mAuth.getCurrentUser().getUid();
       // Log.d("log", uid);
        DatabaseReference userRef = database.getReference( "users/" + uid );
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuario = dataSnapshot.child("usuario").getValue( String.class );
                
                seguindo.clear(); //limpar lista
                for (DataSnapshot s: dataSnapshot.child("seguindo").getChildren()){
                    seguindo.add( s.getValue( String.class ) );
                }
                TextView headerUsuario = findViewById( R.id.headerUsuario );
                headerUsuario.setText( usuario );

                TextView headerSeguindo = findViewById( R.id.headerSeguindo );
                headerSeguindo.setText( "Seguindo: "+ seguindo.size() );

                setTweetListener();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void novoTweet(){
        AlertDialog.Builder alert = new AlertDialog.Builder( this );
        alert.setTitle( "Novo tweet" );
        final EditText texto = new EditText( this );
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter( 140 );
        texto.setFilters( filterArray );

        alert.setView( texto );
        alert.setPositiveButton("Publicar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if( texto.getText().toString().equals( "" ) ) return;

                Tweet novoTweet = new Tweet( uid, usuario, texto.getText().toString() );
                DatabaseReference refTweets = database.getReference( "tweets" );
                refTweets.push().setValue( novoTweet );
            }
        });

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
    private void setTweetListener(){

        tweetEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Tweet tweet = dataSnapshot.getValue( Tweet.class );

               // if( seguindo.contains( tweet.getUsuario() )  || tweet.getUid().equals( uid ) ){
                    tweets.add( 0, tweet ); // 0 significa no topo da lista
                    Log.d( "tweet", tweet.getTexto() );
               // }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        DatabaseReference tweetRef = database.getReference( "tweets" );
        Query tweetQuery = tweetRef.limitToLast( 100 );
        tweetQuery.addChildEventListener( tweetEventListener );
    }

    @Override
    protected void onStop() {
        super.onStop();
        if( tweetEventListener != null ){
            DatabaseReference tweetRef = database.getReference( "tweets" );
            tweetRef.removeEventListener( tweetEventListener );
        }
    }
}
