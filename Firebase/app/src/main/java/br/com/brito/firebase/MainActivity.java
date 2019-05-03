package br.com.brito.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database= FirebaseDatabase.getInstance();

        //DatabaseReference myRef = database.getReference("nomes/Daniel/cidade");
        DatabaseReference myRef = database.getReference("nomes");
        //myRef.setValue( "Hello World" );
        myRef.child("Daniel").child("cidade").setValue("teste1");
        Log.d( "meuLog", "firebase" );
    }
}
