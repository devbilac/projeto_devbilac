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
public class HudUs03 implements Disposable {
	@Override
	public void dispose() {
		stage.dispose();
		
	}

	public Stage stage;
	private Viewport viewport;
	
	private Integer worldTimer;
	private float timeCount;
	private int score;
	
	public Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldLabel;
	Label pontosLabel;
	
	public int getScore() {
		return this.score;
	}
	public void setScore(int newS) {
		int old = this.score;
		this.score = old+newS;
		scoreLabel.setText(String.format("%01d", this.score));
	}
	
	public int getTimer(){
		return worldTimer;
	}
	
	public void setTimer(int worldTimer){
		this.worldTimer = worldTimer;
		countdownLabel.setText(String.format("%02d", this.worldTimer));
	}

	public HudUs03(SpriteBatch sb){
		
		//Camera do jogo.
		viewport = new FitViewport(DevBilac.V_WIDHT,DevBilac.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport, sb);
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		pontosLabel = new Label("Pontos", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		scoreLabel = new Label(String.format("%01d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//Worl e Level, estão nulos para ficar um espaço vazio na label.
		//Futuramente devemos alterar o uso da Label por outra função da LibGdx.
		worldLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timeLabel = new Label("Segundos", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		countdownLabel = new Label(String.format("%02d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		table.add(pontosLabel).expandX().padTop(5);
		table.add(worldLabel).expandX().padTop(5);
		table.add(timeLabel).expandX().padTop(5);
		table.row();
		table.add(scoreLabel).expandX();
		table.add(levelLabel).expandX();
		table.add(countdownLabel).expandX();
		stage.addActor(table);
		
	
	}
	
	public void update(float dt){
		timeCount += dt;
		if(timeCount >= 1){
			if(worldTimer > 0){
				worldTimer--;
				countdownLabel.setText(String.format("%02d", worldTimer));
				timeCount = 0;
			}
		}
	}
}
