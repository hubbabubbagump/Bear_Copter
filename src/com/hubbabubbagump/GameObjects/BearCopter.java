package com.hubbabubbagump.GameObjects;


import com.badlogic.gdx.math.Vector2;

//Adding acceleration vector to velocity and velocity to position every time bear update is called
public class BearCopter {
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private float rotation;
	private int width;
	private int height;
	
	public BearCopter(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y); //sets x and y coordinates for starting position
		velocity = new Vector2(0, 0); //sets initial velocity to 0
		acceleration = new Vector2(0, 460); //implements 0 x acceleration and 460 downwards acceleration
	}
	
	public void update(float delta) {
		velocity.add(acceleration.cpy().scl(delta)); //adds a scalar of acceleration to velocity
		if (velocity.y > 200) { 
			velocity.y = 200; //sets a limit for velocity
		}
		position.add(velocity.cpy().scl(delta)); //adds a scalar of the velocity to the position
	}
	
	public void onClick() {
		velocity.y = -140; //sets the velocity of the bear to -140 when screen is clicked.
	}
	
	public float getX() {
		return position.x; //retrieves the x coordinate of the bear
	}
	
	public float getY() {
		return position.y; //retrieves the y coordinate of the bear
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getRotation() { //retrieves the rotation of the bear
		return rotation;
	}
	
}
