package Screens;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;
import Sprites.Player;


public class ScreenUs10 implements Screen {
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera,gamecam;
	private Player player;
	private TextureAtlas playerAtlas;
	private ShapeRenderer sr;
	private Array<StaticTiledMapTile> frameTiles;
	private TextureAtlas atlas;
	private DevBilac game; 
	private Viewport gamePort;
	@Override
	public void show() {
		map = new TmxMapLoader().load("mapas/fase_vetores_matrizes.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
	//	player=new Player(new Sprite(new Texture("img/player.png"));
       // atlas = new TextureAtlas("images/Mario_And_Enemies.pack");
		
		this.game = game;
		gamecam = new OrthographicCamera();
		//o 'Metodo' FitViewport faz o jogo ter a Altura 100% com espaçamentos laterais.
		gamePort = new FitViewport(DevBilac.V_WIDTH / DevBilac.PPM,DevBilac.V_HEIGHT /DevBilac.PPM,gamecam);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() /2,0);
		
		 

			}

	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
		renderer.setView(camera);
		renderer.render();
        renderer.getBatch().begin();
		player.draw(renderer.getBatch());
		renderer.getBatch().end();
		
			}


	



	@Override
	public void resize(int width, int height) {
		camera.viewportWidth=width ;///1.5f;
		camera.viewportHeight=height;///1.5f;
     	camera.update();
		
		
		
	}
	
	public void update(float delta){
		player.update(delta);	
		//gamecam.position.x = player.b2body.getPosition().x; //Camera segue a posição do personagem
		gamecam.update();
		renderer.setView(gamecam);
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
		dispose();
		
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player.getTexture().dispose();        
	}

}
