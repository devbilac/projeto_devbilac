package entities;

import com.badlogic.gdx.graphics.Texture;

import enums.Tipo;

public class Objeto {
	private Texture img;
	private Tipo tipo;
	private boolean ativo;
	private float x, y;
	
	public Objeto(String img, Tipo tipo, float x, float y) {
		setImg(img);
		setTipo(tipo);
		setAtivo(true);
		setX(x);
		setY(y);
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void desativa() {
		this.ativo = false;
	}

	public Texture getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = new Texture(img);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
