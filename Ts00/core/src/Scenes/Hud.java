package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;
//Classe com o Display da tela 'PlayScreen'.
public class Hud implements Disposable {
	@Override
	public void dispose() {
		stage.dispose();
		
	}

	public Stage stage;
	private Viewport viewport;
	
	private String nome;
	private float timeCount;
	private Integer ra;
	
	Label NomeValor;
	Label RAValor;
	Label NomeTitulo;
	Label levelLabel;
	Label worldLabel;
	Label RATitulo;
	
	public Hud(SpriteBatch sb){
		nome = "";
		ra = DevBilac.RA;
		timeCount = 0;
		
		//Camera do jogo.
		viewport = new FitViewport(DevBilac.V_WIDTH,DevBilac.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport, sb);
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		NomeValor = new Label(nome, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		RAValor = new Label(String.format("%06d", ra), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		NomeTitulo = new Label("Nome", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		worldLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		RATitulo = new Label("R.A.", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		table.add(RATitulo).expandX().padTop(10);
		table.add(worldLabel).expandX().padTop(10);
		table.add(NomeTitulo).expandX().padTop(10);
		table.row();
		table.add(RAValor).expandX();
		table.add(levelLabel).expandX();
		table.add(NomeValor).expandX();
		stage.addActor(table);
		
	
	}

	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
}
