package br.com.ts3.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import br.com.ts3.game.Screens.PlayScreen;


public class Traps extends InteractiveTileObject {
    public Traps(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
    }
    
    public void update(float dt){
    	
    }

    @Override
    public void onBodyHit() {
        Gdx.app.log("Trap", "Collision");
    }
}
