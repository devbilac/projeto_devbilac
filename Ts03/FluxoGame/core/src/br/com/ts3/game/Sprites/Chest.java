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
        
        //Animacao que some os chest quando colide
        //Pegando a posição do Devb
        int i = (int) ((int)body.getPosition().x * FluxoGame.PPM / 16);
        int j = (int) ((int)body.getPosition().y * FluxoGame.PPM / 16);
               
        //Chest 1
        if (i == 25 && j == 6){
        	layer.getCell(26,8).setTile(null);
            layer.getCell(25, 8).setTile(null);
            layer.getCell(25, 9).setTile(null);
            layer.getCell(26,9).setTile(null);
        }
        
        //Chest 2
        if(i == 62 && j == 6){
        	layer.getCell(65,10).setTile(null);
            layer.getCell(66, 10).setTile(null);
            layer.getCell(65, 9).setTile(null);
            layer.getCell(66,9).setTile(null);
        }
        
        //Chest 3
        if(i == 125 && j == 6){
        	layer.getCell(128,10).setTile(null);
            layer.getCell(128, 9).setTile(null);
            layer.getCell(129, 9).setTile(null);
            layer.getCell(129,10).setTile(null);
        }
        
        //Chest 4
        if(i == 206 && j == 6){
        	layer.getCell(210,10).setTile(null);
            layer.getCell(210, 9).setTile(null);
            layer.getCell(211, 9).setTile(null);
            layer.getCell(211,10).setTile(null);
        }       
    }
}
