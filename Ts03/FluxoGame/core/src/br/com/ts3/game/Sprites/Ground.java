package br.com.ts3.game.Sprites;

import com.badlogic.gdx.math.Rectangle;
import br.com.ts3.game.Screens.PlayScreen;


public class Ground extends InteractiveTileObject {
    public Ground(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
    }

    @Override
    public void onBodyHit() {

    }
}
