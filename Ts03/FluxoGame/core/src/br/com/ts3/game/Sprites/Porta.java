package br.com.ts3.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Porta extends InteractiveTileObject {
    public Porta(World world, TiledMap map, Rectangle bounds){
        super(world, map, bounds);
        fixture.setUserData(this);
    }


    @Override
    public void onBodyHit() {
        Gdx.app.log("Door", "Collision");
    }
}
