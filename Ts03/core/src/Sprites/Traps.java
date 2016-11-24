package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import Screens.ScreenUs04;


public class Traps extends InteractiveTileObjectUs04 {
    public Traps(ScreenUs04 screen, Rectangle bounds){
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
