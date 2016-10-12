package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;


public class Circulo extends Objeto{

	private boolean resposta;
	private String msg;
	private float msgX;
	private float msgY;
	private BitmapFont font;
	private int cor;
	
	public Circulo() {
		this.font = new BitmapFont(Gdx.files.internal("images\\font.fnt"), false);
	}
	
	public void update(float dt){
		setPosition(new Vector3(getPosition().x,getPosition().y -2,0));
	}
	public void setMensagem(String Mensagem){
		this.msg = Mensagem;
		setMsg(this.msg);
		congfigPosMsgX();
		congfigPosMsgY();
	}
	public void congfigPosMsgX() {
		this.msgX = this.getPosition().x + (this.getTexture().getWidth() / 2) / (msg.length() / 2);
	}
	
	public void congfigPosMsgY() {
		this.msgY = this.getPosition().y + (this.getTexture().getHeight() / 2);
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
