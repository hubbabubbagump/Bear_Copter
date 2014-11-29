package com.hubbabubbagump.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hubbabubbagump.GameWorld.GameWorld;
import com.hubbabubbagump.Screens.GameScreen;

public class Wall extends Scrollable{

		private Random rand;
		private static float LENGTH = GameScreen.midScreen() + 66;
		private Rectangle bar;
		
		//length of wall limit
		public static int HEIGHT_LIMIT = (int) (LENGTH / 5);
		//limit of how far down the wall can be
		public static final int LENGTH_LIMIT = (int) (LENGTH / 2.5);
		
		private static int score;
		private static float widthToCheck;
		private static float xLoc;
		
		private boolean destroy = false;
		private Vector2 acceleration;
		private static final int ACCELERATION_Y = 460;
		
		private Vector2 time;
		private Vector2 timeRate;
		private static final int TIMERATE = 1;
		private boolean addTime = false;
		
		public int wallLevel = 1;
				
		//resets the wall
		public Wall(float x, float y, int width, int height, float scrollSpeed) {
			super(x, y, width, height, scrollSpeed);
			
			rand = new Random();
			bar = new Rectangle();
			
			widthToCheck = width;
			
			acceleration = new Vector2(0, ACCELERATION_Y);
			
			time = new Vector2(0, 0);
			timeRate = new Vector2(TIMERATE, 0);
			
		}
		
		@Override
		public void update(float delta) {
			super.update(delta);
			if(GameWorld.isInvincible() && (position.x <= 120)) {
				destroyWall();
				addTime = true;
			}
			if (addTime) {
				time.add(timeRate.cpy().scl(delta));
			}
			if (time.x >= 0.35 && destroy) {
				velocity.add(acceleration.cpy().scl(delta));
			}
			//position.x and position.x are the coordinates for the top left of the wall
			bar.set(position.x, position.y + yLoc, width, height);
		}
		
		public void destroyWall() {
			destroy = true;
		}
		
		public boolean isDestroy() {
			return destroy;
		}
		
		public boolean collides(BearCopter bear) {
			if (!destroy) {
				//checks if the wall and circle around the bear overlap
				return (Intersector.overlaps(bear.getBoundingCircle(), bar));
			}
			else return false;
		}
		
		//tested
		public void randomize() {
			score = GameWorld.getScore();
			HEIGHT_LIMIT = (int) (LENGTH / 5);
			//creates a random number to determine length of wall based on score
			if (score >= 200) { //63 - 103
				HEIGHT_LIMIT  = (int) ((int) (LENGTH / 5) + (LENGTH / 5.5));
				height = rand.nextInt(HEIGHT_LIMIT - 19) + HEIGHT_LIMIT;
			}
			else if (score >= 125) { //assuming LENGTH = 165, LENGHT / 5 = 33
				HEIGHT_LIMIT = (int) ((int) (LENGTH / 5) + (LENGTH / 5.5));
				height = rand.nextInt(HEIGHT_LIMIT - 29) + (HEIGHT_LIMIT - 7); //56 - 93
				
			}
			else if (score >= 50) {
				HEIGHT_LIMIT =  (int) ((int) (LENGTH / 5) + (LENGTH / 8));
				height = rand.nextInt(HEIGHT_LIMIT - 21) + (HEIGHT_LIMIT - 11); //42 - 74
				
			}
			else {
				height = rand.nextInt(HEIGHT_LIMIT - 5) + (HEIGHT_LIMIT - 5); // 28 - 56
			}
			
			//creates a random number to determine how far down the wall is
			int MAX_LENGTH = (int) (LENGTH - height);
			yLoc = rand.nextInt(MAX_LENGTH);
			
			
		}
		
		@Override
		public void reset(float newX) {
			super.reset(newX);
			position.y = 0;
			randomize();
			xLoc = newX;
			destroy = false;
			velocity.y = 0;
			time.x = 0;
			addTime = false;
			
		}
		
		public Rectangle getBar() {
			return bar;
		}
		
		public void restart(float x, float scrollSpeed) {
			velocity.x = scrollSpeed;
			wallLevel = 1;
			reset(x);
		}
		
		public void bars() {
			wallLevel = 2;
		}
		
		public float getY() {
			return position.y + yLoc;
		}
		
		public int getHeight() {
			return height;
		}
		
		public float getX() {
		    return position.x;
		}
		 
		public float getEndX() {
			return xLoc + widthToCheck;
		}
		 
		public void reverse(boolean high) {
			velocity.x = -velocity.x;
			
			if (position.x <= 68) {
				position.x = 136 - position.x;
			}
			else if (position.x > 68) {
				position.x = 136 - position.x;
			}
		}
		
		public float getVelocity() {
			return velocity.x;
		}
		
		public void down() {
			position.y++;
		}
		
}
