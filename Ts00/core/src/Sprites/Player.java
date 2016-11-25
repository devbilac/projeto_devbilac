package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.DevBilac;

import Screens.PlayScreen;

public class Player extends Sprite{
	public enum State {FALLING, JUMPING, STANDING, RUNNING};
	public State currentState;
	public State previousState;
	
	public World world;
	public Body b2body;
	private TextureRegion playerStand;
	private Animation playerRun;
	private Animation playerJump;
	private float stateTimer;
	private boolean runningRight;
	
	public Player(PlayScreen screen){
		super(screen.getAtlas().findRegion("little_mario"));
		this.world = screen.getWorld();
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		
		Array<TextureRegion> frames = new Array<TextureRegion>();
		for(int i = 1; i<4; i++){
			frames.add(new TextureRegion(getTexture(),i*16,11, 16, 16));
		}
		playerRun = new Animation(0.1f, frames);
		frames.clear();
		for(int i = 4; i<6;i++){
			frames.add(new TextureRegion(getTexture(),i*16, 11, 16, 16));
		}
		playerJump = new Animation(0.1f,frames);
		playerStand = new TextureRegion(getTexture(), 1,11,16,16);
		definePlayer();
		setBounds(0,0,16 / DevBilac.PPM, 16 / DevBilac.PPM);
		setRegion(playerStand);
	}
	
	public void update(float dt){
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() /2);
		setRegion(getFrame(dt));
	}
	
	public TextureRegion getFrame(float dt) {
		currentState = getState();
		
		TextureRegion region;
		switch(currentState){
		case JUMPING:
			region = playerJump.getKeyFrame(stateTimer);
			break;
		case RUNNING:
			region = playerRun.getKeyFrame(stateTimer, true);
			break;
		case FALLING:
		case STANDING:
		default:
			region = playerStand;
			break;
		}
		if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
			region.flip(true, false);
			runningRight = false;
		}else if((b2body.getLinearVelocity().x>0 || runningRight) && region.isFlipX()){
			region.flip(true,false);
			runningRight = true;
		}
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
		
	}
	public State getState(){
		if(b2body.getLinearVelocity().y >0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING)){
			return State.JUMPING;
		}else if(b2body.getLinearVelocity().y < 0){
			return State.FALLING;
		}else if(b2body.getLinearVelocity().x != 0){
			return State.RUNNING;
		}else{
			return State.STANDING;
		}
	}
	public void definePlayer(){
		BodyDef bdef = new BodyDef();
		bdef.position.set(32 / DevBilac.PPM,32 / DevBilac.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10 / DevBilac.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
		
	}
}
