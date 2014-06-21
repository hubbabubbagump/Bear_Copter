package com.hubbabubbagump.GameWorld;

import com.badlogic.gdx.math.Rectangle;

public class GameWorld {
	
	private Rectangle rect = new Rectangle(0, 0, 17, 12);

		public void update(float delta) {
			System.out.println("Updating GameWorld");
			//Handles the scrolling of a rect from left to right and returning back left.
			rect.x++;
			if (rect.x > 137) {
				rect.x = 0;
			}
		}
		
		public Rectangle getRect() {
			return rect;
		}
}
