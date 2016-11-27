package Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;

import Scenes.Hud;
import Sprites.Devb;
import Sprites.Player;
import Tools.B2WorldCreator;
import Tools.B2WorldCreatorUs04;
import Tools.WorldContactListener;
import Sprites.EnsinoTeorico;
import Sprites.Professor;

public class PlayScreen implements Screen {
	//Referencias para o jogo, usado para set Screen
    private DevBilac game;
    private TextureAtlas atlas;

    //variaveis basicas da PlayScreen
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled Map variaveis
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2D variaveis
    private World world;
    private Box2DDebugRenderer b2dr;

    //Sprites
    private Player player;
    
    private Music music;
    
    static int check = 0;
 // Sprites
 	ArrayList<Professor> professores = new ArrayList<Professor>();
 	ArrayList<EnsinoTeorico> ensinoTeorico = new ArrayList<EnsinoTeorico>();
	public static AssetManager manager;
    public PlayScreen(DevBilac game){
        atlas = new TextureAtlas("Devb.pack");

        this.game = game;
        gameCam = new OrthographicCamera();
        
        //Criando a FitViewport para manter o aspecto virtual em relacao ao tamanho da tela
        gamePort = new FitViewport(DevBilac.V_WIDTH / DevBilac.PPM, DevBilac.V_HEIGHT / DevBilac.PPM, gameCam);

        //Criando a HUD para o placar
        hud = new Hud(game.batch);
        
        //Carregando o mapa ou a renderizacao do mapa
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Principal.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1 / DevBilac.PPM);

        //Inicializacao da camera do game para comecar no inicio do mapa
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        //Criando a Box2D, setup de 0 gravidade em X, -10 gravidade em Y, 
        world = new World(new Vector2(0,-10), true);
        
        //Debugando os Box2D para ver as linhas
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreator(this);

        //Criando o Devb no jogo
        player = new Player(this);

        world.setContactListener(new WorldContactListener());
        music = Gdx.audio.newMusic(Gdx.files.internal("audio/Theme01.mp3"));
		music.setLooping(true); // Looping da Musica
		music.setVolume(0.1f); // Volume da Musica 0.1f = 10%
		music.play(); // Play Musica ...
		criarProfessor();
		criarEnsinos();
    }

    public TextureAtlas getAtlas(){return atlas;}

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        //Configurando a tecla do movimento do Devb, a velocidade e o impulso do pulo.
    	if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <= 1.0F){
        	
            player.b2body.applyLinearImpulse(new Vector2(0, 3.25f), player.b2body.getWorldCenter(), true);
        }
		if(Gdx.input.isKeyPressed(Keys.W) && player.b2body.getLinearVelocity().y <= 1.0f){
        	
            player.b2body.applyLinearImpulse(new Vector2(0, 2.00f), player.b2body.getWorldCenter(), true);
        }

        if((Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) && player.b2body.getLinearVelocity().x <=1.2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);

        if((Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) && player.b2body.getLinearVelocity().x >=-1.2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
    }

    public void update(float dt){
    	//Comandos input do usuario
        handleInput(dt);

        //Um passo na simulacao fisica(60 vezes por segundos)
        world.step(1/60f, 6, 2);

        player.update(dt);
        for (Professor professor : professores) {
			professor.update(dt);
		}
        hud.update(dt);

        //Anexando a camera do jogo ao jogador na coordenada x e y
        System.out.println(player.b2body.getPosition().x);
        if(player.b2body.getPosition().x <2){
            gameCam.position.x = 2;
        }else if(player.b2body.getPosition().x >22){
                gameCam.position.x = (float)22;
        }else{
            gameCam.position.x = player.b2body.getPosition().x;
        }
        if(player.b2body.getPosition().y <1.05){
            gameCam.position.y = (float) 1.05;
        }else{
            gameCam.position.y = player.b2body.getPosition().y;
        }
        //Atualiza a camera do jogo conforme ele muda
        gameCam.update();
        verificarNpc();
        //Dizer ao nosso renderizador para desenhar apenas o que a nossa camera pode ver em nosso mundo de jogo.
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();


        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        
        //colocar textura no jogo
        //game.batch.draw(new Texture(""),0,0);
        
        for (Professor professor : professores) {
			// System.out.println(professor.getId());
			professor.draw(game.batch);

		}
		for (Professor professor : professores) {
			if (professor.isInteracao()) {
				float tamanhoTelaW = gameCam.viewportWidth;
				float aux = player.b2body.getPosition().x + (tamanhoTelaW / 2 - (professor.getTPreview().getWidth()));
				// System.out.println(aux);
				game.batch.draw(professor.getTPreview(), aux, 10);

				// professor.getFont().draw(game.batch,professor.getTextoChat(),
				// professor.b2body.getPosition().x,
				// professor.b2body.getPosition().y);
				// Parte de ensino teorico
				if (Gdx.input.isKeyPressed(Keys.X)) {
					// Conversando
					// Exibir conteudo na tela.;
					// System.out.println("ESTOU CONVERSANDO COM O NPC!!!");
					game.setScreen(ensinoTeorico.get(professor.getId()).getScreen());
					music.dispose();
					//this.dispose();
				}

			}
		}
        player.draw(game.batch);
		game.batch.end();
        
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }
    
    public TiledMap getMap(){
    	return map;
    }
    
    public World getWorld(){
    	return world;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    public Hud getHud(){ return hud;}

	public static int getCheck() {
		return check;
	}

	public static void setCheck(int check) {
		ScreenUs04.check = check;
	}
	public void criarProfessor() {
		String[] nomes = { "Angela", "André", "Gerson","Luiz Fernando","Eduardo Alves" };
		Vector2[] position = { 
				new Vector2((float) 5.16, (float)0.4),
				new Vector2((float)8.80,(float)0.3),
				new Vector2((float)13.3, (float)0.3),
				new Vector2((float)16.65, (float)0.3),
				new Vector2((float)20.80, (float)0.3) };
		for (int i = 0; i < nomes.length; i++) {
			Professor professor = new Professor(this, position[i].x, position[i].y);
			professor.setId(i);
			professor.setNome(nomes[i]);
			professor.setNivel(0);
			professor.setTPreview(new Texture("images/Professor-preview.png"));
			professor.setTChat(new Texture("images/Chat01.png"));
			professor.setTextoChat("OLÁ TUDO BEM ? EU IREI ENSINA-LO AS VARIAVEIS BASICAS - MODULO I.");
			professor.setInteracao(false);
			professores.add(professor);
		}
	}

	// Metodo para verificar se o Usuario esta Perto de algum Npc.
	private void verificarNpc() {
		for (Professor professor : professores) {
			if ((player.b2body.getPosition().x >= professor.b2body.getPosition().x - 0.4)
					&& (player.b2body.getPosition().x <= (professor.b2body.getPosition().x +0.4))) {
				// System.out.println(professor.getNome());
				int NivelPlayer = 1;
				if (NivelPlayer >= professor.getNivel()) {
					System.out.println("VOCÊ TEM ACESSO A ESSE NPC!");
					professor.setInteracao(true);

				} else {
					professor.setInteracao(false);
					System.out.println("VOCÊ NÃO TEM ACESSO A ESSE NPC!");
				}
			} else {
				professor.setInteracao(false);
			}
		}
	}

	private void criarEnsinos() {
		int[] ids = { 1, 2, 3, 4, 5 };
		String[] texto = { "TEXTO DO NPC 01", "TEXTO DO NPC 02", "TEXTO DO NPC 03" , "TEXTO DO NPC 04" , "TEXTO DO NPC 05" };
		Screen[] tela = { new ScreenUs03(game), new ScreenUs04(game), new ScreenUs05(game), new ScreenUs07(game), new ScreenUs10(game) };
		for (int i = 0; i < ids.length; i++) {
			EnsinoTeorico teorico = new EnsinoTeorico(game);
			teorico.setId(i);
			teorico.setTexto(texto[i]);
			teorico.setNpc_id(ids[i]);
			teorico.setScreen(tela[i]);
			ensinoTeorico.add(teorico);
		}
	}
    
}
