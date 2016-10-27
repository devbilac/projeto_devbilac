package br.com.ts3.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import br.com.ts3.game.FluxoGame;


public class Chest extends InteractiveTileObject {
    public Chest(World world, TiledMap map, Rectangle bounds){
        super(world, map,bounds);
        fixture.setUserData(this);
        setCategoryFilter(FluxoGame.CHEST_BIT);
    }

    @Override
    public void onBodyHit() {
        Gdx.app.log("Chest", "Collision");
        setCategoryFilter(FluxoGame.PASS_BIT);
        
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(2);
        
        //Chest 1
        int i = 26;
        int j = 8;
        if (i == 26 && j == 8){
        	layer.getCell(26,8).setTile(null);
            layer.getCell(25, 8).setTile(null);
            layer.getCell(25, 9).setTile(null);
            layer.getCell(26,9).setTile(null);
        }
        
       //getCell().setTile(null);
        
        
    }
}
