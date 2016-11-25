package Sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;


public class PlayerUs10 extends Sprite implements InputProcessor  {
/** Movimento de velocidade */
	
	  
	private Vector2 velocity= new Vector2();
	
	private float speed = 60 * 2, gravity = 60 * 1.8f, animationTime = 0, increment;
	
	private TiledMapTileLayer collisionLayer;
	private boolean canJump;
	private String blockedKey= "blocked";
	private Animation still, left, right;
	
	

	
	
	public PlayerUs10(Sprite sprite ,TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.collisionLayer=collisionLayer;
		// TODO Auto-generated constructor stub
	}

	public void draw (SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	public void update (float delta){
		
			// Aplicando a gravidade 
		  	velocity.y-= gravity * delta;
		  	
		 if(velocity.y>speed)
				velocity.y= speed;
		 
			else if(velocity.y< speed)
				velocity.y=-speed;
			
			
		
		
			//Salvando a posição antiga
		
		
	    float oldX = getX(), oldY = getY(),tileWidth=collisionLayer.getTileWidth(),tileHeight=collisionLayer.getTileHeight();
			boolean collisionX = false,collisionY = false;
			
			
			//Movendo no eixo x
		
			setX(getX() + velocity.x * delta);
			
			//setX(getX()+velocity.x * delta);
			// calculate the increment for step in #collidesLeft() and #collidesRight()
		//	increment = collisionLayer.getTileWidth();
		//	increment = getWidth() < increment ? getWidth() / 2 : increment / 2;
			
			if(velocity.x < 0){ 
				//superior esquerdo
				collisionX= collisionLayer.getCell((int)(getX()/tileWidth),(int)(getY()+getHeight()))
						.getTile().getProperties().containsKey("blocked");
				//canJump = collisionY = collidesBottom1();
				
				// meio esquerdo 
				if(!collisionX)
				collisionX = collisionLayer.getCell((int)(getX()/tileWidth),(int)((getY()+getHeight()/2)/tileHeight))
						.getTile().getProperties().containsKey("blocked");
				// inferior esquerdo 
				if(!collisionX)
				collisionX= collisionLayer.getCell((int)(getX()/tileWidth),(int)(getY()/tileHeight)).getTile().getProperties().containsKey("blocked");
				
			
			}else if(velocity.x > 0){ 
			//		collisionY = collidesTop1();
					// superior direito
				
			//		collisionX = collisionLayer.getCell((int)((getX()+getWidth())/tileWidth),(int)((getY()+getHeight()/tileHeight)).getTile().getProperties().containsKey("blocked"));
			
			
					//meio direito
			    if(!collisionX)
					collisionX = collisionLayer.getCell((int)((getX()+getWidth())/tileWidth),(int) ((getY()+getWidth()/ 2 ) /tileHeight)).getTile().getProperties().containsKey("blocked");
					
					//inferior direito 
			    if(!collisionX)
			    	collisionX = collisionLayer.getCell((int)((getX()+getWidth())/tileWidth),(int) (getY() /tileHeight)).getTile().getProperties().containsKey("blocked");
					    			   
			    }
			    //movendo no eixo y
			setY(getY()+ velocity.y * delta);
			if(velocity.y<0){
				// inferior esquerdo 
				collisionY =collisionLayer.getCell((int)(getX()/tileWidth),(int)(getY()/tileHeight)).getTile().getProperties().containsKey("blocked");
				//
			}
			    
	// reação na colisão do eixo y
			//	if(collisionY) {
			//		setY(oldY);
			//		velocity.y = 0;
			//	}

				// ataualização da animação do personagem 
			//	animationTime += delta;
			//	setRegion(velocity.x < 0 ? left.getKeyFrame(animationTime) : velocity.x > 0 ? right.getKeyFrame(animationTime) : still.getKeyFrame(animationTime));
			}

		private	boolean isCellBlocked1(float x, float y) {
				com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
				return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
			}

			public boolean collidesRight1() {
				for(float step = 0; step <= getHeight(); step += increment)
					if(isCellBlocked1(getX() + getWidth(), getY() + step))
						return true;
		    	return false;
			}

			public boolean collidesLeft1() {
				for(float step = 0; step <= getHeight(); step += increment)
					if(isCellBlocked1(getX(), getY() + step))
						return true;
				return false;
			}

			public boolean collidesTop1() {
				for(float step = 0; step <= getWidth(); step += increment)
					if(isCellBlocked1(getX() + step, getY() + getHeight()))
						return true;
				return false;

			}

			public boolean collidesBottom1() {
				for(float step = 0; step <= getWidth(); step += increment)
					if(isCellBlocked1(getX() + step, getY()))
						return true;
				return false;
			}
		

	private boolean collidesLeft() {
		return false;
		// TODO Auto-generated method stub
		
	}

	private boolean collidesTop() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean collidesBottom() {
		return canJump;
		// TODO Auto-generated method stub
		
	}

	private boolean isCellBlocked(float f, float g) {
		// TODO Auto-generated method stub
		return false;
	}

    private void collidesRight() {
		// TODO Auto-generated method stub
		
	}

	public Vector2 getVelocidade() {
		return velocity;
	}

	public void setVelocidade(Vector2 velocidade) {
		this.velocity = velocidade;
	}

	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		case Keys.W:
			
			if(canJump) {
				velocity.y = speed / 1.8f;
			canJump = false;
		}
			break;
		case Keys.A:
			velocity.x = -speed;
			//animationTime = 0;
		break;
		case Keys.D:
			velocity.x = speed;
		//	animationTime = 0;
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
		case Keys.A:
		case Keys.D:
			velocity.x = 0;
		//	animationTime = 0;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
	//	 TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	
	}}

