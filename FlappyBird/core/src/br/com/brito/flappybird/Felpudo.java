package br.com.brito.flappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class Felpudo {

    private Texture[] frames;

    private int curFrame;
    private float timeToNext;

    private Circle body;

    public Felpudo(){
        frames = new Texture[6];
        for (int i = 0; i < 5; i++){
            frames[i] = new Texture( "felpudo/felpudoVoa" + ( i + 1) + ".png" );
        }
        curFrame   = 0;
        timeToNext = 0.2f;

        body = new Circle( 200, 1700/2, 60 );
    }

    public void draw(SpriteBatch batch){

        batch.draw( frames[ curFrame ], body.x - 60, body.y - 60, 120, 120 );


    }

    public void update( float delta ){
        timeToNext -= delta;
        if( timeToNext <= 0 ){
            curFrame += 1;
            if( curFrame >= 5 ) curFrame = 0;
            timeToNext = 0.2f;
        }
    }


}
