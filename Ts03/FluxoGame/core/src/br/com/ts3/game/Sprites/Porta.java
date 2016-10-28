package br.com.ts3.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import br.com.ts3.game.Screens.PlayScreen;

public class Porta extends InteractiveTileObject {
    public Porta(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
    }


    @Override
    public void onBodyHit() {
        Gdx.app.log("Door", "Collision");
    }
}
