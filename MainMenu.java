package com.mygdx.game.telas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.tween.ActorAccessor;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class MainMenu implements Screen {
	private Stage stage ;
	private Table table ;
	private TextButton buttonPlay,buttonExit,buttonContinueGame;
	private Label heading;
	private Skin skin ;
	private BitmapFont white,black,black32;
	private TextureAtlas atlas ;
	private TweenManager tweenManager;

	@Override
	public void show() {
		stage=new Stage();
		Gdx.input.setInputProcessor(stage);
		atlas =new TextureAtlas("ui/atlas.pack");		
		skin = new Skin (Gdx.files.internal("ui/menuSkin.json"),atlas);
		skin.addRegions(atlas);
		table = new Table (skin);
		table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		
		
		
		buttonExit=new TextButton("Sair ",skin);
		buttonExit.addListener(new ClickListener(){
			public void clicked(InputEvent event,float x ,float y){  //evento do botão Sair
				Gdx.app.exit();
			}
		});
		buttonExit.pad(15);
		buttonPlay= new  TextButton("Opções",skin);
		buttonPlay.addListener(new ClickListener(){
			public void clicked(InputEvent event,float x ,float y){  //evento do botão Novo Jogo 
				((Game)Gdx.app.getApplicationListener()).setScreen(new Login());
			}
			
		});
		buttonPlay.pad(15);
		buttonContinueGame= new  TextButton("Novo Jogo",skin);
		buttonContinueGame.addListener(new ClickListener(){
			public void clicked(InputEvent event,float x ,float y){  //evento do botão Sair
				((Game)Gdx.app.getApplicationListener()).setScreen(new LevelMenu());
			}
		});
		buttonContinueGame.pad(15);
		LabelStyle headingStyle = new LabelStyle(white,Color.WHITE);
		//criando o cabeçalho do menu
		
		heading=new Label("DEV BILAC GAME",skin);
		heading.setFontScale(2);
		heading.setColor(1,0,0,1);
		// juntando o menu em uma tabela
		table.add(heading);	
		table.getCell(heading).spaceBottom(100);
		table.row();
		table.add(buttonContinueGame);
		table.getCell(buttonContinueGame).spaceBottom(15);
		table.row();
		table.add(buttonPlay);
		table.getCell(buttonPlay).spaceBottom(15);
		table.row();
		table.add(buttonExit);
		
	//	table.debug();
		stage.addActor(table);
	//	table.debug();
		//criando animações do menu fade in
		//Timeline.createSequence().beginSequence()
		//.push(Tween.to(heading,ActorAccessor.RGB,5f).target(0,0,1))
	//	.push(Tween.to(heading,ActorAccessor.RGB,5f).target(0,1,0))
	//	.push(Tween.to(heading,ActorAccessor.RGB,5f).target(1,0,0))
	//	.push(Tween.to(heading,ActorAccessor.RGB,5f).target(0,1,1))
	//	.push(Tween.to(heading,ActorAccessor.RGB,5f).target(1,1,0))
	//	.push(Tween.to(heading,ActorAccessor.RGB,5f).target(1,0,1))
		//.push(Tween.to(heading,ActorAccessor.RGB,5f).target(1,1,1))
	//	.end().repeat(Tween.INFINITY,0).start(tweenManager);
		// criando animações dos botoes
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class,new ActorAccessor());
		Timeline.createSequence().beginSequence()
		.push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
		.push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
		.push(Tween.from(heading, ActorAccessor.ALPHA,.5F).target(0))
		.push(Tween.to(buttonPlay, ActorAccessor.ALPHA,.5F).target(1))
		.push(Tween.to(buttonExit, ActorAccessor.ALPHA,.5F).target(1))
		.end().start(tweenManager);
		//animação da tabela
		Tween.from(table,ActorAccessor.ALPHA,.5F).target(0).start(tweenManager);
		Tween.from(table,ActorAccessor.ALPHA,.5F).target(Gdx.graphics.getHeight()/8).start(tweenManager);
	//	tweenManager.update(Gdx.graphics.getDeltaTime());
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//table.drawDebug(stage); //acionar linhas da tabela 
		tweenManager.update(delta);
		stage.act(delta);
		stage.draw();
		
		
	}

	@Override
	public void resize(int width, int height) {
		table.invalidateHierarchy();
		table.setSize(width,height);
	//	invalidateHierarchy();
		
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
		white.dispose();
		black.dispose();
		black32.dispose();
		
	}
	

}
