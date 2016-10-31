package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.telas.CriarConta;
import com.mygdx.game.telas.FaseVetoresMatrizes;
import com.mygdx.game.telas.Login;
import com.mygdx.game.telas.MainMenu;
import com.mygdx.game.telas.Splash;


public class MyGdxGame extends Game {
	
public static final String TITLE ="Dev Bilac " ,VERSION ="0.0.0.0";
public static final int V_WIDHT = 400;
public static final int V_HEIGHT = 208;
public static final float PPM = 100;
public SpriteBatch batch;
	
	@Override
	public void create () {
    setScreen(new MainMenu());
    batch = new SpriteBatch();
		
	}

	

	@Override
	public void render () {		
		
		super.render();
		
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		
	}

	@Override
	public void pause() {
		super.pause();
		
	}

	@Override
	public void resume() {
	super.resume();
		
	}
}
