package Screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;

import Scenes.HudUs05;
import Scenes.HudUs07;
import Tools.Botao;
import Tools.Visor;

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
	private Visor visor;
	private Botao[] botao;
	private double resposta;
	private String pergunta;
	
	
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
		gamePort = new FitViewport(DevBilac.V_WIDTH / DevBilac.PPM,DevBilac.V_HEIGHT / DevBilac.PPM,gamecam);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() /2,0);
		hud = new HudUs05(game.batch); 
		hud.setTimer(60); //Envia o Tempo que o jogo tera para o Display.
		
		
		// ----------------------- CONFIGURAÇÕES ADICIONAIS
		//Pega o Tamanho Atual da Tela, Largura e Altura e armazena.
		tamanhoOriginalH = Gdx.graphics.getHeight();
		tamanhoOriginalW = Gdx.graphics.getWidth();
		this.visor = new Visor(470, 410);
		this.botao = new Botao[11];
		criaBotao();
		gerarQuestao();
	}
	
	
	@Override
	public void show() {
		
	}
	
	public void handleInput(float delta){
		acionarTeclado();
	}


	public void update(float delta){
		hud.update(delta);
		//Bloquear
		if(!acabouJogo()){
			handleInput(delta);
			verificaResposta();
			verificaClick();
			mouseOver();
		}
	}

	@Override
	public void render(float delta) {		
		update(delta);
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		hud.stage.draw(); 
		batch.begin();
		//batch.draw();
				
		for (Botao b : botao) {
			batch.draw(b.getImg(), b.getPos().x, b.getPos().y);			
			b.getFont().draw(batch, b.getText(), b.getPosText().x, b.getPosText().y);			
		}
		batch.draw(visor.getImg(), visor.getPos().x, visor.getPos().y);
		visor.getFont().draw(batch, visor.getTextResposta(), visor.getPosTextResposta().x, visor.getPosTextResposta().y);
		visor.getFont().draw(batch, visor.getTextPergunta(), visor.getPosTextPergunta().x, visor.getPosTextPergunta().y);
		visor.getFont().draw(batch, visor.getTextIgual(), visor.getPosTextIgual().x, visor.getPosTextIgual().y);
				
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
	
	private void verificaClick() {
		for (Botao b : botao) {
			if ((Gdx.input.justTouched() && Gdx.input.getX() >= b.getPos().x && Gdx.input.getX() <= b.getPos().x + b.getImg().getWidth()) && Gdx.graphics.getHeight() - Gdx.input.getY() >= b.getPos().y && Gdx.graphics.getHeight() - Gdx.input.getY() <= b.getPos().y + b.getImg().getHeight()) {
				if (b.getText().equals("C")) 
					visor.apagaResposta();			
				else if (b.getText().equals("Confirma")) {
					/* Botão Confirma foi desativado.
					 * Ação quando o usuario confirmar resposta; 
					 */
				} else {
						visor.add(b.getText());
				}
			}
				
		}
	}
	
	private void mouseOver() {
		for (Botao b : botao) {
			if (!b.getText().startsWith("C")){
				if ((Gdx.input.getX() >= b.getPos().x && Gdx.input.getX() <= b.getPos().x + b.getImg().getWidth()) && Gdx.graphics.getHeight() - Gdx.input.getY() >= b.getPos().y && Gdx.graphics.getHeight() - Gdx.input.getY() <= b.getPos().y + b.getImg().getHeight()) {
					b.setImg(new Texture("images\\botaoOver.png"));
				} else {
					b.setImg(new Texture("images\\botao.png"));				
				}
			} else if (b.getText().equals("C") ){
				if ((Gdx.input.getX() >= b.getPos().x && Gdx.input.getX() <= b.getPos().x + b.getImg().getWidth()) && Gdx.graphics.getHeight() - Gdx.input.getY() >= b.getPos().y && Gdx.graphics.getHeight() - Gdx.input.getY() <= b.getPos().y + b.getImg().getHeight()) {
					b.setImg(new Texture("images\\botaoCOver.png"));
				} else {
					b.setImg(new Texture("images\\botaoC.png"));				
				}				
			} else {
				/*if ((Gdx.input.getX() >= b.getPos().x && Gdx.input.getX() <= b.getPos().x + b.getImg().getWidth()) && Gdx.graphics.getHeight() - Gdx.input.getY() >= b.getPos().y && Gdx.graphics.getHeight() - Gdx.input.getY() <= b.getPos().y + b.getImg().getHeight()) {
					b.setImg(new Texture("images\\botaoConfirmaOver.png"));
				} else {
					b.setImg(new Texture("images\\botaoConfirma.png"));				
				}*/								
			}
		}		
	}
	
	private void criaBotao() {
		Texture img = new Texture("images\\botao.png");
		
		float x = 470;
		float y = 200;
		int c = 0;
		
		for (int i = 1; i < 10; i++) {
			botao[i] = new Botao(x, y, String.valueOf(i), img);
			
			x += 100;
			c++;
			
			if (c == 3) {
				c = 0;
				y += 70;
				x = 470;
			}
		}
		
		y = 130;
		x = 470;
		botao[0] = new Botao(x, y, "0", img);

		x += 100;
		img = new Texture("images\\botaoC.png");
		botao[10] = new Botao(x, y, "C", img);
		
		/*
		 * y -= 70;
		x = 470;
		img = new Texture("images\\botaoConfirma.png");		
		botao[11] = new Botao(x, y, "Confirma", img);
		botao[11].getPosText().x -= 70;
		*/
	}
	
	public void gerarQuestao(){
		
		Random gerador = new Random();
		int limiteNumero = 12;
		int primeiro = gerador.nextInt(limiteNumero);
		int segundo = gerador.nextInt(limiteNumero);
		boolean GerarMaior = true;
		
		while(GerarMaior){
			if(primeiro < segundo){
				primeiro = gerador.nextInt(limiteNumero);
			}else{GerarMaior = false;}
		}
		while(primeiro == 0 && segundo == 0){
			primeiro = gerador.nextInt(limiteNumero);
			segundo = gerador.nextInt(limiteNumero);
		}
		// ---
		// 0 - Adição ~ 1 - Subtração ~ 2 - Multiplicação ~ 3 - Divisão
		// ---
		int operador = gerador.nextInt(4);
		double resposta = 0;
		String pergunta = "";
		boolean verificador;
		
		switch (operador) {
		case 0:
			resposta = primeiro + segundo;
			pergunta = primeiro+" + "+segundo;
			break;

		case 1:
			resposta = primeiro - segundo;
			pergunta = primeiro+" - "+segundo;
			break;

		case 2:
			resposta = primeiro * segundo;
			pergunta = primeiro+" * "+segundo;
			break;

		case 3:
			verificador = true;
			while(verificador){
				double aux = primeiro / segundo;
				if(inteiro(aux)){
					resposta = primeiro / segundo;
					pergunta = primeiro+" / "+segundo;
					verificador = false;
				}else{
					primeiro = gerador.nextInt(limiteNumero);
					segundo = gerador.nextInt(limiteNumero);
					GerarMaior = true;
					while(GerarMaior){
						if(primeiro < segundo){
							primeiro = gerador.nextInt(limiteNumero);
							segundo = gerador.nextInt(limiteNumero);
						}else{GerarMaior = false;}
					}
					while(primeiro == 0 && segundo == 0){
						primeiro = gerador.nextInt(limiteNumero);
						segundo = gerador.nextInt(limiteNumero);
					}
				}
				
			}
			resposta = primeiro / segundo;
			pergunta = primeiro+" / "+segundo;
			break;

		default:
			//Caso ocorra algum erro não previsto, como default a operação é Adição.
			resposta = primeiro + segundo;
			pergunta = primeiro+" + "+segundo;
			break;
		}
		this.pergunta = pergunta;
		this.resposta = resposta;

		System.out.println(pergunta+" = "+resposta);
		visor.setTextPergunta(pergunta);
	}
	
	private boolean inteiro(double num) {
		int aux = (int)num;
		return (((double)aux) == num);
	}
	
	public void verificaResposta(){
		if(visor.getTextResposta() != null && visor.getTextResposta() != ""){
			int aux = Integer.parseInt(visor.getTextResposta());
			if(resposta == aux){
				//PONTOS
				System.out.println("Acertou !");
				visor.apagaResposta();
				Pontuar(2);
				//Gerar outra Pergunta
				gerarQuestao();
			}
		}
	}
	
	private void acionarTeclado() {
		//Adicionar Metodos pelo Teclado
				if(Gdx.input.isKeyJustPressed(Keys.NUM_0) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_0)){
					visor.add("0");
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_1) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_1)){
					visor.add("1");
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_2) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_2)){
					visor.add("2");
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_3) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_3)){
					visor.add("3");
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_4) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_4)){
					visor.add("4");
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_5) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_5)){
					visor.add("5");
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_6) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_6)){
					visor.add("6");
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_7) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_7)){
					visor.add("7");
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_8) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_8)){
					visor.add("8");
				}
				if(Gdx.input.isKeyJustPressed(Keys.NUM_9) || Gdx.input.isKeyJustPressed(Keys.NUMPAD_9)){
					visor.add("9");
				}
				if(Gdx.input.isKeyJustPressed(Keys.BACKSPACE)){
					visor.apagaResposta();
				}
	}
	public boolean acabouJogo(){
		if (hud.getTimer() == 0) {
			return true;
		}else{return false;}
		
	}
	public void Pontuar(int Tipo){
		hud.setScore(Tipo);
	}
}
