package br.com.ts3.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import br.com.ts3.game.FluxoGame;
import br.com.ts3.game.Screens.PlayScreen;
import br.com.ts3.game.Sprites.Chest;
import br.com.ts3.game.Sprites.Coin;
import br.com.ts3.game.Sprites.Escada;
import br.com.ts3.game.Sprites.Porta;
import br.com.ts3.game.Sprites.Traps;


public class B2WorldCreator {
    public B2WorldCreator(PlayScreen screen){
    	World world = screen.getWorld();
    	TiledMap map = screen.getMap();
    	
    	//criando as variaveis do corpo e da fixture
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //Escadas
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Escada(screen, rect);
        }

        //Moedas
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(screen, rect);
        }

        //Corpo chao
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / FluxoGame.PPM, (rect.getY() + rect.getHeight() / 2) / FluxoGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / FluxoGame.PPM, rect.getHeight() / 2 / FluxoGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Placas
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Chest(screen, rect);
        }

        //Traps
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Traps(screen, rect);
        }

        //Porta
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Porta(screen, rect);
        }
    }
}
