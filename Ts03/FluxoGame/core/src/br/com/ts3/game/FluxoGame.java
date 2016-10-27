package br.com.ts3.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.com.ts3.game.Screens.PlayScreen;

public class FluxoGame extends Game {
	//Virtual Screen size and Box2D Scale(Pixels Per Meter)
		public static final int V_WIDTH = 400;
		public static final int V_HEIGHT = 208;
		public static final float PPM = 100;
		
		//Box2D Collision Bits
		public static final short DEFAULT_BIT = 0;
		public static final short ESCADA_BIT = 1;
		public static final short DEVB_BIT = 2;
		public static final short CHEST_BIT = 4;
		public static final short COIN_BIT = 8;
		public static final short PASS_BIT = 16;
		
		public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}