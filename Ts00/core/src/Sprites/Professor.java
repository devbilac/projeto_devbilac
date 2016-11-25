package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.DevBilac;

import Screens.PlayScreen;

public class Professor extends Npc {
	private float stateTime;
	private Animation walkAnimation;
	private Array<TextureRegion> frames;
	private String nome;
	private int id;
	private int nivel;
	private Texture TPreview;
	private Texture TChat;
	private String TextoChat;
	private BitmapFont font;
	private boolean interacao;

    private TextureAtlas atlas;
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public Professor(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		
		 Array<TextureRegion> frames = new Array<TextureRegion>();
	        //Animacao do boneco quando se move
	        atlas = new TextureAtlas("professor.pack");
	        for(int i = 1; i < 8; i++)
	        	frames.add(new TextureRegion(atlas.findRegion("left"), 0, 0, 80, 80));
	        walkAnimation = new Animation(0.5f, frames);
	        frames.clear();

	        setBounds(0, 0, 32 / DevBilac.PPM, 32 / DevBilac.PPM);
	    }

	public void update(float dt){
		stateTime += dt;
		setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() /2);
		setRegion(walkAnimation.getKeyFrame(stateTime, true));
	}
	
	@Override
	protected void defineNpc(float x, float y) {
		BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.StaticBody;

        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / DevBilac.PPM);
        fdef.filter.categoryBits = DevBilac.DEFAULT_BIT;
        fdef.filter.maskBits = DevBilac.DEFAULT_BIT | DevBilac.CHEST_BIT | DevBilac.ESCADA_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        CircleShape body = new CircleShape();
        body.setRadius(10 / DevBilac.PPM);
        //new Vector2(-9 / FluxoGame.PPM, 9 / FluxoGame.PPM), new Vector2(9 / FluxoGame.PPM, 9 / FluxoGame.PPM)
        fdef.shape = body;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("body");

		
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Texture getTPreview() {
		return TPreview;
	}

	public void setTPreview(Texture tPreview) {
		TPreview = tPreview;
	}

	public Texture getTChat() {
		return TChat;
	}

	public void setTChat(Texture tChat) {
		TChat = tChat;
	}

	public String getTextoChat() {
		return TextoChat;
	}

	public void setTextoChat(String textoChat) {
		TextoChat = textoChat;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public boolean isInteracao() {
		return interacao;
	}

	public void setInteracao(boolean interacao) {
		this.interacao = interacao;
	}

}
