package br.com.brito.flappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Pipe {

    private Texture img;

    public Rectangle up;
    public Rectangle down;
    public Rectangle score;


    public Pipe(){
        img = new Texture( "cano.png" );

        float y = MathUtils.random( 300, 1400 );

        up = new Rectangle( 1100, y + 200, 200, 1700 );
        down = new Rectangle( 1100, y - 200 - 1700, 200, 1700  );
        score = new Rectangle( 1100 + 200 + 120, -200, 10, 400 );
    }

    public void draw(SpriteBatch batch){
        batch.draw( img, up.x, up.y, up.width, up.height, 0,0, img.getWidth(), img.getHeight(), false, true );
        batch.draw( img, down.x, down.y, down.width, down.height, 0,0, img.getWidth(), img.getHeight(), false, false );
    }

    public int update( float delta ){
        float SPEED = -500;
        up.x += delta * SPEED;
        down.x += delta * SPEED;
        if( up.x <= -200 ) return -1;
        score.x += delta * SPEED;
        return 0;
    }
}
