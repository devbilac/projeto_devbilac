package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import Screens.PlayScreen;

public abstract class Npc extends Sprite {
	protected World world;
	protected PlayScreen screen;
	public Body b2body;

	public Npc(PlayScreen screen, float x, float y) {
		super(screen.getAtlas().findRegion("right"));
		this.world = screen.getWorld();
		this.screen = screen;
		setPosition(x,y);
		defineNpc(x,y);
		
	}
	protected abstract void defineNpc(float x, float y);
	
	
}
