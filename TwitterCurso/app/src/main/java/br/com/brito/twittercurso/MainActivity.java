package br.com.brito.twittercurso;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    private String uid;
    private String usuario;

    private ArrayList<String> seguindo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        seguindo = new ArrayList<>();
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
        Log.d("log", uid);
        DatabaseReference userRef = database.getReference( "users/" + uid );
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuario = dataSnapshot.child("usuario").getValue( String.class );

                seguindo.clear(); //limpar lista
                for (DataSnapshot s: dataSnapshot.child("seguindo").getChildren()){
                    seguindo.add( s.getValue( String.class ) );
                }
                Log.d("log", "Usuario: "+usuario);
                Log.d( "log_lista", seguindo.toString() );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
