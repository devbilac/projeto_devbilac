package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Recipiente {
	public int id;
	public Texture texture;
	public Vector3 position;
	private int tipo;
	private boolean Visivel;
	
	public int getTipo(){
		return tipo;
	}
	public void setTipo(int tipo){
		this.tipo = tipo;
	}
	public boolean isVisivel() {
		return Visivel;
	}
	public void setVisivel(boolean visivel) {
		Visivel = visivel;
	}

	public void setId(int id) {
		this.id = id;
	}
	public static final Texture defaultPlayer = new Texture("images/badlogic.jpg");

	public Recipiente() {
		texture = defaultPlayer;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public int getId() {
		return id;
	}
	

}