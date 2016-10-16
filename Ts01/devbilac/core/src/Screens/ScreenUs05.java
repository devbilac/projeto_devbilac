package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;

import Scenes.HudUs05;
import Scenes.HudUs07;

public class ScreenUs05 implements Screen {
	private HudUs05 hud;
	//Variaveis Box2d
	private World world;
	private DevBilac game;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private SpriteBatch batch;
	private Vector3 touchPos;
	int tamanhoOriginalH;
	int tamanhoOriginalW;
	
	
	public ScreenUs05(DevBilac game){
		// ----------------------- CONFIGURAÇÕES PADRÃO TELA
		batch = new SpriteBatch();
		// Chama o HudRanking que Ã© um 'Display' na tela, onde ficas Labels.
		this.game = game;
		gamecam = new OrthographicCamera();
		world = new World(new Vector2(0,-200 / DevBilac.PPM), true);
		//Alterar para mudar a Gravidade do mundo
		world = new World(new Vector2(0,-200 / DevBilac.PPM), true);
		//o 'Metodo' FitViewport faz o redimencionamento de tela.
		gamePort = new FitViewport(DevBilac.V_WIDHT / DevBilac.PPM,DevBilac.V_HEIGHT / DevBilac.PPM,gamecam);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() /2,0);
		hud = new HudUs05(game.batch); 
		hud.setTimer(60); //Envia o Tempo que o jogo tera para o Display.
		
		
		// ----------------------- CONFIGURAÇÕES ADICIONAIS
		//Pega o Tamanho Atual da Tela, Largura e Altura e armazena.
		tamanhoOriginalH = Gdx.graphics.getHeight();
		tamanhoOriginalW = Gdx.graphics.getWidth();
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void handleInput(float delta){
		
	}
	
	public void update(float delta){
		handleInput(delta);
		hud.update(delta);
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		hud.stage.draw(); 
		batch.begin();
		//batch.draw();
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

	//Logo Abaixo METODOS criados pelos Desenvolvedores:
	
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
        System.out.println(PositionM);
		return PositionM;
	}
}
