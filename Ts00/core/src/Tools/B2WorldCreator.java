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
import Screens.ScreenUs04;
import Sprites.Chest;
import Sprites.Porta;
import Sprites.Traps;


public class B2WorldCreator {
    public B2WorldCreator(PlayScreen screen){
    	World world = screen.getWorld();
    	TiledMap map = screen.getMap();
    	
    	//criando as variaveis do corpo e da fixture
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        //Corpo chao
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / DevBilac.PPM, (rect.getY() + rect.getHeight() / 2) / DevBilac.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / DevBilac.PPM, rect.getHeight() / 2 / DevBilac.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
}
