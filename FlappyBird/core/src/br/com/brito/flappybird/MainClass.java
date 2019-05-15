package br.com.brito.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainClass extends ApplicationAdapter {
	private Viewport viewport;
	private SpriteBatch batch;
	private Background back;
	private Felpudo felpudo;
	private Array<Pipe> pipes;
	private float timeToNext = 1.5f;
	private enum State { WAIT, PLAY, DIE, FINISH };
	private State state;
	private float timeToFinish;
	private int score = 0;
	private boolean counting = false;
	private BitmapFont font150;
	private BitmapFont font250;
	private GlyphLayout glyphLayout;
	@Override
	public void create () {
		viewport = new FitViewport( 1000, 1700 );
		batch =  new SpriteBatch();
		back  = new Background();
		felpudo = new Felpudo();
		pipes = new Array<Pipe>();
		pipes.add( new Pipe() );
		state = State.WAIT;
        FreeTypeFontGenerator.setMaxTextureSize( FreeTypeFontGenerator.NO_MAXIMUM );
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator( Gdx.files.internal( "font.ttf" ) );
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        parameter.size = 150;
        font150 = generator.generateFont( parameter );

        parameter.size = 150;
        font250 = generator.generateFont( parameter );
        generator.dispose();
        glyphLayout = new GlyphLayout();
	}

	@Override
	public void render () {
	    float delta = Gdx.graphics.getDeltaTime();
		update( delta );

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix( viewport.getCamera().combined );
		batch.begin();

		draw( batch );

		batch.end();
	}

	private void update( float delta ){
		switch ( state ){
			case WAIT:
				  felpudo.update( delta );
				  back.update( delta );
				  if( Gdx.input.justTouched() ){
				  	state = State.PLAY;
				  	felpudo.fly();
				  }
				break;
			case PLAY:
				  if( felpudo.update( delta ) == -1 ){
				  	state = State.DIE;
				  	timeToFinish = 0.5f;
				  }
				  back.update( delta );
				  timeToNext -= delta;
				  if( timeToNext <= 0 ){
					pipes.add( new Pipe() );
					timeToNext = 1.5f;
				  }
				  for(int i = 0; i< pipes.size; i++){
					Pipe p = pipes.get( i );
					if( p.update( delta ) == -1 ){
						pipes.removeIndex( i );
						i++;
						//Gdx.app.log( "log", "Destruiu" );
					}
				  }

				  boolean aux = false;

				  for(Pipe p: pipes){
				  	if( Intersector.overlaps( felpudo.body, p.up ) || Intersector.overlaps( felpudo.body, p.down ) ){
				  		state = State.DIE;
				  		timeToFinish = 2f;
						Gdx.app.log( "log", "Perdeu" );
				  		Gdx.input.vibrate( 2000 );
				  		felpudo.die();
					}
				  	if( Intersector.overlaps( felpudo.body, p.score ) ){
						//Gdx.app.log( "log", "Passou" );
				  		aux = true;
						if( !counting ){
							score++;
							//Gdx.app.log( "log", "pontos: "+ score );
							counting = true;
						}

					}
				  }
				  if( !aux ) counting = false;
				  if( Gdx.input.justTouched() ) felpudo.fly();
				break;
			case DIE:
				  felpudo.update( delta );
				  timeToFinish -= delta;
				  if( timeToFinish <= 0 ){
				  	state = State.FINISH;
				  }
				break;
			case FINISH:
				if( Gdx.input.justTouched() ) reset();
		}

	}


	private void reset(){
		state = State.WAIT;
		felpudo = new Felpudo();
		pipes.clear();
		timeToNext = 1.5f;
		score = 0;
		counting = false;
	}


	private void draw( SpriteBatch batch ){
		back.draw( batch );

		for (Pipe p: pipes){
			p.draw( batch );
		}

		felpudo.draw( batch );

		switch ( state ){
            case WAIT:
                font150.draw( batch, "Touch!", ( 1000 - getTamX( font150, "Touch!" ) ) / 2, 1200 );
                break;
            case PLAY:
            case DIE:
                font150.draw( batch, String.valueOf( score ), ( 1000 - getTamX( font150, String.valueOf( score ) ) ) /2, 1600 );
                break;
            case FINISH:
                font150.draw( batch, "Score: ", ( 1000 - getTamX( font150, "Score:" ) ) / 2, 1200 );
                font250.draw( batch, String.valueOf( score ), ( 1000 - getTamX( font250, String.valueOf( score ) ) ) /2, 1000 );
                break;
        }
	}

	@Override
	public void resize(int width, int height) {
		viewport.update( width, height, true );
	}

	@Override
	public void dispose () {
		batch.dispose();
		font150.dispose();
		font250.dispose();
		felpudo.dispose();
		back.dispose();
		for( Pipe p: pipes ){
			p.dispose();
		}
	}

	private float getTamX( BitmapFont font, String text ){
	    glyphLayout.reset();
	    glyphLayout.setText( font, text );
	    return glyphLayout.width;
    }
}
