package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;

import Scenes.HudUs03;
import Sprites.BarraLateral;
import Sprites.Botao;
import Sprites.Objeto;
import Sprites.Circulo;

//Tela destinada ao Jogo da User Story 07.
public class ScreenUs07Jogo implements Screen {
	private World world;
	private DevBilac game;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private SpriteBatch batch;
	private Circulo circulos;
	Vector3 touchPos;
	int tamanhoOriginalH;
	int tamanhoOriginalW;
	BarraLateral BarraLateral;
	Botao botao;
	int Clicked;
	boolean ClickedBotao;
	Vector3 AuxV01;
	private BitmapFont currentFont;
	String estrutura;
	
	
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
		BarraLateral.setAtivo(true);
		botao = new Botao();
		botao.setTexture(new Texture("images/botao.jpg"));
		botao.setPosition(new Vector3(BarraLateral.getTexture().getWidth(),500,0));
		botao.setAtivo(true);
		currentFont = new BitmapFont();
		criaCirculo();
		estrutura = ""
				+ "Se(bolinha = true){ "
				+ "\nganha +1"
				+ "\n"
				+ "\n}senao{"
				+ "\nperde 1 ponto"
				+ "\n"
				+ "\n}";
	}
	
	private void criaCirculo() {
		String img = "assets\\circulo.png";
		float x = 400;
		float y = 300;
		circulos = new Circulo();
		circulos.setTexture(new Texture("images/circulo.png"));
		circulos.setTipo(1);
		circulos.setPosition(new Vector3(x,y,0));
		circulos.setMensagem("SOU AZUL");
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
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		if(BarraLateral.isAtivo()){
			batch.draw(BarraLateral.getTexture(),BarraLateral.getPosition().x, BarraLateral.getPosition().y);
			currentFont.draw(batch,estrutura, 20, 450);
		}
		batch.draw(botao.getTexture(),botao.getPosition().x,botao.getPosition().y);
		batch.draw(circulos.getTexture(),circulos.getPosition().x,circulos.getPosition().y);
		circulos.getFont().draw(batch, circulos.getMsg(), circulos.getMsgX(), circulos.getMsgY());
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
		currentFont.dispose();
		
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
	        System.out.println(PositionM);
			return PositionM;
		}
		
	public void BarraAcao(){
		
		
		if(Gdx.input.isTouched()){
			if(Clicked == 1){
				AuxV01 = PositionMouse();
					//Verifica se o Mouse esta dentro do Objeto.
					System.out.println(AuxV01);
					if (AuxV01.x >= botao.getPosition().x && AuxV01.x <= (botao.getPosition().x + botao.getTexture().getWidth())){
						if (AuxV01.y >= botao.getPosition().y && AuxV01.y <= (botao.getPosition().y + botao.getTexture().getHeight())){
							System.out.println("Clicou Dentro");
							ClickedBotao = true;
						}
						
					}
			}else{
				if(ClickedBotao == true){
					if (BarraLateral.isAtivo()){
						System.out.println("Desativar!!");
						BarraLateral.setAtivo(false);
						botao.setAtivo(false);
						float Aux01 = BarraLateral.getPosition().x-BarraLateral.getTexture().getWidth();
						BarraLateral.setPosition(new Vector3(Aux01,BarraLateral.getPosition().y,0));
						botao.setPosition(new Vector3(0,500,0));
						
						
					}else{
						System.out.println("Ativar!!");
						BarraLateral.setAtivo(true);
						botao.setAtivo(true);
						BarraLateral.setPosition(new Vector3(0,BarraLateral.getPosition().y,0));
						botao.setPosition(new Vector3(BarraLateral.getTexture().getWidth(),500,0));
						
					}	
					Clicked = 0;
				}
			}
			Clicked++;

		}else{Clicked=0;
		ClickedBotao = false;}
	}
	
}
