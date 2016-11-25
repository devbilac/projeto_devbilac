package Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Exception;

import Screens.Conexao_BD;

public class Login implements Screen{
	private Stage stage ;
	private TextureAtlas atlas ;
	private Skin skin ;
	private TextButton btnlogar,btnnovaconta,btnsair ;
	private Table table;
	private TextField  txfpassword,txfra;
	private OrthographicCamera camera;
	Conexao_BD  conexao;
	@Override
	public void show() {
		stage =new Stage();
		Gdx.input.setInputProcessor(stage);
		camera= new OrthographicCamera(); 
		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),atlas);
		skin.addRegions(atlas);
		table = new Table(skin);
		table.setFillParent(true);
		
			
		txfra= new  TextField("",skin,"default");
    	txfra.setPosition(500,300);// anterior 500,240
		txfra.setSize(300,40);
		
				
		
		txfpassword=new TextField(" ",skin,"default"); 
		txfpassword.setPosition(500,200);// anterior 500,150
		txfpassword.setSize(300,40);
		txfpassword.setPasswordCharacter('*');
		txfpassword.setText("");
		txfpassword.setPasswordMode(true);
		
		btnlogar= new TextButton("Entrar",skin,"small"); 
		btnlogar.setSize(130, 40);//130,40 size do btn
		btnlogar.setPosition(440,150);
		btnlogar.addListener(new ClickListener(){//btnlogin 500,300 //password 500,200 //username 500 ,250
			//public void touchUp(InputEvent e,float x,float y,int point,int button){
			
			@Override
			public void clicked(InputEvent event,float x ,float y){ 
				
				 try {
					 					
					 conexao = new Conexao_BD();
					 conexao.getConexao();
			          String  sql = "SELECT ra, senha FROM users WHERE ra=? and senha=?";
			            @SuppressWarnings("static-access")
						PreparedStatement ps = Conexao_BD.connection.prepareStatement(sql);
			            ps.setInt(1,Integer.parseInt(txfra.getText()));
			            ps.setString(2,txfpassword.getText());
			            ResultSet rs;
			            rs = ps.executeQuery();
			            if (rs.next()) {
			                rs.getString("ra");
			                rs.getString("senha");
			                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu()); 
			                
			            } else {
			            	 btnlogar.setText("Acesso Negado !" );
			            	 ps.close();
			               
			            }
			        } catch (SQLException ex) {
			           btnlogar.setText(""+ ex);
			        } catch (Exception e) {
			        	 btnlogar.setText(""+ e);
						e.printStackTrace();
					}
			        
			    
				
				
				
			}});
		
		
		btnnovaconta =new TextButton("Nova Conta",skin,"small");
		btnnovaconta.setSize(130, 40);
		btnnovaconta.setPosition(590,150);
		btnnovaconta.addListener(new ClickListener(){//btnlogin 500,300 //password 500,200 //username 500 ,250
			//public void touchUp(InputEvent e,float x,float y,int point,int button){
			@Override
			@SuppressWarnings("static-access")
			public void clicked(InputEvent event,float x ,float y){ 
				((Game)Gdx.app.getApplicationListener()).setScreen(new CriarConta());
				
			}});
		
		
		btnsair =new TextButton("Sair",skin,"small");
		btnsair.setSize(130, 40);
		btnsair.setPosition(740,150);
		btnsair.addListener(new ClickListener(){//btnlogin 500,300 //password 500,200 //username 500 ,250
			@Override
			public void touchUp(InputEvent e,float x,float y,int point,int button){
			
				Gdx.app.exit();
		}
		});
		
		
		
		table.add(new Label("LOGIN", skin, "default")).colspan(3).expandX().spaceBottom(65).row();
		table.add(new Label("Digite seu RA:", skin, "default")).colspan(3).expandX().spaceBottom(65).row();
		table.add(new Label("Digitar Senha:", skin, "default")).colspan(3).expandX().spaceBottom(55).row();
	//	table.add(new Label("Digitar Senha:", skin, "default")).colspan(3).expandX().row();
		
		stage.addActor(table); 
		//stage.addActor(btngravar);
	//	stage.addActor(txfusername);
		stage.addActor(txfpassword);
		stage.addActor(txfra);
		stage.addActor(btnlogar);
		stage.addActor(btnnovaconta);
		stage.addActor(btnsair);
		
		
		
	}


		
		
	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);			
		stage.act(delta);
		stage.draw();
		camera.update();
		
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
		((ApplicationListener) btnlogar).dispose();
		
		
	}

}
