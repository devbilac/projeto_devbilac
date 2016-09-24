package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;

import Scenes.Hud;
import Sprites.Player;
import Sprites.Professor;
import Tools.B2WorldCreator;

public class PlayScreen implements Screen {
	private DevBilac game;
	private TextureAtlas atlas;
	private Ranking Ranking;
	private Music music;
	private boolean menu = false;
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	//Variaveis do Mapa
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	//Variaveis Box2d
	private World world;
	private Box2DDebugRenderer b2dr;
	//Sprites
	private Player player;
	private Professor professor;
	
	public PlayScreen(DevBilac game){
		atlas = new TextureAtlas("images/Mario_And_Enemies.pack");
		
		this.game = game;
		gamecam = new OrthographicCamera();
		//o 'Metodo' FitViewport faz o jogo ter a Altura 100% com espaçamentos laterais.
		gamePort = new FitViewport(DevBilac.V_WIDHT / DevBilac.PPM,DevBilac.V_HEIGHT / DevBilac.PPM,gamecam);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() /2,0);
		hud = new Hud(game.batch);
		
		maploader = new TmxMapLoader();
		map = maploader.load("images/level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / DevBilac.PPM);
		
		//Alterar para mudar a Gravidade do mundo
		world = new World(new Vector2(0,-200 / DevBilac.PPM), true);
		b2dr = new Box2DDebugRenderer();
		new B2WorldCreator(this);
		player = new Player(this);
		
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/Theme01.mp3"));
		music.setLooping(true); // Looping da Musica
		music.setVolume(0.1f); // Volume da Musica 0.1f = 10%
		music.play(); // Play Musica ...
		professor = new Professor(this, 32, 32f);
		
	}
	
	public TextureAtlas getAtlas(){
		return atlas;
	}
	
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	//Metodo para receber as ações do teclado.
	public void handleInput(float dt){
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isKeyPressed(Keys.UP)){
			player.b2body.applyLinearImpulse(new Vector2(0,0.1f), player.b2body.getWorldCenter(), true);
			
		}
            
        if ((Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        if ((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) && player.b2body.getLinearVelocity().x >= -2)
        	player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
        	//Problema no Dispose();
        	//dispose();
        	/*if(!menu){
        		game.setScreen(new Ranking(game));
        		System.out.println("Menu Verdadeiro");
        		menu = true;
        	}else{
        		game.setScreen(new Ranking(game));
        		System.out.println("Menu Falso");
        		menu = false;
        	}*/
        	System.out.println("Play ESQ");
        }
        	
	}
	
	public void update(float dt){
		handleInput(dt);
		world.step(1/60f, 6, 2);
		player.update(dt);
		professor.update(dt);
		gamecam.position.x = player.b2body.getPosition().x; //Camera segue a posição do personagem
		gamecam.update();
		renderer.setView(gamecam);
	}
	
	
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();
		b2dr.render(world,gamecam.combined);
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		professor.draw(game.batch);
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
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
	}

}
