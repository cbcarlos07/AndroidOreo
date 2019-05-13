package br.com.brito.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainClass extends ApplicationAdapter {
	private Viewport viewport;
	private SpriteBatch batch;
	private Background back;
	private Felpudo felpudo;
	private Array<Pipe> pipes;
	@Override
	public void create () {
		viewport = new FitViewport( 1000, 1700 );
		batch =  new SpriteBatch();
		back  = new Background();
		felpudo = new Felpudo();
		pipes = new Array<Pipe>();
		pipes.add( new Pipe() );
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
		back.update( delta );
		if( felpudo.update( delta ) == -1 ){
			//perdemos
		}

		for(int i = 0; i< pipes.size; i++){
			Pipe p = pipes.get( i );
			if( p.update( delta ) == -1 ){
				pipes.removeIndex( i );
				i++;
				Gdx.app.log( "log", "Destruiu" );
			}
		}
		if( Gdx.input.justTouched() ) felpudo.fly();
	}

	private void draw( SpriteBatch batch ){
		back.draw( batch );

		for (Pipe p: pipes){
			p.draw( batch );
		}

		felpudo.draw( batch );
	}

	@Override
	public void resize(int width, int height) {
		viewport.update( width, height, true );

	}

	@Override
	public void dispose () {

	}
}
