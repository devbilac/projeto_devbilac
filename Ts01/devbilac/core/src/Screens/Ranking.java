package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.DevBilac;

import Dao.ConexaoAE;
import Scenes.HudRanking;

public class Ranking implements Screen {
	private HudRanking hud;
	private World world;
	private DevBilac game;
	public Ranking(DevBilac game){
		hud = new HudRanking(game.batch); // Chama o HudRanking que é um 'Display' na tela, onde ficas Labels.
		this.game = game;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	public void handleInput(float dt){
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
				//Problema no Dispose();
				//game.setScreen(new PlayScreen(game));
				//dispose();
        		System.out.println("Rank ESQ");
        }
	}
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		hud.stage.draw();
		
	}

	private void update(float delta) {
		handleInput(delta);
		// TODO Auto-generated method stub
		
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
		
	}

}
