package com.hubbabubbagump.JUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.hubbabubbagump.GameObjects.BearCopter;
import com.hubbabubbagump.GameObjects.Fruit;
import com.hubbabubbagump.GameObjects.Wall;
import com.hubbabubbagump.GameWorld.GameWorld;
import com.hubbabubbagump.Helpers.InputHandler;
import com.hubbabubbagump.Screens.GameScreen;

public class tst {
	private boolean isScrolledLeft;
	private boolean isScrolledRight;
	private Vector2 position;
	
	private int width = 10;
	
	@Test
	public void scrollTest() {
		position = new Vector2(0, 0);
		position.x = 100;
		for(int i = 0; i <= 100; i++) {
			isScrolledLeft = false;
			if (position.x + width < 0) {
				isScrolledLeft = true;
			}
			 
			 //If object passes left side of screen, sets scrolledLeft to true
			 if (position.x + width >= 0) {
				 assertFalse(isScrolledLeft);
			 }
			 else {
				 assertTrue(isScrolledLeft);
			 }
			 position.x--;
		}
		
		position.x = 0;
		for(int i = 0; i <= 100; i++) {
			isScrolledRight = false;
			if (position.x > 136) {
				isScrolledRight = true;
			}
			
			if (position.x > 136) {
				assertTrue(isScrolledRight);
			}
			else {
				assertFalse(isScrolledRight);
			}
			position.x++;
		}
	}
	
	private int score = 0;
	private int LENGTH;
	private int HEIGHT_LIMIT;
	private int height;
	private Random rand;
	private int yLoc;
	private boolean smaller;
	
	@Test
	public void randomizeTest() {
		rand = new Random();
		score = 0;
		LENGTH = 100;
		for (int i = 0; i <= 200; i++) {
			for (int j = 0; j <= 150; j++) {
				smaller = true;
				
				HEIGHT_LIMIT = (int) (LENGTH / 5);
				//creates a random number to determine length of wall based on score
				if (score >= 150) { //assuming LENGTH = 165, LENGHT / 5 = 33
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
				
				if (yLoc + height > LENGTH) smaller = false;
				assertTrue(smaller);
				
				
				score++;
			}
			LENGTH++;
		}
	}
	
	private Wall wall;
	private boolean Alive = true;
	private Rectangle rect;
	private Circle circle;
	
	@Test
	public void deathByBlock() {
		wall = new Wall(100, 0, 22, 100, 0);
		circle = new Circle();
		
		for(int i = 0; i <= 100; i++) {
			
			rect = wall.getBar();
			rect.set(wall.getX() - i, wall.getY(), wall.getWidth(), wall.getHeight());
			circle.set(30, 30, 3);
			if(Intersector.overlaps(circle, rect)) {
				Alive = false;
			}
			if(wall.getX() - i >= 33) {
				assertTrue(Alive);
			}
			else if (wall.getX() < 33) {
				assertFalse(Alive);
			}
			
		}
		
	}
	
	private Fruit fruit;
	private Circle circle2;
	private boolean eaten;
	
	@Test
	public void fruitorshroomTest() {
		fruit = new Fruit(100, 30, 6, 6, 0);
		circle2 = new Circle();
		circle = new Circle();
		eaten = false;
		
		for(int i = 0; i <= 100; i++) {
			circle2 = fruit.getCircle();
			circle2.set(fruit.getX() + 5, fruit.getY() + 5, 3);
			circle.set(30, 30, 3);
			if(Intersector.overlaps(circle, circle2)) {
				eaten = true;
			}
			
			if(circle.x >= 30) {
				assertFalse(eaten);
			}
			else if (circle.y < 30) {
				assertTrue(eaten);
			}
		}
		
	}
	
	private int wallY;
	private int wallHeight;
	private int groundHeight = 166;
	private int shroomHeight;
	
	@Test
	public void shroomSetTest() {
		wall = new Wall(100, 20, 22, 40, 0);
		wallY = (int) wall.getY();
		wallHeight = wall.getHeight();
		if (groundHeight - wallHeight - wallY >= wallY) {
			shroomHeight = ((groundHeight - wallHeight - wallY) / 2) + wallHeight + wallY;
		}
		else {
			shroomHeight = wallY / 2;
		}
		assertEquals(113, shroomHeight);
		
		groundHeight = 180;
		if (groundHeight - wallHeight - wallY >= wallY) {
			shroomHeight = ((groundHeight - wallHeight - wallY) / 2) + wallHeight + wallY;
		}
		else {
			shroomHeight = wallY / 2;
		}
		assertEquals(120, shroomHeight);
		
		wall = new Wall(100, 100, 22, 40, 0);
		wallY = (int) wall.getY();
		if (groundHeight - wallHeight - wallY >= wallY) {
			shroomHeight = ((groundHeight - wallHeight - wallY) / 2) + wallHeight + wallY;
		}
		else {
			shroomHeight = wallY / 2;
		}
		assertEquals(50, shroomHeight);
	}
	
	private boolean stopped = false;
	
	@Test
	public void stop() {
		wall = new Wall(100, 0, 10, 10, -1);
		if(wall.getVelocity() != 0) {
			stopped = false;
		}
		else if (wall.getVelocity() == 0) {
			stopped = true;
		}
		assertFalse(stopped);
		wall.stop();
		if(wall.getVelocity() != 0) {
			stopped = false;
		}
		else if (wall.getVelocity() == 0) {
			stopped = true;
		}
		assertTrue(stopped);
	}
	
	private BearCopter bear;
	private boolean velocityNot0 = false;
	private boolean accelerationNot0 = false;
	
	@Test
	public void bearStop() {
		bear = new BearCopter(33, 50, 16, 18);
		bear.ForTestingPurposes();
		if(bear.ay() != 0) {
			accelerationNot0 = true;
		}
		if (bear.vy() != 0) {
			velocityNot0 = true;
		}
		assertTrue(accelerationNot0);
		assertTrue(velocityNot0);
		bear.dead();
		if (bear.vy() == 0) {
			velocityNot0 = false;
		}
		assertFalse(velocityNot0);
		bear.deadAcceleration();
		if (bear.ay() == 0) {
			accelerationNot0 = false;
		}
		assertFalse(accelerationNot0);
		
	}
	
	private Rectangle ground;
	private int GRASS_LOCATION = 166;
	private int GAMEWIDTH = 136;
	
	@Test
	public void floorDeath() {
		ground = new Rectangle(0, GRASS_LOCATION, GAMEWIDTH, 20);
		circle = new Circle();
		circle.set(30, 50, 5);
		for (int i = 0; i <= 200; i++) {
			circle.set(30, 50 + i, 5);
			
			if (i <= 111) {
				assertFalse(Intersector.overlaps(circle, ground));
			}
			else if (i >= 141) {
				assertFalse(Intersector.overlaps(circle, ground));
			}
			else if (i > 111) {
				assertTrue(Intersector.overlaps(circle, ground));
			}
			
		}
	}
	private Rectangle ceil;
	
	@Test
	public void ceilingDeath() {
		ceil = new Rectangle(0, -40, 136, 20);
		circle = new Circle();
		for (int i = 0; i <= 200; i++) {
			circle.set(30, 50 - i, 5);
			if (i <= 65) {
				assertFalse(Intersector.overlaps(circle, ceil));
			}
			else if (i >= 95) {
				assertFalse(Intersector.overlaps(circle, ceil));
			}
			else if (i > 65) {
				assertTrue(Intersector.overlaps(circle, ceil));
			}
			
		}
		
	}
}
