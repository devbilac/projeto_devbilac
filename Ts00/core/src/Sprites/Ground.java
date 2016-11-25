package Sprites;

import com.badlogic.gdx.math.Rectangle;
import Screens.ScreenUs04;


public class Ground extends InteractiveTileObjectUs04 {
    public Ground(ScreenUs04 screen, Rectangle bounds){
        super(screen, bounds);
    }

    @Override
    public void onBodyHit() {

    }
}
