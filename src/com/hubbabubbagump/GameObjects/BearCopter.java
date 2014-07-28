package com.hubbabubbagump.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.hubbabubbagump.GameWorld.GameWorld;
import com.hubbabubbagump.Helpers.AssetLoader;
import com.hubbabubbagump.Screens.GameScreen;

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
	public static final float VOLUME = GameWorld.VOLUME;

	public static int COMBO = 0; // combo counter
	private static final int GOOD = 2; // combo multipliers
	private static final int GREAT = 3;
	private static final int EXCELLENT = 4;
	public static final int GOOD_THRESHOLD = 2;
	public static final int GREAT_THRESHOLD = 5;
	public static final int EXCELLENT_THRESHOLD = 10;

	private static float mid = GameScreen.midScreen();

	// vertical acceleration due to "gravity"
	public static final int ACCELERATION_Y = 430;

	// rate at which the score is accumulated
	public static final int SCORE_RATE = 1;

	// Velocity limit
	public static final int VELOCITY_CAP = 200;

	// Velocity the moment the screen is tapped
	public static final int VELOCITY_CLICK = -130;

	// How far the bear can rotate
	public static int CCW_ROTATION_CAP = -20;
	public static int CW_ROTATION_CAP = 90;

	// Speed of bear rotation
	public static int CCW_ROTATION_RATE = 600;
	public static int CW_ROTATION_RATE = 480;

	// radius of the circle
	public static float CIRCLE_RADIUS = 8.3f;

	public static float CIRCLE_X = 0;
	public static float CIRCLE_Y = 0;

	public static int SCORE_INCREASE = 3;
	public static final int SHROOM_INCREASE = 8;

	public static float BEAR_WIDTH = 9;
	public static float BEAR_HEIGHT = 8;

	// The X-axis which the bear follows a cosine curve around
	public static final float Y_COS = mid - 5;

	private static boolean isHigh = false;

	public BearCopter(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;

		position = new Vector2(x, y); // sets x and y coordinates for starting
										// position
		velocity = new Vector2(0, 0); // sets initial velocity to 0
		acceleration = new Vector2(0, ACCELERATION_Y); // implements 0 x
														// acceleration and 460
														// downwards
														// acceleration
		boundCircle = new Circle();

		score = new Vector2(0, 0);
		scoreRate = new Vector2(SCORE_RATE, 0);

		Alive = true;

		COMBO = 0;
	}

	public void minimize(float x, float y, float r) {
		BEAR_HEIGHT = y;
		BEAR_WIDTH = x;
		CIRCLE_RADIUS = r;
	}

	public void update(float delta) {
		isHigh = GameWorld.isHigh();
		// sets the center of the circle onto the middle of the bear
		CIRCLE_X = position.x + BEAR_HEIGHT;
		CIRCLE_Y = position.y + BEAR_WIDTH;

		velocity.add(acceleration.cpy().scl(delta)); // adds a scalar of
														// acceleration to
														// velocity
		if (velocity.y > VELOCITY_CAP) {
			velocity.y = VELOCITY_CAP; // sets a limit for velocity
		}
		position.add(velocity.cpy().scl(delta)); // adds a scalar of the
													// velocity to the position

		// sets circle center to (9, 8) with respect to the bear
		// sets radius to 6.5f
		boundCircle.set(CIRCLE_X + 1, CIRCLE_Y - 2, CIRCLE_RADIUS);

		// rotating ccw
		if (velocity.y < 0) {
			rotation -= CCW_ROTATION_RATE * delta;
			if (rotation < CCW_ROTATION_CAP && !isHigh) {
				rotation = CCW_ROTATION_CAP; // checks for rotation, and applies
												// rotation cap
			}

		}

		// rotate cw
		if (isFalling() || !Alive) {
			rotation += CW_ROTATION_RATE * delta;
			if (rotation > CW_ROTATION_CAP && !isHigh) {
				rotation = CW_ROTATION_CAP; // checks for rotation, and applies
											// rotation cap
			}
		}

		score.add(scoreRate.cpy().scl(delta));// adds a scalar of the score
												// velocity to the score
	}

	public void fruitEaten() {
		// score increases according to a multiplier when a fruit is eaten
		if (COMBO >= EXCELLENT_THRESHOLD) {
			score.x = score.x + (SCORE_INCREASE * EXCELLENT);

		} else if (COMBO >= GREAT_THRESHOLD) {
			score.x = score.x + (SCORE_INCREASE * GREAT);
		} else if (COMBO >= GOOD_THRESHOLD) {
			score.x = score.x + (SCORE_INCREASE * GOOD);
		} else {
			score.x = score.x + SCORE_INCREASE;
		}

		long eat = AssetLoader.eat.play();
		AssetLoader.eat.setVolume(eat, VOLUME);

	}

	public void shroomEaten() {
		if (COMBO >= EXCELLENT_THRESHOLD) {
			score.x = score.x + (SHROOM_INCREASE * EXCELLENT);

		} else if (COMBO >= GREAT_THRESHOLD) {
			score.x = score.x + (SHROOM_INCREASE * GREAT);
		} else if (COMBO >= GOOD_THRESHOLD) {
			score.x = score.x + (SHROOM_INCREASE * GOOD);
		} else {
			score.x = score.x + SHROOM_INCREASE;
		}
		long eat = AssetLoader.eat.play();
		AssetLoader.eat.setVolume(eat, VOLUME);

	}

	public static void comboReset() {
		COMBO = 0;
	}

	public static void comboUp() {
		COMBO += 1;
	}

	public boolean isFalling() {
		return velocity.y > 110;
	}

	public boolean notScared() {
		return velocity.y > 70;
	}

	public void onClick() {
		if (Alive && GameWorld.running()) {
			velocity.y = VELOCITY_CLICK; // sets the velocity of the bear to
											// -140 when screen is clicked.
			
			long up = AssetLoader.up.play();
			AssetLoader.up.setVolume(up, VOLUME);
		}
	}

	public void updatePause(float runTime) {
		// has the bear follow a
		// cosine curve when not
		// in the running state
		position.y = 2 * (float) Math.cos(7 * runTime) + Y_COS;
	}

	public float getScore() {
		return score.x;
	}

	public float getX() {
		return position.x; // retrieves the x coordinate of the bear
	}

	public float getY() {
		return position.y; // retrieves the y coordinate of the bear
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getRotation() { // retrieves the rotation of the bear
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
		scoreRate.x = 0; // stops the score from going up
		velocity.y = 0;
	}

	public void deadAcceleration() {
		acceleration.y = 0;
	}

	public void restart(int yPos) {
		isHigh = GameWorld.isHigh();
		rotation = 0;
		position.y = yPos; // sets the bear back to the starting position
		velocity.y = 0;
		acceleration.y = ACCELERATION_Y;
		Alive = true;
		position.x = 33;

		scoreRate.x = SCORE_RATE;
		score.x = 0; // resets the score

		COMBO = 0;
		BEAR_WIDTH = 9;
		BEAR_HEIGHT = 8;
		if (isHigh) {
			AssetLoader.bear.flip(true, false);
			AssetLoader.bearDown.flip(true, false);
		}
		isHigh = false;

		CCW_ROTATION_RATE = 600;
		CW_ROTATION_RATE = 480;
	}

	public void reverse(boolean high) {
		if (high) {
			position.x = 95;
			CCW_ROTATION_RATE = -800;
			CW_ROTATION_RATE = -800;
		} else if (!high) {
			position.x = 33;
			CCW_ROTATION_RATE = 600;
			CW_ROTATION_RATE = 480;
			rotation = 0;
		}
		AssetLoader.bear.flip(true, false);
		AssetLoader.bearDown.flip(true, false);

	}

}
