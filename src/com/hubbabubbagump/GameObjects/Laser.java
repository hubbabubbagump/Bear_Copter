package com.hubbabubbagump.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Laser {
	
	private Vector2 position;
	private Rectangle beam;
	private Random rand;
	private int laserGap;
	private static boolean active = false;
	private static boolean isFiring = false; //marker laser
	private static boolean fire = false; //actual beam
	private boolean stop = false;

	public Laser() {
		position = new Vector2(0, 0);
		beam = new Rectangle();
		rand = new Random();
		laserGap = 3;
		
	}
	
	public void update(BearCopter bear) {
		if(!stop) {
			position.y = bear.getY() + 8;
		}
		
		beam.set(0, position.y, 136, 3);
	}
	
	public float getY() {
		return position.y;
	}
	
	public boolean collides(BearCopter bear) {
		return Intersector.overlaps(bear.getBoundingCircle(), beam);
	}
	
	public void restart() {
		active = false;
		isFiring = false;
	}
	
	public static void activate() {
		active = true;
	}
	
	public boolean isActive() {
			return active;
	}
	
	public void justFired() {
		laserGap = rand.nextInt(6) + 5;
		isFiring = false;
		fire = false;
	}
	
	public void firing() {
		isFiring = true;
	}
	
	public void fire() {
		isFiring = false;
		fire = true;
	}
	
	public int getGap() {
		return laserGap;
	}
	
	public void pause() {
		stop = true;
	}
	
	public void resume() {
		stop = false;
	}
	
	public boolean isFiring() {
		return isFiring;
	}
	
	public boolean isFire() {
		return fire;
	}
	
}
