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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById( R.id.meuPlayer );
        image = findViewById( R.id.imageView );
        ytListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo( "mhmaLQ1TFa0" );
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize( YouTubeConfig.getAPI_KEY(), ytListener );
        String caminhoImagem = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvndm8RETCt8HUmi0t7AyMtzeP2fOLVPtrYljFV8hKPn33Md_n";
        Picasso.with( getApplicationContext() ).load( caminhoImagem ).into( image );
    }
}
