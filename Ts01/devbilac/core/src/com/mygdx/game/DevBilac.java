package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Screens.ScreenUs03;

public class DevBilac extends Game {
	public static final int V_WIDHT = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;
	public static final float PPM2 = 100;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//setScreen(new PlayScreen(this));
		//setScreen(new Ranking(this));
		setScreen(new ScreenUs03(this));
		//setScreen(new ScreenUs07(this));
		//setScreen(new ScreenUs05(this));
		
	}

	@Override
	public void render () {
		super.render();
		
		
	}
}
