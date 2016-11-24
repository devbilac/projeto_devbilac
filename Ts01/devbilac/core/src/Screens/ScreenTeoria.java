	package Screens;

	import java.util.ArrayList;
	import java.util.Random;

	import javax.swing.JOptionPane;

	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.Input.Keys;
	import com.badlogic.gdx.Screen;
	import com.badlogic.gdx.graphics.GL20;
	import com.badlogic.gdx.graphics.OrthographicCamera;
	import com.badlogic.gdx.graphics.Texture;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
	import com.badlogic.gdx.math.Vector3;
	import com.badlogic.gdx.physics.box2d.World;
	import com.badlogic.gdx.utils.viewport.FitViewport;
	import com.badlogic.gdx.utils.viewport.Viewport;
	import com.mygdx.game.DevBilac;

	import Model.Ranking;
	import Scenes.HudUs03;
	import Sprites.Objeto;
	import Sprites.Recipiente;
	
public class ScreenTeoria implements Screen {

		private HudUs03 hud;
		private World world;
		private DevBilac game;
		private OrthographicCamera gamecam;
		private Viewport gamePort;
		private SpriteBatch batch;
		Vector3 touchPos;
		int tamanhoOriginalH;
		int tamanhoOriginalW;
		Screen tela;
		public ScreenTeoria(DevBilac game,String texto,Screen tela){
			this.tela = tela;
			//Pega o Tamanho Atual da Tela, Largura e Altura e armazena.
			tamanhoOriginalH = Gdx.graphics.getHeight();
			tamanhoOriginalW = Gdx.graphics.getWidth();
			touchPos = new Vector3();
			batch = new SpriteBatch();
			// Chama o HudRanking que é um 'Display' na tela, onde ficas Labels.
			hud = new HudUs03(game.batch); 
			hud.setTimer(60); //Envia o Tempo que o jogo tera para o Display.
			this.game = game;
			gamecam = new OrthographicCamera();
			//o 'Metodo' FitViewport faz o redimencionamento de tela.
			gamePort = new FitViewport(DevBilac.V_WIDHT / DevBilac.PPM,DevBilac.V_HEIGHT / DevBilac.PPM,gamecam);
			gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() /2,0);
			
		}
		
		
		@Override
		public void show() {
			// TODO Auto-generated method stub
			
		}
		public void handleInput(float dt){
			if (Gdx.input.isKeyPressed(Keys.Z)){
				//Conversando
				//Exibir conteudo na tela.;
				//System.out.println("ESTOU CONVERSANDO COM O NPC!!!");
				game.setScreen(tela);
	        	this.dispose();
			}
		}
		
		@Override
		public void render(float delta) {
			update(delta);
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			//Desenha o Display na Tela.
			hud.stage.draw(); 
			
			batch.begin();
			
			batch.end();
			
		}
		
		private void update(float delta) {
			handleInput(delta);
			gamecam.position.x = DevBilac.V_WIDHT / 2; //Camera no centro da Tela.
			gamecam.update();
			hud.update(delta);
			
		}
		
		@Override
		public void resize(int width, int height) {
			//Metodo de Redimencionamento de Tela.
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
	        this.world.dispose();
	        this.hud.dispose();
		}

	}