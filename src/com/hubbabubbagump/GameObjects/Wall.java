package com.hubbabubbagump.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.hubbabubbagump.Screens.GameScreen;

public class Wall extends Scrollable{

		private Random rand;
		private static float length;
		private Rectangle bar;
		
		//resets the wall
		public Wall(float x, float y, int width, int height, float scrollSpeed, float groundY) {
			super(x, y, width, height, scrollSpeed);
			
			rand = new Random();
			
			bar = new Rectangle();
		}
		
		@Override
		public void update(float delta) {
			super.update(delta);
			
			bar.set(position.x, position.y + yLoc, width, height);
		}
		
		public boolean collides(BearCopter bear) {
			if(position.x < bear.getX() + bear.getWidth()) {
				return (Intersector.overlaps(bear.getBoundingCircle(), bar));
			}
			return false;
		}
		
		@Override
		public void reset(float newX) {
			super.reset(newX);
			
			length = GameScreen.midScreen() + 66;
			int l = (int) (length / 4);
			int h = (int) (length / 3.5);
			
			//creates a random number to determine length of wall and how far from top of screen
			height = rand.nextInt(h) + h - 10;
			rand = new Random();
			if (height > h + 5) {
				yLoc = rand.nextInt(l);
			}
			else yLoc = rand.nextInt(l + 10);
			
			//in case brick extends too long
			if (yLoc + height > length) {
				height = (int) (length / 2);
				yLoc = (float) (length / 3);
			}
			
		}
		
		public Rectangle getBar() {
			return bar;
		}
}
