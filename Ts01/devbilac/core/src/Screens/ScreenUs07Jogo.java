package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;

import Scenes.HudUs03;
import Sprites.BarraLateral;
import Sprites.Objeto;

//Tela destinada ao Jogo da User Story 07.
public class ScreenUs07Jogo implements Screen {
	private World world;
	private DevBilac game;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private SpriteBatch batch;
	Vector3 touchPos;
	int tamanhoOriginalH;
	int tamanhoOriginalW;
	BarraLateral BarraLateral;
	public ScreenUs07Jogo(DevBilac game){
		//Pega o Tamanho Atual da Tela, Largura e Altura e armazena.
		tamanhoOriginalH = Gdx.graphics.getHeight();
		tamanhoOriginalW = Gdx.graphics.getWidth();
		touchPos = new Vector3();
		batch = new SpriteBatch();
		// Chama o HudRanking que é um 'Display' na tela, onde ficas Labels.
		this.game = game;
		gamecam = new OrthographicCamera();
		//o 'Metodo' FitViewport faz o redimencionamento de tela.
		gamePort = new FitViewport(DevBilac.V_WIDHT / DevBilac.PPM,DevBilac.V_HEIGHT / DevBilac.PPM,gamecam);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() /2,0);
		BarraLateral = new BarraLateral();
		BarraLateral.setTexture(new Texture("images/fundo.jpg"));
		BarraLateral.setEstrutura("");
		BarraLateral.setPosition(new Vector3(0,0,0));
		BarraLateral.setAtivo(false);
		
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public void handleInput(float delta){
		BarraAcao();
	}
	public void update(float delta){
		handleInput(delta);
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(BarraLateral.getTexture(),BarraLateral.getPosition().x, BarraLateral.getPosition().y);
		batch.end();
		
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
		// TODO Auto-generated method stub
		
	}

	//Metodo Utilizado para pegar as Posições X,Y do Mouse e tratalas adequadamente.
		public Vector3 PositionMouse(){
			Vector3 PositionM = new Vector3();
			touchPos.set(0,Gdx.input.getY(), 0);
	        gamecam.unproject(touchPos);
	        float TouchY = touchPos.y*300;
	        PositionM.x = Gdx.input.getX();
	        PositionM.y = (int)TouchY;
	        float aux01 = (tamanhoOriginalW - Gdx.graphics.getWidth())/2;
	        if(aux01 <=0){
	       	 PositionM.x = Gdx.input.getX()+aux01;
	        }else{
	       	 touchPos.set(Gdx.input.getX(),Gdx.input.getY(), 0);
	       	 gamecam.unproject(touchPos);
	        }
			return PositionM;
		}
		
	public void BarraAcao(){
		if(Gdx.input.isKeyPressed(Keys.M)){
			if (BarraLateral.isAtivo()){
				
				BarraLateral.setAtivo(false);
				float Aux01 = BarraLateral.getPosition().x-BarraLateral.getTexture().getWidth();
				BarraLateral.setPosition(new Vector3(Aux01,BarraLateral.getPosition().y,0));
				
				
			}else{
				BarraLateral.setAtivo(true);
				BarraLateral.setPosition(new Vector3(0,BarraLateral.getPosition().y,0));
				
			}
		}
	}
}
