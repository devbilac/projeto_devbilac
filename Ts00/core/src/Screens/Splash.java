package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.DevBilac;

import Tools.SpriteAcessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class Splash implements Screen {
private Sprite splash;
private SpriteBatch batch;
private TweenManager tweenManager;
private DevBilac game;
public Splash(DevBilac game){
	this.game = game;
}

	@Override
	public void show() {
		batch = new SpriteBatch();
		tweenManager =new TweenManager();
		Tween.registerAccessor(Sprite.class,new SpriteAcessor());
		Texture splashTexture = new Texture(Gdx.files.internal("Logo.jpeg"));
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		Tween.set(splash,SpriteAcessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splash,SpriteAcessor.ALPHA,2).target(1).repeatYoyo(1,2).setCallback(new TweenCallback(){
			@Override
			public void onEvent (int type,BaseTween<?>source){
				((Game)Gdx.app.getApplicationListener()).setScreen(new Login(game));
				
			}
		}).start(tweenManager);
		
		//tweenManager.update(Gdx.graphics.getDeltaTime());
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tweenManager.update(delta);
		batch.begin();
		splash.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {

   batch.dispose();
   splash.getTexture().dispose();
		
	}

}
