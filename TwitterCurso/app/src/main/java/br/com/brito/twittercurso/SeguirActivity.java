package br.com.brito.twittercurso;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SeguirActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private ListView listaSeguindo;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> seguindo;
    private ArrayList<String> usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguir);

        final String uid = getIntent().getStringExtra( "uid" );
        database = FirebaseDatabase.getInstance();

        listaSeguindo = findViewById( R.id.listaSeguindo );

        usuarios = new ArrayList<>();
        seguindo = new ArrayList<>();

        adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_checked, usuarios );
        listaSeguindo.setChoiceMode( AbsListView.CHOICE_MODE_MULTIPLE );
        listaSeguindo.setAdapter( adapter );

        listaSeguindo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView = ( CheckedTextView ) view;
                if( checkedTextView.isChecked() ){
                    seguindo.add( usuarios.get( position ) );
                }else{
                    seguindo.remove( seguindo.indexOf( usuarios.get( position ) ) );
                }
                DatabaseReference seguindoRef = database.getReference( "users/" + uid + "/seguindo" );
                seguindoRef.setValue( seguindo );

            }
        });

        DatabaseReference usersRef = database.getReference( "users" );
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for ( DataSnapshot d: dataSnapshot.getChildren() ){
                    if( d.getKey().equals( uid ) ){
                        seguindo.clear();
                        for( DataSnapshot s: d.child( "seguindo" ).getChildren() ){
                            seguindo.add( s.getValue( String.class ) );
                        }
                    }else{
                        String usuario = d.child( "usuario" ).getValue( String.class );
                        usuarios.add( usuario );
                    }
                }
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void updateUI(){
        adapter.notifyDataSetChanged();
        for(String u: usuarios){
            listaSeguindo.setItemChecked( usuarios.indexOf( u ), seguindo.contains( u ) );
        }
    }
}
