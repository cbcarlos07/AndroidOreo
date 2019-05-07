package br.com.brito.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database= FirebaseDatabase.getInstance();

        //DatabaseReference myRef = database.getReference("nomes/Daniel/cidade");
        //DatabaseReference myRef = database.getReference("nomes");
        //DatabaseReference myRef = database.getReference("nomes/Tito");
        //DatabaseReference myRef = database.getReference("nomes/Daniel");
       // DatabaseReference myRef = database.getReference("nomes");
        //myRef.setValue( "Hello World" );
        //myRef.child("Daniel").child("cidade").setValue("teste1");
       /* Map<String,Object> infos = new HashMap<>();
        infos.put( "nome", "Daniel" );
        infos.put("sobrenome","Ciolfi");
        infos.put("cidade","Campinas");
        infos.put("cargo", "Instrutor");
        myRef.setValue( infos );*/
       /*Pessoa tito = new Pessoa("Tito", "Petri", "São Paulo", "Instrutor");
       myRef.setValue( tito );*/
     /*   ValueEventListener danielListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Pessoa daniel = dataSnapshot.getValue( Pessoa.class );
                Log.d("log", daniel.nome + "  "+ daniel.sobrenome  );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };*/
        //myRef.addValueEventListener( danielListener ); //Atualizar toda vez
       // myRef.addListenerForSingleValueEvent( danielListener ); //Atualizar apenas uma vez
/*
        ChildEventListener nomes = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Pessoa pessoa = dataSnapshot.getValue( Pessoa.class );
                Log.d("log", pessoa.nome+ "  "+ pessoa.sobrenome);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Pessoa pessoa = dataSnapshot.getValue( Pessoa.class );
                Log.d("log", "mudou: " + pessoa.nome+ "  "+ pessoa.sobrenome);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Pessoa pessoa = dataSnapshot.getValue( Pessoa.class );
                Log.d("log", "removido: " + pessoa.nome+ "  "+ pessoa.sobrenome);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myRef.addChildEventListener( nomes );
        myRef.push().setValue( new Pessoa("teste","teste", "teste", "teste") );*/
    }
}
