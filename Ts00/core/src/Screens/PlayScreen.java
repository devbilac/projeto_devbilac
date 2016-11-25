package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    //
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

       /* music = DevBilac.manager.get("audio/music/Fluxogame.ogg", Music.class);
        music.setLooping(true);
        music.play();*/
    }

    public TextureAtlas getAtlas(){return atlas;}

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        //Configurando a tecla do movimento do Devb, a velocidade e o impulso do pulo.
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <= 3.25f){
        	
            player.b2body.applyLinearImpulse(new Vector2(0, 3.25f), player.b2body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <=1.2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >=-1.2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
    }

    public void update(float dt){
    	//Comandos input do usuario
        handleInput(dt);

        //Um passo na simulacao fisica(60 vezes por segundos)
        world.step(1/60f, 6, 2);

        player.update(dt);
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
		manager.dispose();
    }

    public Hud getHud(){ return hud;}

	public static int getCheck() {
		return check;
	}

	public static void setCheck(int check) {
		ScreenUs04.check = check;
	}
    
    
}
