package br.com.ts3.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import br.com.ts3.game.FluxoGame;
import br.com.ts3.game.Screens.PlayScreen;


public class Coin extends InteractiveTileObject {
	private FluxoGame game;
    public Coin(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        game.batch.draw(new Texture("coins.png"),16,50);

    }

    @Override
    public void onBodyHit() {
        Gdx.app.log("Coin", "Collision");
    }
}
