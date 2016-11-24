package br.com.ts3.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;

import br.com.ts3.game.FluxoGame;
import br.com.ts3.game.Scenes.Hud;
import br.com.ts3.game.Screens.PlayScreen;

public class Porta extends InteractiveTileObject {
    public Porta(PlayScreen screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
    }


    @Override
    public void onBodyHit() {
        Gdx.app.log("Door", "Collision");
        //debugando chest collision
        System.out.println(PlayScreen.getCheck());
        if(PlayScreen.getCheck() == 4){
            Hud.addScore(1000);
            FluxoGame.manager.get("audio/sounds/door_open.wav", Sound.class).play();
            
        }
    }
}
