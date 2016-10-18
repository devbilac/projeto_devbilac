package Screens;

import com.badlogic.gdx.Gdx;
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
		this.visor = new Visor(180, 410);
		this.botao = new Botao[12];
		criaBotao();
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
		
		verificaClick();
		mouseOver();
				
		for (Botao b : botao) {
			batch.draw(b.getImg(), b.getPos().x, b.getPos().y);			
			b.getFont().draw(batch, b.getText(), b.getPosText().x, b.getPosText().y);			
		}
		
		batch.draw(visor.getImg(), visor.getPos().x, visor.getPos().y);
		visor.getFont().draw(batch, visor.getText(), visor.getPosText().x, visor.getPosText().y);
				
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
					visor.apaga();			
				else if (b.getText().equals("Confirma")) {
					/* Ação quando o usuario confirmar resposta;
					 * 
					 * 
					 * 
					 * 
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
				if ((Gdx.input.getX() >= b.getPos().x && Gdx.input.getX() <= b.getPos().x + b.getImg().getWidth()) && Gdx.graphics.getHeight() - Gdx.input.getY() >= b.getPos().y && Gdx.graphics.getHeight() - Gdx.input.getY() <= b.getPos().y + b.getImg().getHeight()) {
					b.setImg(new Texture("images\\botaoConfirmaOver.png"));
				} else {
					b.setImg(new Texture("images\\botaoConfirma.png"));				
				}								
			}
		}		
	}
	
	private void criaBotao() {
		Texture img = new Texture("images\\botao.png");
		
		float x = 180;
		float y = 200;
		int c = 0;
		
		for (int i = 1; i < 10; i++) {
			botao[i] = new Botao(x, y, String.valueOf(i), img);
			
			x += 100;
			c++;
			
			if (c == 3) {
				c = 0;
				y += 70;
				x = 180;
			}
		}
		
		y = 130;
		x = 180;
		botao[0] = new Botao(x, y, "0", img);

		x += 100;
		img = new Texture("images\\botaoC.png");
		botao[10] = new Botao(x, y, "C", img);
		
		y -= 70;
		x = 180;
		img = new Texture("images\\botaoConfirma.png");		
		botao[11] = new Botao(x, y, "Confirma", img);
		botao[11].getPosText().x -= 70;
	}
	
	
	
	
	
	
}
