package br.com.brito.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
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

        mAuth = FirebaseAuth.getInstance();
      /*  mAuth.createUserWithEmailAndPassword( "teste@teste.com", "12345678" )
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if( currentUser != null ){
                            //ir para a proxima tela
                            Log.d("log", "Ir para a proxima tela");
                        }else{
                            Toast.makeText(MainActivity.this, "Falha na criação de usuário", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });*/
     /* mAuth.signInWithEmailAndPassword("teste@teste.com", "12345678")
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if( currentUser != null ){
                            //ir para a proxima tela
                            Log.d("log", "Logado com sucesso");
                        }else{
                            Toast.makeText(MainActivity.this, "Falha na autenticação do usuário", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if( currentUser != null ){
            //ir para a proxima tela
            //Log.d("log", "Ir para a proxima tela");
            Log.d("log", "Logado com sucesso");
        }
    }

    public void logout(View view){
        //FirebaseAuth.getInstance().signOut();
        mAuth.signOut();
        Toast.makeText(MainActivity.this, "Deslotou", Toast.LENGTH_SHORT).show();
    }
    public void login( View view ){
        mAuth.signInWithEmailAndPassword("teste@teste.com", "12345678")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            if( currentUser != null ){
                                //ir para a proxima tela
                                Log.d("log", "btn Logado com sucesso");
                            }else{
                                Toast.makeText(MainActivity.this, "Falha na autenticação do usuário", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
