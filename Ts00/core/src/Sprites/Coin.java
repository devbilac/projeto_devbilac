package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.DevBilac;

import Screens.ScreenUs04;


public class Coin extends InteractiveTileObjectUs04 {
	private DevBilac game;
    public Coin(ScreenUs04 screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
        game.batch.draw(new Texture("coins.png"),16,50);

    }

    @Override
    public void onBodyHit() {
        Gdx.app.log("Coin", "Collision");
        //FluxoGame.manager.get("audio/sounds/coin.wav", Sound.class).play();
    }
}
