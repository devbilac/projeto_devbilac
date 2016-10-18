package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;

public class Botao {
	
	private Texture img;
	private String text;
	private Vector3 pos;
	private Vector3 posText;
	private BitmapFont font;

	
	public Botao(float x, float y, String text, Texture img) {
		this.font = new BitmapFont(Gdx.files.internal("images\\fonts\\fontBotao.fnt"), false);
		this.pos = new Vector3(x, y, 0);
		this.text = text;
		this.img = img;
		this.configPostext();
	}
	
	private void configPostext() {
		float x = getPos().x + (getImg().getWidth() / 2) - 10;
		float y = getPos().y + (getImg().getHeight() / 2) + 10;
		this.posText = new Vector3(x, y, 0);		
	}

	public Texture getImg() {
		return img;
	}

	public void setImg(Texture img) {
		this.img = img;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Vector3 getPos() {
		return pos;
	}

	public void setPos(Vector3 pos) {
		this.pos = pos;
	}

	public Vector3 getPosText() {
		return posText;
	}

	public void setPosText(Vector3 posText) {
		this.posText = posText;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

}
