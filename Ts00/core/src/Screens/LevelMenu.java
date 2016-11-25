package Screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelMenu implements Screen {	
	private Stage stage ;
	private Table table ;
	private TextureAtlas atlas ;
	private Skin skin ;
	private List list ;
	private ScrollPane scrollPane;
	private TextButton jogar , voltar;
	private ArrayList fase,Fases;

	@Override
	public void show() {
		stage =new Stage ();
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),atlas);
		skin.addRegions(atlas);
		
		table = new  Table(skin);
		table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		table.debug();
		ArrayList<String> fase =  new ArrayList<String>();
		fase.add("Fase 1");
		fase.add("Fase 2");
		fase.add("Fase 3");
		fase.add("Fase 4");
		list = new List (skin);			
		scrollPane =new ScrollPane(list,skin);
		jogar = new TextButton("Jogar",skin);
		jogar.pad(15);
		voltar = new TextButton("voltar",skin);
		voltar.addListener(new ClickListener(){
			@Override
			public void clicked (InputEvent event, float x,float y){
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
			}
		});
		voltar.pad(5);
		table.add().width(table.getWidth()/3);
		table.add("SELECIONAR FASE").width(table.getWidth()/3).center();
		table.add().width(table.getWidth()/3).row();
		table.add(scrollPane).expandY();
		table.add(jogar);
		table.add(voltar).bottom().right();
		stage.addActor(table);
		
		
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		stage.dispose();
		atlas.dispose();
		skin.dispose();
		
	}

}
