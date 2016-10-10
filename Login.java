package com.mygdx.game.telas;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Login implements Screen{
	private Stage stage ;
	private TextureAtlas atlas ;
	private Skin skin ;
	private TextButton gravar ;
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),atlas);
		skin.addRegions(atlas);
		gravar = new TextButton("Gravar",skin);
		gravar.addListener(new ClickListener(){
			public void clicked (InputEvent event, float x,float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		});
        gravar.setPosition(300,300);
        gravar.setSize(300, 60);
        stage.addActor(gravar);
		
	}


		
		
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		//table.invalidateHierarchy();
		//table.setSize(width,height);
		
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
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		((ApplicationListener) gravar).dispose();
		
		
	}

}
