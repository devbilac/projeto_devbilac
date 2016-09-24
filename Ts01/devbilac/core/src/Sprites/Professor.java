package Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
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
	
	public Professor(PlayScreen screen, float x, float y) {
		super(screen, x, y);
		frames = new Array<TextureRegion>();
		for(int i = 0;i<2;i++){
			frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"), i*16, 1, 16,16));
		}
		walkAnimation = new Animation(0.4f, frames);
		stateTime = 0;
		setBounds(getX(), getY(), 16 / DevBilac.PPM, 16 / DevBilac.PPM);
	}

	public void update(float dt){
		stateTime += dt;
		setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() /2);
		setRegion(walkAnimation.getKeyFrame(stateTime, true));
	}
	
	@Override
	protected void defineNpc() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(32 / DevBilac.PPM,32 / DevBilac.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(6 / DevBilac.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
	}

}
