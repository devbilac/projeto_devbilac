package Scenes;

import java.util.ArrayList;

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

import Dao.ConexaoAE;
import Dao.RankingDao;
import Model.Ranking;

public class HudRanking implements Disposable {
	@Override
	public void dispose() {
		stage.dispose();
	}

	public Stage stage;
	private Viewport viewport;
	
	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	Label Posicao;
	Label Fase;
	Label Pontuacao;
	String SPosicao = "Posição\n";
	String SFase = "Fase\n";
	String SPontuacao = "Pontuação\n";
	
	
	Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldLabel;
	Label marioLabel;
	
	public HudRanking(SpriteBatch sb){
		worldTimer = 300;
		timeCount = 0;
		score = 0;
		ConexaoAE CAE= new ConexaoAE();
	    
			try {
				
				RankingDao RankingDao = new RankingDao();
				//RankingDao.addLista(CAE.getArquivo()); //Insere os dados do arquivoExterno na lista Ranking
				//Metodo de Adicionar os Dados do Banco na lista do RankingDao.
				RankingDao.buscarRankingsBD();
				ArrayList<Model.Ranking> lista = RankingDao.getDado(); //Metodo que pega os dados armazenados no RankingDao
				//Metodo de Repetição para exibir os dados;
				int x = 1;
				for(Ranking lista1 : lista){
					
					//Usar o metodo de Ordenação para inserir a posição.
					SPosicao += x+"\n";
					SFase += lista1.getFase()+"\n";  
					SPontuacao += lista1.getPontuacao()+"\n";  
					x++;
		        }
				
			} catch (Exception e) {}
		
		
		
		viewport = new FitViewport(DevBilac.V_WIDHT,DevBilac.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport, sb);
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		
		timeLabel = new Label(SPosicao, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//Posição fixa
		//timeLabel.setBounds(0, 0, 200, 300);
		worldLabel = new Label(SFase, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		marioLabel = new Label(SPontuacao, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		table.add(timeLabel).expandX().padTop(10);
		table.add(worldLabel).expandX().padTop(10);
		table.add(marioLabel).expandX().padTop(10);
		table.row();
		stage.addActor(table);
		
	
	}
}
