package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.DevBilac;

import Scenes.HudUs04;
import Screens.ScreenUs04;

public class Porta extends InteractiveTileObjectUs04 {
    public Porta(ScreenUs04 screen, Rectangle bounds){
        super(screen, bounds);
        fixture.setUserData(this);
    }


    @Override
    public void onBodyHit() {
        Gdx.app.log("Door", "Collision");
        //debugando chest collision
        System.out.println(ScreenUs04.getCheck());
        if(ScreenUs04.getCheck() == 4){
            HudUs04.addScore(1000);
            DevBilac.manager.get("audio/sounds/door_open.wav", Sound.class).play();
            
        }
    }
}
