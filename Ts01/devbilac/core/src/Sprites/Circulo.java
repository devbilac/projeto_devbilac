package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import enums.Tipo;

public class Circulo extends Objeto{

	private boolean resposta;
	private String msg;
	private float msgX;
	private float msgY;
	private BitmapFont font;
	private int cor;
	
	public Circulo(String img, Tipo tipo, float x, float y) {
		super(img, tipo, x, y);
		this.font = new BitmapFont(Gdx.files.internal("assets\\fonts\\font.fnt"), false);
		setMsg("Teste 001");
		congfigPosMsgX();
		congfigPosMsgY();
	}
	
	public void congfigPosMsgX() {
		this.msgX = getX() + (getImg().getWidth() / 2) / (msg.length() / 2);
	}
	
	public void congfigPosMsgY() {
		this.msgY = getY() + (getImg().getHeight() / 2);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isResposta() {
		return resposta;
	}

	public void setResposta(boolean resposta) {
		this.resposta = resposta;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public float getMsgX() {
		return msgX;
	}

	public float getMsgY() {
		return msgY;
	}

	public int getCor() {
		return cor;
	}

	public void setCor(int cor) {
		this.cor = cor;
	}
	
}
