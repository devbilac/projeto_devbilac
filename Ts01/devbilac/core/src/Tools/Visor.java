package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;

public class Visor {

	private Texture img;
	private String text;
	private Vector3 pos;
	private Vector3 posText;
	private BitmapFont font;

	
	public Visor(float x, float y) {
		this.font = new BitmapFont(Gdx.files.internal("images\\fonts\\fontVisor.fnt"), false);
		this.img = new Texture("images\\visor.png");
		this.text = "";
		this.pos = new Vector3(x, y, 0);
		this.configPostext();
	}
	
	private void configPostext() {
		float x = (getPos().x + getImg().getWidth() - 10); 
		float y = getPos().y + 40;
		this.posText = new Vector3(x, y, 0);
	}
	
	public void apaga() {
		if (getText().length() > 0) {
			setText(getText().substring(0, getText().length()-1));
			getPosText().x += 21;
		}
	}
	
	public void add(String text) {
		this.text += text;
		getPosText().x -= 21;
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
