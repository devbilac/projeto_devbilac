package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.MainClass;

import entities.Circulo;
import enums.Tipo;

public class SegundaFase implements Screen {

	MainClass game;
	Circulo circulos;
	
	public SegundaFase(MainClass game) {
		this.game = game;
		criaCirculo();
	}
	
	private void criaCirculo() {
		String img = "assets\\circulo.png";
		float x = 100;
		float y = 300;
		circulos = new Circulo(img, Tipo.BOOLEAN, x, y);
	}
	
	public void show() {
		
	}

	public void render(float delta) {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		game.batch.begin();

		game.batch.draw(circulos.getImg(), circulos.getX(), circulos.getY());
		
		circulos.getFont().draw(game.batch, circulos.getMsg(), circulos.getMsgX(), circulos.getMsgY());
		
		game.batch.end(); 			
	}

	public void resize(int width, int height) {
		
	}

	public void pause() {
		
	}

	public void resume() {
		
	}

	public void hide() {
		
	}

	public void dispose() {
		circulos.getFont().dispose();
	}

}