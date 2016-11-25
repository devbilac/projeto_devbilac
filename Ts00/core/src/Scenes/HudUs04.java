package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DevBilac;

import com.badlogic.gdx.scenes.scene2d.Stage;


public class HudUs04 implements Disposable{
	//Scene2D.ui Stage e sua propria viewport para HUD
    public Stage stage;
    private Viewport viewport;

    //Variaveis de tempo e pontuacao 
    private static Integer worldTimer;
    private float timeCount;
    private static Integer score;

    //Scene2D widgets
    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label fluxoLabel;

    public HudUs04(SpriteBatch sb){
    	//Definindo as variaveis
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        //Configurando a HUD usando uma nova camera separada da gamecam do jogo
        //Definindo o Stage usando o viewport e os spritesbatch do jogo
        viewport = new FitViewport(DevBilac.V_WIDTH, DevBilac.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //Definindo uma table para organizar a HUD
        Table table = new Table();
        //Alinhamento no topo
        table.top();
        //fazer a tabela preencher toda a tela
        table.setFillParent(true);
        
        //Definindo as labels usando String, formatando o tipo de fonte e cor
        countdownLabel = new Label(String.format("%03d", worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("DevBilac",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fluxoLabel = new Label("DEVB",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //Adicionando as labels na tabela com espacamento e expandindo de acordo com expandX
        table.add(fluxoLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        //segunda linha da tabela
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();
        
        //add a tabela na Stage
        stage.addActor(table);
    }
    
    public void update(float dt){
    	timeCount += dt;
    	if(timeCount >= 1){
    		worldTimer--;
    		countdownLabel.setText(String.format("%03d", worldTimer));
    		timeCount = 0;
    	}
    }
    
   public static void addScore(int value){
    	score += value;
    	score += worldTimer;
    	scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
