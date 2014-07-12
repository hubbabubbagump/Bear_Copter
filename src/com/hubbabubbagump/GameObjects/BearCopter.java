package com.hubbabubbagump.GameObjects;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

//Adding acceleration vector to velocity and velocity to position every time bear update is called
public class BearCopter {
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private Vector2 score;
	private Vector2 scoreRate;
	
	private Circle boundCircle;
	private Boolean Alive;
	
	private float rotation;
	private int width;
	private int height;
	
	private float originalY;
	
	public BearCopter(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		this.originalY = y;
		position = new Vector2(x, y); //sets x and y coordinates for starting position
		velocity = new Vector2(0, 0); //sets initial velocity to 0
		acceleration = new Vector2(0, 430); //implements 0 x acceleration and 460 downwards acceleration
		boundCircle = new Circle();
		
		score = new Vector2(0, 0);
		scoreRate = new Vector2(1, 0);
		
		Alive = true;
	}
	
	public void update(float delta) {
		velocity.add(acceleration.cpy().scl(delta)); //adds a scalar of acceleration to velocity
		if (velocity.y > 200) { 
			velocity.y = 200; //sets a limit for velocity
		}
		position.add(velocity.cpy().scl(delta)); //adds a scalar of the velocity to the position
		
		//sets circle center to (9, 8) with respect to the bear
		// sets radius to 6.5f
		boundCircle.set(position.x + 9, position.y + 8, 8.3f);
		
		//rotating ccw
		if (velocity.y < 0) {
			rotation -= 600 * delta;
			if (rotation < -20) {
				rotation = -20;
			}
			
		}
		
		//rotate cw
		if (isFalling() || !Alive) {
			rotation += 480 * delta;
			if (rotation > 90) {
				rotation = 90;
			}
		}
		score.add(scoreRate.cpy().scl(delta));
	}
	
	public boolean isFalling() {
		return velocity.y > 110;
	}
	
	public boolean notScared() {
		return velocity.y > 70;
	}
	public void onClick() {
		if (Alive) {
			velocity.y = -130; //sets the velocity of the bear to -140 when screen is clicked.
			//originally was -140
	
		}
	}
	
	public void updatePause(float runTime) {
        position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
    }
	
	public float getScore() {
		return score.x;
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
	
	public Circle getBoundingCircle() {
		return boundCircle;
	}
	
	public boolean Alive() {
		return Alive;
	}
	
	public void dead() {
		Alive = false;
		scoreRate.x = 0;
		velocity.y = 0;
	}
	
	public void deadAcceleration() {
		acceleration.y = 0;
	}
	
	public void restart(int yPos) {
		rotation = 0;
		position.y = yPos;
		velocity.y = 0;
		acceleration.y = 460;
		Alive = true;
		
		scoreRate.x = 1;
		score.x = 0;
	}
}
