package br.com.brito.youtubeapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
     //AIzaSyALJORdJO18X4qzmgGmmLzVYqXg7hvA6Es
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YouTubeConfig.getAPI_KEY();
    }
}
