package Sprites;

import com.badlogic.gdx.Screen;
import com.mygdx.game.DevBilac;

import Screens.ScreenTeoria;

public class EnsinoTeorico {
	private int id;
	private String texto;
	private int npc_id;
	private Screen tela;
	private DevBilac game;
	private Screen screen;
	
	public EnsinoTeorico(DevBilac game){
		this.game = game;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getNpc_id() {
		return npc_id;
	}
	public void setNpc_id(int npc_id) {
		this.npc_id = npc_id;
	}

	public Screen getTela() {
		this.tela = new ScreenTeoria(game,texto,screen);
		return tela;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
	
}
