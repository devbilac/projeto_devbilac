package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.DevBilac;

import Screens.PlayScreen;
import Sprites.Brick;
import Sprites.Coin;

public class B2WorldCreator {
	//Classe que verifica Colisão com os Objetos do mapa, assim impedindo o Usuario de colidir com alguns objetos.
	//Utiliza-se as 'Layers' para indentificar qual objeto pode ou não se colidir.
	//tileset_gutter.png = mapa do jogo
	public B2WorldCreator(PlayScreen screen) {
		World world = screen.getWorld();
		TiledMap map = screen.getMap();
		// TODO Auto-generated constructor stub
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() /2) / DevBilac.PPM, (rect.getY() + rect.getHeight() / 2) / DevBilac.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox((rect.getWidth() / 2) / DevBilac.PPM, (rect.getHeight()/ 2) / DevBilac.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			
		}

		//Create brick bodies/fixtures
		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() /2) / DevBilac.PPM, (rect.getY() + rect.getHeight() / 2) / DevBilac.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox((rect.getWidth() / 2) / DevBilac.PPM, (rect.getHeight()/ 2) / DevBilac.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			
		}
		//Create brick bodies/fixtures
		for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Brick(screen, rect);
			
		}
		//Create brick bodies/fixtures
		for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new Coin(screen, rect);
			
		}
	}		
}
