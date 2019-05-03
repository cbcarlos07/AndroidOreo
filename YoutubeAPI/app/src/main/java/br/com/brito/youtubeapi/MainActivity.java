package br.com.brito.youtubeapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

public class MainActivity extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener ytListener;
    ImageView image;
    String youtubeID = "LwU8TELuVh4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //https://img.youtube.com/vi/6lZp4to_7ZI/hqdefault.jpg
        youTubePlayerView = findViewById( R.id.meuPlayer );
        image = findViewById( R.id.imageView );
        ytListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo( youtubeID );
               // youTubePlayer.cueVideo( youtubeID );
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize( YouTubeConfig.getAPI_KEY(), ytListener );
        String caminhoImagem = "https://img.youtube.com/vi/"+youtubeID+"/hqdefault.jpg";
        Picasso.with( getApplicationContext() ).load( caminhoImagem ).into( image );
    }
}
