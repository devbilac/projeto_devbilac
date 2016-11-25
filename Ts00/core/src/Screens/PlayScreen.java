package Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
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
import Sprites.EnsinoTeorico;
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
	// Variaveis do Mapa
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	// Variaveis Box2d
	private World world;
	private Box2DDebugRenderer b2dr;
	// Sprites
	private Player player;
	ArrayList<Professor> professores = new ArrayList<Professor>();
	ArrayList<EnsinoTeorico> ensinoTeorico = new ArrayList<EnsinoTeorico>();

	public PlayScreen(DevBilac game) {
		atlas = new TextureAtlas("images/Mario_And_Enemies.pack");

		this.game = game;
		gamecam = new OrthographicCamera();
		// o 'Metodo' FitViewport faz o jogo ter a Altura 100% com espaçamentos
		// laterais.
		gamePort = new FitViewport(DevBilac.V_WIDTH / DevBilac.PPM2, DevBilac.V_HEIGHT / DevBilac.PPM2, gamecam);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		hud = new Hud(game.batch);

		maploader = new TmxMapLoader();
		map = maploader.load("images/level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / DevBilac.PPM2);

		// Alterar para mudar a Gravidade do mundo
		world = new World(new Vector2(0, -200), true);
		b2dr = new Box2DDebugRenderer();
		new B2WorldCreator(this);
		player = new Player(this);

		music = Gdx.audio.newMusic(Gdx.files.internal("audio/Theme01.mp3"));
		music.setLooping(true); // Looping da Musica
		music.setVolume(0.1f); // Volume da Musica 0.1f = 10%
		music.play(); // Play Musica ...
		criarProfessor();
		criarEnsinos();
	}

	public TextureAtlas getAtlas() {
		return atlas;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	// Metodo para receber as ações do teclado.
	public void handleInput(float dt) {
		if ((Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.SPACE) || Gdx.input.isKeyPressed(Keys.UP))
				&& player.b2body.getLinearVelocity().y == 0f) {
			player.b2body.applyLinearImpulse(new Vector2(0, 400), player.b2body.getWorldCenter(), true);
		}
		if ((Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))
				&& player.b2body.getLinearVelocity().x <= 100) {
			player.b2body.applyLinearImpulse(new Vector2(100, 0), player.b2body.getWorldCenter(), true);
		}
		if ((Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))
				&& player.b2body.getLinearVelocity().x >= -100) {
			player.b2body.applyLinearImpulse(new Vector2(-100, 0), player.b2body.getWorldCenter(), true);
		}
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			// Problema no Dispose();
			// dispose();
			/*
			 * if(!menu){ game.setScreen(new Ranking(game));
			 * System.out.println("Menu Verdadeiro"); menu = true; }else{
			 * game.setScreen(new Ranking(game));
			 * System.out.println("Menu Falso"); menu = false; }
			 */
			game.setScreen(new ScreenUs05(game));
			// dispose();
		}

	}

	public void update(float dt) {
		handleInput(dt);
		world.step(1 / 60f, 6, 2);
		player.update(dt);
		for (Professor professor : professores) {
			professor.update(dt);
		}
		gamecam.position.x = player.b2body.getPosition().x; // Camera segue a
															// posição do
															// personagem
		// inicio do mapa +2
		if (gamecam.position.x < 0) {
			gamecam.position.x = 0;
		}
		// fim do mapa -2
		if (gamecam.position.x > 3600) {
			gamecam.position.x = 3600;
		}
		gamecam.update();
		renderer.setView(gamecam);
		verificarNpc();
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();
		b2dr.render(world, gamecam.combined);
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		for (Professor professor : professores) {
			// System.out.println(professor.getId());
			professor.draw(game.batch);

		}
		for (Professor professor : professores) {
			if (professor.isInteracao()) {
				float tamanhoTelaW = gamecam.viewportWidth;
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
					game.setScreen(ensinoTeorico.get(professor.getId()).getTela());
					this.dispose();
				}

			}
		}
		game.batch.end();
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);

	}

	public TiledMap getMap() {
		return map;

	}

	public World getWorld() {
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
		this.map.dispose();
		this.renderer.dispose();
		this.world.dispose();
		this.b2dr.dispose();
		this.hud.dispose();
		this.music.stop();
	}

	public void criarProfessor() {
		String[] nomes = { "Angela", "André", "Gerson" };
		Vector2[] position = { new Vector2(20, 21), new Vector2(200, 21),
				new Vector2(400, 21) };
		for (int i = 0; i < nomes.length; i++) {
			Professor professor = new Professor(this, position[i].x, position[i].y);
			professor.setId(i);
			professor.setNome(nomes[i]);
			professor.setNivel(i);
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
			if ((player.b2body.getPosition().x >= professor.b2body.getPosition().x - 50)
					&& (player.b2body.getPosition().x <= (professor.b2body.getPosition().x + 50))) {
				// System.out.println(professor.getNome());
				int NivelPlayer = 1;
				if (NivelPlayer >= professor.getNivel()) {
					// System.out.println("VOCÊ TEM ACESSO A ESSE NPC!");
					professor.setInteracao(true);

				} else {
					professor.setInteracao(false);
					// System.out.println("VOCÊ NÃO TEM ACESSO A ESSE NPC!");
				}
			} else {
				professor.setInteracao(false);
			}
		}
	}

	private void criarEnsinos() {
		int[] ids = { 1, 2, 3 };
		String[] texto = { "TEXTO DO NPC 01", "TEXTO DO NPC 02", "TEXTO DO NPC 03" };
		Screen[] tela = { new ScreenUs03(game), new ScreenUs05(game), new ScreenUs07(game) };
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
