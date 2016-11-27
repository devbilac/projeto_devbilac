package Screens;

import java.sql.SQLException;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.DevBilac;

import Screens.Conexao_BD;
import java.sql.PreparedStatement;  

public class CriarConta implements Screen {
	
    

	private Stage stage ;
	private TextureAtlas atlas ;
	private Skin skin ;
	private TextButton  btnsair,btngravar ;
	private TextField  txfusername,txfpassword,txfra;
	private Table table;
	private Label heading;
	Conexao_BD  conexao;
	 
	private DevBilac game;
	public CriarConta(DevBilac game){
		this.game = game;
	}
	@Override
	public void show() {
		stage =new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),atlas);
		skin.addRegions(atlas);
		table = new Table(skin);
		table.setFillParent(true);
		
		txfusername= new  TextField("",skin,"default");
		txfusername.setPosition(500,290);
		txfusername.setSize(300,40);
		
		
		txfra= new  TextField("",skin,"default");
    	txfra.setPosition(500,190);
		txfra.setSize(300,40);
		
				
		
		txfpassword=new TextField(" ",skin,"default"); 
		txfpassword.setPosition(500,100);
		txfpassword.setSize(300,40);
		txfpassword.setPasswordCharacter('*');
		txfpassword.setText("");
		txfpassword.setPasswordMode(true);
		
		
		
		btngravar= new TextButton("Criar",skin,"small"); 
	    btngravar.setSize(130, 40);//170,40 size do btn
		btngravar.setPosition(500,50);
		btngravar.addListener(new ClickListener(){//btnlogin 500,300 //password 500,200 //username 500 ,250
			//public void touchUp(InputEvent e,float x,float y,int point,int button){
			@Override
			@SuppressWarnings("static-access")
			public void clicked(InputEvent event,float x ,float y){ 	
				
				
				 try{         // Estrutura para inserção de dados
					 
					 conexao = new Conexao_BD();
					 conexao.getConexao();
					 
			            String sql= "INSERT INTO users (ra,nome,senha) VALUES (?,?,?)";
			           
						PreparedStatement ps = Conexao_BD.connection.prepareStatement(sql);
			            ps.setInt(1,Integer.parseInt(txfra.getText()));
			            ps.setString(2,txfusername.getText());
			            ps.setString(3,txfpassword.getText());
			           
			            
			           
			            ps.executeUpdate();
			           
			         System.out.println(" Cadastrado !");
		             game.setScreen(new Login(game));
			    }                                               
			       catch(SQLException erro  ){
			    	   btngravar.setText(""+erro);
			    	 
			    } catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		});
		
    	btnsair= new TextButton("Voltar",skin,"small"); 
		btnsair.setSize(130, 40);
		btnsair.setPosition(670,50);
		btnsair.addListener(new ClickListener(){//btnlogin 500,300 //password 500,200 //username 500 ,250
			@Override
			public void touchUp(InputEvent e,float x,float y,int point,int button){
	             game.setScreen(new Login(game));
		}
		});
		
		
		table.add(new Label("CRIAR CONTA", skin, "default")).colspan(3).expandX().spaceBottom(65).row();
		table.add(new Label("Digite seu nome:", skin, "default")).colspan(3).expandX().spaceBottom(65).row();
		table.add(new Label("Digite seu RA:", skin, "default")).colspan(3).expandX().spaceBottom(55).row();
		table.add(new Label("Digitar Senha:", skin, "default")).colspan(3).expandX().row();
		
		stage.addActor(table); 
		stage.addActor(btngravar);
		stage.addActor(txfusername);
		stage.addActor(txfpassword);
		stage.addActor(txfra);
		stage.addActor(btnsair);
		
		
		
			}
		
		
	public void btnloginClicked() throws Exception{
		
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
		table.invalidateHierarchy();
		table.setSize(width,height);
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
		((ApplicationListener) txfusername).dispose();
		((ApplicationListener) btngravar).dispose();
		((ApplicationListener) heading).dispose();
		
		
	}

}
