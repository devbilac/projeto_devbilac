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
public class ScreenUs03 implements Screen {
	private HudUs03 hud;
	private World world;
	private DevBilac game;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private SpriteBatch batch;
	Vector3 touchPos;
	int tamanhoOriginalH;
	int tamanhoOriginalW;
	ArrayList<Objeto> objetos = new ArrayList<Objeto>();
	ArrayList<Recipiente> recipientes = new ArrayList<Recipiente>();
	int Clicked = 0;
	boolean ClickedObject = false;
	int idObjeto = 0;
	//Utilizado no Método clicou()
	Vector3 AuxV01 = new Vector3();
	//Utilizado para saber as posições fixas dos objetos na tela.
	Vector3 positions[] = {
			new Vector3(50,200,0),new Vector3(550,200,0),new Vector3(1050,200,0),
			new Vector3(50,300,0),new Vector3(550,300,0),new Vector3(1050,300,0),
			new Vector3(50,400,0),new Vector3(550,400,0),new Vector3(1050,400,0),
			new Vector3(50,500,0),new Vector3(550,500,0),new Vector3(1050,500,0),
			};
	
	public ScreenUs03(DevBilac game){
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
		
		criarObjeto();
		criarRecipiente();
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	public void handleInput(float dt){
	
	clicou();
		
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Desenha o Display na Tela.
		hud.stage.draw(); 
		
		batch.begin();
		//Bloco de codigo que roda o Array para desenhar os Objetos
		for (Objeto objeto : objetos) {
			if(objeto.isAtivo()){
				batch.draw(objeto.getTexture(),objeto.getPosition().x,objeto.getPosition().y);
			}
		}
		//Bloco de codigo que roda o Array para desenhar os Recipientes
		for (Recipiente recipiente : recipientes) {
			if(recipiente.isVisivel()){
				batch.draw(recipiente.getTexture(),recipiente.getPosition().x,recipiente.getPosition().y);
			}
		}
		
		
		batch.end();
		
	}
	
	private void update(float delta) {
		handleInput(delta);
		gamecam.position.x = DevBilac.V_WIDHT / 2; //Camera no centro da Tela.
		gamecam.update();
		hud.update(delta);
		
		
		//Vericia se o jogo Acabou, caso acabou ele envia uma msg no console avisando e mostra a pontuação.
		if(acabouJogo()){
			System.out.println("ACABO");
			System.out.println("Pontos: "+hud.getScore());
		}
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
		// TODO Auto-generated method stub
	}
	
	//Metodo que faz a Ação de quando Ocorre um Clique.
	public void clicou(){
		
		//Caso o Mouse esteje clicando e Jogo Ativo
		if(Gdx.input.isTouched() && acabouJogo() == false){
			
			
			//Salvar qual foi o primeiro Objeto Clicado.
			if(Clicked == 1){
				AuxV01 = PositionMouse();
				for (Objeto objeto : objetos) {
					idObjeto++;
					//Verifica se o Mouse esta dentro do Objeto.
					if (AuxV01.x >= objeto.getPosition().x && AuxV01.x <= (objeto.getPosition().x + objeto.getTexture().getWidth())){
						if (AuxV01.y >= objeto.getPosition().y && AuxV01.y <= (objeto.getPosition().y + objeto.getTexture().getHeight())){
							ClickedObject = true;
							objeto.setPosition(AuxV01);
							System.out.println("Clicou "+objeto.getId());
							break;
						}
						
					}
				}
			}else{
				if(ClickedObject == true){
					for (Objeto objeto : objetos) {
						System.out.println(idObjeto);
						objetos.get(idObjeto-1).setPosition(PositionMouse());
					}
				}
			}
			Clicked++;
		}else{
			Clicked = 0;
			//Salvar qual foi o primeiro Objeto Clicado.
			if(ClickedObject == true){
				AuxV01 = PositionMouse();
				for (Recipiente recipiente : recipientes) {
					//Verifica se o Mouse esta dentro do Objeto.
					if (AuxV01.x >= recipiente.getPosition().x && AuxV01.x <= (recipiente.getPosition().x + recipiente.getTexture().getWidth())){
						if (AuxV01.y >= recipiente.getPosition().y && AuxV01.y <= (recipiente.getPosition().y + recipiente.getTexture().getHeight())){
							//verifica se o Objeto é do mesmo tipo que o recipiente.
							if(objetos.get(idObjeto-1).getTipo() == recipiente.getTipo()){
								System.out.println("DENTROU "+idObjeto);
								objetos.get(idObjeto-1).setAtivo(false);
								Pontuar(objetos.get(idObjeto-1).getTipo());
								recriarObjetos(); //Recria um Objeto de um tipo aleatorio no local do Objeto arrastado anteriormente.
								break;
							}else{//Caso o Objeto arrastado não seja compativel com o Recipiente.
								objetos.get(idObjeto-1).setPosition(positions[objetos.get(idObjeto-1).getId()]);
							}
						}
						
					}
				}
				
			}else{
				posicionarObjetos();}
			Clicked++;
			ClickedObject = false;
			idObjeto=0;
		
		
		}
	}
	
	//Método que move os objetos para a sua posição original.
	public void posicionarObjetos(){
		for (Objeto objeto : objetos) {
			if(objeto.getPosition() != positions[objeto.getId()]){
				objeto.setPosition(positions[objeto.getId()]);
			}
		}
	}
	
	//Metodo utilizado para recriar os objetos. Utilizado quando um Objeto é Oculto ou Inativo.
	public void recriarObjetos(){
		for (Objeto objeto : objetos) {
			if(objeto.isAtivo() == false){
				//Função para randomizar um numero no tipo do objeto.
				Random gerador = new Random();
				objeto.setTipo(gerador.nextInt(4));
				objeto.setAtivo(true);
				//Verifica o tipo do objeto e dependendo do tipo ele recebe uma Texture diferente.
				switch (objeto.getTipo()) {
				case 0:
					objeto.setTexture(new Texture("images/boxBoolean.png"));
					break;
				case 1:
					objeto.setTexture(new Texture("images/boxDouble.png"));
					break;
				case 2:
					objeto.setTexture(new Texture("images/boxInt.png"));
					break;
				case 3:
					objeto.setTexture(new Texture("images/boxString.png"));
					break;

				default:
					objeto.setTexture(new Texture("images/box.png"));
					break;
				}
				objeto.setPosition(positions[objeto.getId()]);
			}
		}
	}
	
	//Metodo que Cria e Armazena os Objetos criados em um Array List.
	public void criarObjeto(){
		int LimiteObj = 11;
		//new Vector3(X,Y,Z)
		
		for (int i = 0; i <= LimiteObj; i++) {
	        Random gerador = new Random();
			Objeto obj = new Objeto();
			if(i<10){
				obj.setId(i);
			}else{obj.setId(i);}
			//Gera um Numero de 0 a X, depois verificamos no vetor qual numero se refere ao tipo.
			obj.setTipo(gerador.nextInt(4));
			obj.setAtivo(true);
			switch (obj.getTipo()) {
			case 0:
				obj.setTexture(new Texture("images/boxBoolean.png"));
				break;
			case 1:
				obj.setTexture(new Texture("images/boxDouble.png"));
				break;
			case 2:
				obj.setTexture(new Texture("images/boxInt.png"));
				break;
			case 3:
				obj.setTexture(new Texture("images/boxString.png"));
				break;

			default:
				obj.setTexture(new Texture("images/box.png"));
				break;
			}
			obj.setPosition(positions[i]);
			objetos.add(obj);
		}
	}
	
	//Metodo que Cria e Armazena os Recipientes criados em um Array List.
	public void criarRecipiente(){
		int LimiteRecp = 4;
		//new Vector3(X,Y,Z)
		Vector3 positions[] = {new Vector3(50,50,0),new Vector3(350,50,0),new Vector3(650,50,0),new Vector3(950,50,0)};
		for (int i = 0; i < LimiteRecp; i++) {
			Recipiente recp = new Recipiente();
			recp.setId(i);
			recp.setTipo(i);
			switch (recp.getTipo()) {
			case 0:
				recp.setTexture(new Texture("images/boxBoolean.png"));
				break;
			case 1:
				recp.setTexture(new Texture("images/boxDouble.png"));
				break;
			case 2:
				recp.setTexture(new Texture("images/boxInt.png"));
				break;
			case 3:
				recp.setTexture(new Texture("images/boxString.png"));
				break;

			default:
				recp.setTexture(new Texture("images/box.png"));
				break;
			}
			recp.setVisivel(true);
			recp.setPosition(positions[i]);
			recipientes.add(recp);
		}
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
	
	//Método utilizado para fazer o Sistema de Pontuação do jogo.
	public void Pontuar(int Tipo){
		hud.setScore(Tipo);
	}

	
	//Verifica se o Timer do jogo esta em 0, caso esteja ele retorna True, para assim notificar que o jogo acabou.
	public boolean acabouJogo(){
		if (hud.getTimer() == 0) {
			return true;
		}else{return false;}
		
	}

}