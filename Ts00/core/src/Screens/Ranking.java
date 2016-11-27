package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;

import Scenes.HudRanking;

public class Ranking implements Screen {
	private HudRanking hud;
	private World world;
	private DevBilac game;
	 //variaveis basicas da PlayScreen
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private int fase;
	public Ranking(DevBilac game,int fase){
		this.fase = fase;
		hud = new HudRanking(game.batch); // Chama o HudRanking que é um 'Display' na tela, onde ficas Labels.
		this.game = game;
		gameCam = new OrthographicCamera();
        
        //Criando a FitViewport para manter o aspecto virtual em relacao ao tamanho da tela
        gamePort = new FitViewport(DevBilac.V_WIDTH / DevBilac.PPM, DevBilac.V_HEIGHT / DevBilac.PPM, gameCam);
      //Inicializacao da camera do game para comecar no inicio do mapa
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	public void handleInput(float dt){
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
				game.setScreen(new MainMenu(game));
        		System.out.println("Rank ESQ");
        }
	}
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		hud.stage.draw();
		gameCam.update();
        game.batch.begin();
		game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.end();
        
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
	}

	private void update(float delta) {
		handleInput(delta);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		 gamePort.update(width, height);
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
