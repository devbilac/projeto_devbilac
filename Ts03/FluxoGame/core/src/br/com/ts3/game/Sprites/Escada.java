package br.com.ts3.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import br.com.ts3.game.FluxoGame;
import br.com.ts3.game.Screens.PlayScreen;


public class Escada extends InteractiveTileObject {
    public Escada(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);

    }

    @Override
    public void onBodyHit() {
        Gdx.app.log("Escada", "Collision");
        setCategoryFilter(FluxoGame.PASS_BIT);
    }
}

