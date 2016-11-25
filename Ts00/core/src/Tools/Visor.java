package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;

public class Visor {

	private Texture img;
	private String textResposta = "";
	private String textPergunta;
	private String textIgual = "=";
	private Vector3 pos;
	private Vector3 posTextResposta;
	private Vector3 posTextPergunta;
	private Vector3 posTextIgual;
	private BitmapFont font;

	
	public Visor(float x, float y) {
		this.font = new BitmapFont(Gdx.files.internal("fonts\\fontVisor.fnt"), false);
		this.img = new Texture("images\\visor.png");
		this.pos = new Vector3(x, y, 0);
		this.configPostextResposta();
		this.configPostextPergunta();
		this.configPostextIgual();
	}
	
	private void configPostextResposta() {
		float x = (getPos().x + getImg().getWidth() - 10); 
		float y = getPos().y + 40;
		this.posTextResposta = new Vector3(x, y, 0);
	}
	
	private void configPostextPergunta() {
		float x = getPos().x + 10;
		float y = getPos().y + 40;
		this.posTextPergunta = new Vector3(x, y, 0);
	}
	
	private void configPostextIgual() {
		float x = getPos().x + (getImg().getWidth() /2);
		float y = getPos().y + 40;
		this.posTextIgual = new Vector3(x, y, 0);
	}
	public void apagaResposta() {
		if (getTextResposta().length() > 0) {
			if (getTextResposta().length() == 1) {
				this.textResposta = "";
				configPostextResposta();
			}else{
			setTextResposta(getTextResposta().substring(0, getTextResposta().length()-1));
			getPosTextResposta().x += 21;
			}
		}
		
	}
	public void apagaPergunta() {
		/*if (getText().length() > 0) {
			setText(getText().substring(0, getText().length()-1));
			getPosText().x += 21;
		}*/
		this.textPergunta = "";
		configPostextPergunta();
	}
	
	public void add(String text) {
		if(getTextResposta().length() <3){
			this.textResposta += text;
			getPosTextResposta().x -= 21;
		}
	}

	public Texture getImg() {
		return img;
	}

	public void setImg(Texture img) {
		this.img = img;
	}

	public String getTextResposta() {
		return textResposta;
	}

	public void setTextResposta(String textResposta) {
		this.textResposta = textResposta;
	}

	public String getTextPergunta() {
		return textPergunta;
	}

	public void setTextPergunta(String textPergunta) {
		this.textPergunta = textPergunta;
	}

	public String getTextIgual() {
		return textIgual;
	}

	public void setTextIgual(String textIgual) {
		this.textIgual = textIgual;
	}

	public Vector3 getPos() {
		return pos;
	}

	public void setPos(Vector3 pos) {
		this.pos = pos;
	}

	public Vector3 getPosTextResposta() {
		return posTextResposta;
	}

	public void setPosTextResposta(Vector3 posTextResposta) {
		this.posTextResposta = posTextResposta;
	}

	public Vector3 getPosTextPergunta() {
		return posTextPergunta;
	}

	public void setPosTextPergunta(Vector3 posTextPergunta) {
		this.posTextPergunta = posTextPergunta;
	}

	public Vector3 getPosTextIgual() {
		return posTextIgual;
	}

	public void setPosTextIgual(Vector3 posTextIgual) {
		this.posTextIgual = posTextIgual;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

}
