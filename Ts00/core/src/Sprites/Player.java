package Sprites;

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
import Screens.ScreenUs04;


public class Player extends Sprite {
    public enum State { FALLING, JUMPING, STANDING, RUNNING, DEAD };
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion devbStand;
    private TextureRegion devbDead;
    private Animation devbRun;
    private Animation devbJump;
    private float stateTimer;
    private boolean runningRight;
    private boolean devbIsDead;

    public Player(PlayScreen screen){
        super(screen.getAtlas().findRegion("right"));
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        //Animacao do boneco quando se move
        for(int i = 1; i < 8; i++)
        	frames.add(new TextureRegion(screen.getAtlas().findRegion("left"), i*88, 91, 80, 80));
        devbRun = new Animation(0.1f, frames);
        frames.clear();

        //Devb Pulando
        for(int i = 1; i< 7; i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("left"), i*86, 270, 80,80));
        devbJump = new Animation(0.1f, frames);

        
        devbDead = new TextureRegion(screen.getAtlas().findRegion("left"), 250, 270, 80, 80);
        
        devbStand = new TextureRegion(getTexture(), 5, 91, 80,80);

        defineDevb();
        setBounds(0, 0, 32 / DevBilac.PPM, 32 / DevBilac.PPM);
        setRegion(devbStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() /2, b2body.getPosition().y - getHeight() /2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch (currentState){

            case JUMPING:
                region = devbJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = devbRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
                default:
                    region = devbStand;
                    break;
        }
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return  region;
    }

    public State getState(){
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y<0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    public void defineDevb(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / DevBilac.PPM, 32 / DevBilac.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;

        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10 / DevBilac.PPM);
        fdef.filter.categoryBits = DevBilac.DEVB_BIT;
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
}
