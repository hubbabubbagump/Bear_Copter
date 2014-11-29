package com.hubbabubbagump.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.hubbabubbagump.GameWorld.GameWorld;
import com.hubbabubbagump.Screens.GameScreen;


public class ScrollHandler {
	
	
    private Grass frontGrass, backGrass;
    private Background frontBG, backBG;
    private Dirt frontDirt, backDirt;
    private Wall wall1;
	private Wall wall2;
	private Wall wall3;
    private Fruit fruit1, fruit2, fruit3;
    private Shroom shroom;
    private Laser laser;
    private Animals penguin, ram, toast, cat;
    
    private boolean callCombo1 = true;//ensures it only calls the function once instead of
    private boolean callCombo2 = true;//all of the time it spends behind the bear
    private boolean callCombo3 = true;
    private boolean callCombo4 = true;
    
    public static final int WALLS = 3; //number of walls
   // private Wall[] wall = new Wall[WALLS];
    
    private Random rand;
    
    //Randomizer for the first 3 walls
    private int yPos1, yPos2, yPos3, yLength1, yLength2, yLength3;
    
    private int shroomProbability;			//creates a random number from 0 to
    private static final int SHROOMMAX = 30;//SHROOMMAX to determine whether or not to spawn a shroom
    private static int SHROOMNUMBER = 28; //if the randomized number is > 28, spawns a shroom
    private static int SHROOMNUMBERSAVE = SHROOMNUMBER;
    
    private static boolean SHROOM = false; // is true if a shroom exists in front of the bear
    									   // only allows one shroom on the screen at a time
    private static int shroomHeight; // creates a shroom halfway between the bottom 
    private static int wallHeight;   // of the wall and ground or top of the wall
    private static int wallY;        // and ceiling
    private static int wallX;
    private static int groundHeight;
    
    private float fruitX1, fruitX2;
    private float fruitX3, fruitY1, fruitY2, fruitY3, restart1, //reset/restart values for
    restart2, restart3, reset1, reset2, reset3;                 //the 3 fruit
    public static final int MINIMUM_HEIGHT = 20; // minimum height of a fruit
	public static final int MAXIMUM_HEIGHT = (int) (GameScreen.midScreen() + 26); //add 20 to get the maximum height of a fruit
	public static final int FRUIT_WIDTH_HEIGHT = 9; //height and width of a fruit
	public int fruit_gap;
    public static final int FRUIT_GAP = 5; // the maximum numbers of walls before another fruit appears
    public static final float FRUIT_SPACE = (float) 78.72; //The distance from the middle of a gap to the next
  
    //old speed value -49
    public static int SCROLL_SPEED = -49; //scroll speed of the objects
    public static final int BG_SPEED = -12;
    public static final int ENEMY_SPEED = 20;
    
    public static final int WALL_GAP = 58; //originally 49
    
    
    //limits for randomizing first 3 walls
    public static final int HEIGHT = 50;
    public static final int LENGTH = 20;
    
    private int ground = (int) (GameScreen.midScreen() + 66); //y location for the ground
    
    //public static int RESET_WALL;
    
    public static boolean USESCROLLEDLEFT = true; // changes when velocity of the objects is reversed
    
    public int LEVEL;
    public boolean level4 = false;
    
    public int wallCount = 0;
    public int laserGap;
    public Vector2 timer;
    public Vector2 timerRate;
    public boolean isFiring = false;
    public boolean fire = false;
    public static final float WAIT_TIME = (float) 3.9;
    
    private boolean spawnPenguin = true;
    private boolean spawnRam = true;
    private boolean spawnToast = true;
    private boolean spawnCat = true;
    private int animalHeight;
    
    private int score;
    
    // Constructor receives a float that tells us where we need to create our 
    // Grass and Wall objects.
    public ScrollHandler(float yPos) {
    	groundHeight = (int) yPos;
    	rand = new Random();
    	
    	laser = new Laser();
    	timer = new Vector2(0, 0);
    	timerRate = new Vector2(1, 0);
    	
    	//Randomizer for the first 3 walls
    	yPos1 = rand.nextInt(HEIGHT);
    	yPos2 = rand.nextInt(HEIGHT);
    	yPos3 = rand.nextInt(HEIGHT);
    	yLength1 = rand.nextInt(LENGTH) + 50;
    	yLength2 = rand.nextInt(LENGTH) + 50;
    	yLength3 = rand.nextInt(LENGTH) + 50;
    	
    	//consturcts the background
    	frontBG = new Background(0, ground - 200, 136, 200, BG_SPEED);
    	backBG = new Background(frontBG.getTailX(), ground - 200, 136, 200, BG_SPEED);
    	
    	//constructs the first frontGrass and backGrass
    	frontGrass = new Grass(0, yPos, 136, 11, SCROLL_SPEED);
    	backGrass = new Grass(frontGrass.getTailX(), yPos, 136, 11, SCROLL_SPEED);
    	
    	frontDirt = new Dirt(9, yPos + 11, 136, 50, SCROLL_SPEED);
    	backDirt = new Dirt(frontDirt.getTailX(), yPos + 11, 136, 50, SCROLL_SPEED);
    	
    	//x position, y position, width, height, scroll speed
    	//constructs first 3 walls
    	wall1 = new Wall(210, yPos1, 22, yLength1, SCROLL_SPEED);
    	wall2 = new Wall(wall1.getTailX() + WALL_GAP, yPos2 ,22, yLength2, SCROLL_SPEED);
    	wall3 = new Wall(wall2.getTailX() + WALL_GAP, yPos3, 22, yLength3, SCROLL_SPEED);
    	
    	//Randomizer and constructors for the first 3 fruit
    	fruitX1 = (float) (261 + rand.nextInt(FRUIT_GAP) * FRUIT_SPACE);
    	fruitY1 = rand.nextInt(MAXIMUM_HEIGHT) + MINIMUM_HEIGHT;
    	fruit1 = new Fruit(fruitX1, fruitY1, FRUIT_WIDTH_HEIGHT,
    			FRUIT_WIDTH_HEIGHT, SCROLL_SPEED);
    	
    	fruitX2 = fruit1.getX() + (rand.nextInt(FRUIT_GAP) + 1) * FRUIT_SPACE;
    	fruitY2 = rand.nextInt(MAXIMUM_HEIGHT) + MINIMUM_HEIGHT;
    	fruit2 = new Fruit(fruitX2, fruitY2, FRUIT_WIDTH_HEIGHT, 
    			FRUIT_WIDTH_HEIGHT, SCROLL_SPEED);
    	
    	fruitX3 = fruit2.getX() + (rand.nextInt(FRUIT_GAP) + 1) * FRUIT_SPACE;
    	fruitY3 = rand.nextInt(MAXIMUM_HEIGHT) + MINIMUM_HEIGHT;
    	fruit3 = new Fruit(fruitX3, fruitY3, FRUIT_WIDTH_HEIGHT, 
    			FRUIT_WIDTH_HEIGHT, SCROLL_SPEED);
    	
    	shroom = new Shroom(0, -50, FRUIT_WIDTH_HEIGHT, FRUIT_WIDTH_HEIGHT, SCROLL_SPEED);
    	
    	penguin = new Animals(0, -100, 13, 12, SCROLL_SPEED);
    	ram = new Animals(0, -100, 13, 12, SCROLL_SPEED);
    	toast = new Animals(0, -100, 13, 12, SCROLL_SPEED);
    	cat = new Animals(0, -100, 13, 12, SCROLL_SPEED);
    	
    }
    
    
    public void updatePause(float delta) {

        frontGrass.update(delta);
        backGrass.update(delta);
        frontBG.update(delta);
        backBG.update(delta);
        frontDirt.update(delta);
        backDirt.update(delta);
        
        // Same with grass
        if (frontGrass.isScrolledLeft()) {
            frontGrass.reset(backGrass.getTailX());

        } else if (backGrass.isScrolledLeft()) {
            backGrass.reset(frontGrass.getTailX());

        }
        
        if(frontDirt.isScrolledLeft()) {
        	frontDirt.reset(backDirt.getTailX());
        }
        else if (backDirt.isScrolledLeft()) {
        	backDirt.reset(frontDirt.getTailX());
        }
        
        if (frontBG.isScrolledLeft()) {
        	frontBG.reset(backBG.getTailX());
        }
        else if (backBG.isScrolledLeft()) {
        	backBG.reset(frontBG.getTailX());
        }

    }
    
    public void level() {
    	LEVEL = GameWorld.getLevel();
    }
    
    public boolean isLevel4() {
    	return level4;
    }
    
	public void update(float delta, BearCopter bear) {
		rand = new Random();
        frontGrass.update(delta);
        backGrass.update(delta);
        frontDirt.update(delta);
        backDirt.update(delta);
        frontBG.update(delta);
        backBG.update(delta);
        wall1.update(delta);
        wall2.update(delta);
        wall3.update(delta);
        fruit1.update(delta);
        fruit2.update(delta);
        fruit3.update(delta);
        shroom.update(delta);
        laser.update(bear);
        penguin.update(delta);
        ram.update(delta);
        toast.update(delta);
        cat.update(delta);
        score = GameWorld.getScore();
        
        if (penguin.getX() <= -100) {
        	penguin.stop();
        }
        if (ram.getX() <= -100) {
        	ram.stop();
        }
        if (toast.getX() <= -100) {
        	toast.stop();
        }
        if (cat.getX() <= -100) {
        	cat.stop();
        }
        if (animalCollidesShroom(shroom)) {
        	shroom.moveUp();
        }
        
        wallCheck();
        level();
        
        
        if(laser.isActive()) {
        	laserGap = laser.getGap();
        	if (wallCount == laserGap) {
        		isFiring = true;
        		wallCount = 0;
        		laser.resume();
        	}
        	
        	if(isFiring) {
        		timer.add(timerRate.cpy().scl(delta));
        		laser.firing();
        	}
        	
        	if (timer.x >= WAIT_TIME - 1) {
        		laser.pause();
        	}
        	
        	if(timer.x >= WAIT_TIME + 0.35) {
        		fire = false;
        		timer.x = 0;
        		laser.justFired();
        	}
        	else if(timer.x >= WAIT_TIME) {
        		isFiring = false;
        		fire = true;
        		timer.add(timerRate.cpy().scl(delta));
        		laser.fire();
        	}
        }
        
        
        //if the fruit is behind the bear and isn't eaten, resets the combo
        if(fruit1.combo() && callCombo1) {
			BearCopter.comboReset();
			callCombo1 = false; //ensures it only calls the function once instead of
								//all of the time it spends behind the bear
        }
        if(fruit2.combo() && callCombo2) {
			BearCopter.comboReset();
			callCombo2 = false;

        }
        if(fruit3.combo() && callCombo3) {
			BearCopter.comboReset();
			callCombo3 = false;

        }
        if(shroom.combo() && callCombo4) {
        	BearCopter.comboReset();
        	callCombo4 = false;
        }
        
        //wall 1 retrieves wall 2, wall 2 retrieves wall 3, etc.
        //Same for grass.
        
        
        if (USESCROLLEDLEFT) {
	       wallLeft();
	        
	       fruitLeft();
	        
	       grassLeft();
	       
	       BGLeft();
	        
        }
        //used during high
        if(!USESCROLLEDLEFT) {
        	wallRight();
 	        //Creates a new fruit at least one wall behind the last fruit, with a maximum of 4 behind
 	        fruitRight();
 	        
 	        grassRight();
        }
        
    }
	
	public void wallLeft() {

		if (wall1.isScrolledLeft()) {
        	wall1.reset(wall3.getTailX() + WALL_GAP);
        	shroomProbability = rand.nextInt(SHROOMMAX);
        	if(shroomProbability >= SHROOMNUMBER && SHROOM) {
        		shroom(wall1);
        	}
        	if (LEVEL == 4 && (backBG.LEVEL == 4 || frontBG.LEVEL == 4)) {
        		wall1.bars();
        		
        	}
        	if (laser.isActive()) {
        		wallCount++;
        	}
        	if (score >= 75 && spawnPenguin) {
        		spawnAnimal(wall1, penguin);
        		spawnPenguin = false;
        	}
        	if (score >= 150 && spawnRam) {
        		spawnAnimal(wall1, ram);
        		spawnRam = false;
        	}
        	if (score >= 250 && spawnToast) {
        		spawnAnimal(wall1, toast);
        		spawnToast = false;
      
        	}
        	if (score >= 350 && spawnCat) {
        		spawnAnimal(wall1, cat);
        		spawnCat = false;
        	}
        }
        else if (wall2.isScrolledLeft()) {
        	
        	wall2.reset(wall1.getTailX() + WALL_GAP);
        	shroomProbability = rand.nextInt(SHROOMMAX);
        	if(shroomProbability >= SHROOMNUMBER && SHROOM) {
        		shroom(wall2);
        	}
        	if (LEVEL == 4 && (backBG.LEVEL == 4 || frontBG.LEVEL == 4)) {
        		wall2.bars();
        		
        	}
        	if (laser.isActive()) {
        		wallCount++;
        	}
        	if (score >= 75 && spawnPenguin) {
        		spawnAnimal(wall2, penguin);
        		spawnPenguin = false;
        	}
        	if (score >= 150 && spawnRam) {
        		spawnAnimal(wall2, ram);
        		spawnRam = false;
        	}
        	if (score >= 250 && spawnToast) {
        		spawnAnimal(wall2, toast);
        		spawnToast = false;
      
        	}
        	if (score >= 350 && spawnCat) {
        		spawnAnimal(wall2, cat);
        		spawnCat = false;
        	}
        }
        else if (wall3.isScrolledLeft()) {
      
        	wall3.reset(wall2.getTailX() + WALL_GAP);
        	shroomProbability = rand.nextInt(SHROOMMAX);
        	if(shroomProbability >= SHROOMNUMBER && SHROOM) {
        		shroom(wall3);
        	}
        	if (LEVEL == 4 && (backBG.LEVEL == 4 || frontBG.LEVEL == 4)) {
        		wall3.bars();
        		
        	}
        	if (laser.isActive()) {
        		wallCount++;
        	}
        	if (score >= 75 && spawnPenguin) {
        		spawnAnimal(wall3, penguin);
        		spawnPenguin = false;
        	}
        	if (score >= 150 && spawnRam) {
        		spawnAnimal(wall3, ram);
        		spawnRam = false;
        	}
        	if (score >= 250 && spawnToast) {
        		spawnAnimal(wall3, toast);
        		spawnToast = false;
      
        	}
        	if (score >= 350 && spawnCat) {
        		spawnAnimal(wall3, cat);
        		spawnCat = false;
        	}
        }
	}
	
	public void fruitLeft() {
		 //Creates a new fruit at least one wall behind the last fruit, with a maximum of 4 behind
        if (fruit1.isScrolledLeft()) {
        	reset1 = (rand.nextInt(FRUIT_GAP) + 2 * FRUIT_SPACE);
        	fruit1.reset(fruit3.getX() + reset1);
			callCombo1 = true;

        }
        else if (fruit2.isScrolledLeft()) {
        	reset2 = (rand.nextInt(FRUIT_GAP) + 2) * FRUIT_SPACE;
        	fruit2.reset(fruit1.getX() + reset2);
			callCombo2 = true;
        	
			

        }
        else if (fruit3.isScrolledLeft()) {
        	reset3 = (rand.nextInt(FRUIT_GAP) + 2) * FRUIT_SPACE;
        	fruit3.reset(fruit2.getX() + reset3);
			callCombo3 = true;
			
        }
	}
	
	public void grassLeft() {
		 if (frontGrass.isScrolledLeft()) {
	        	frontGrass.reset(backGrass.getTailX());
	        }
	        else if (backGrass.isScrolledLeft()) backGrass.reset(frontGrass.getTailX());
	        
	        if(frontDirt.isScrolledLeft()) {
	        	frontDirt.reset(backDirt.getTailX());
	        }
	        else if (backDirt.isScrolledLeft()) {
	        	backDirt.reset(frontDirt.getTailX());
	        }
	        
	        
	        if(shroom.isScrolledLeft()) {
	        	SHROOM = true;
	        }
	}
	
	public void BGLeft() {
		//BG is rainbow when reversed, so no need for a BGright
		if (frontBG.isScrolledLeft()) {
        	if (LEVEL == 1) {
        		frontBG.level1();
        	}
        	else if (LEVEL == 2) {
        		frontBG.level2();
        	}
        	else if (backBG.LEVEL == 3 || LEVEL == 4 || level4) {
        		frontBG.level4();
        	}
        	else if (LEVEL == 3) {
        		frontBG.level3();
        		level4 = true;
        	}
        	
        	frontBG.reset(backBG.getTailX());
        }
        else if (backBG.isScrolledLeft()) {
        	if (LEVEL == 1) {
        		backBG.level1();
        	}
        	else if (LEVEL == 2) {
        		backBG.level2();
        	}
        	else if (backBG.LEVEL == 3 || LEVEL == 4 || level4) {
        		backBG.level4();
        	}
        	else if (LEVEL == 3) {
        		backBG.level3();
        		level4 = true;
        	}
        	
        	backBG.reset(frontBG.getTailX());
        }
	}
	
	public void wallRight() {
		if (wall1.isScrolledRight()) {
    		
	        	wall1.reset(wall3.getX() - WALL_GAP - 22);
	        	 shroomProbability = rand.nextInt(SHROOMMAX);
	        	if(shroomProbability >= SHROOMNUMBER && SHROOM) {
	        		shroom(wall1);
	        	}
	        	if (LEVEL == 4 && (backBG.LEVEL == 4 || frontBG.LEVEL == 4)) {
        		wall1.bars();
	        	}
	        	if (laser.isActive()) {
        		wallCount++;
	        	}
	        	if (score >= 75 && spawnPenguin) {
	        		spawnAnimal(wall1, penguin);
	        		spawnPenguin = false;
	        	}
	        	if (score >= 150 && spawnRam) {
	        		spawnAnimal(wall1, ram);
	        		spawnRam = false;
	        	}
	        	if (score >= 250 && spawnToast) {
	        		spawnAnimal(wall1, toast);
	        		spawnToast = false;
	      
	        	}
	        	if (score >= 350 && spawnCat) {
	        		spawnAnimal(wall1, cat);
	        		spawnCat = false;
	        	}
	        }
	        else if (wall2.isScrolledRight()) {
		        	
		        	wall2.reset(wall1.getX() - WALL_GAP - 22);
		        	shroomProbability = rand.nextInt(SHROOMMAX);
	        	if(shroomProbability >= SHROOMNUMBER && SHROOM) {
	        		shroom(wall2);
	        	}
	        	if (LEVEL == 4 && (backBG.LEVEL == 4 || frontBG.LEVEL == 4)) {
	        		wall2.bars();
	        	}
	        	if (laser.isActive()) {
	        		wallCount++;
	        	}
	        	if (score >= 75 && spawnPenguin) {
	        		spawnAnimal(wall1, penguin);
	        		spawnPenguin = false;
	        	}
	        	if (score >= 150 && spawnRam) {
	        		spawnAnimal(wall1, ram);
	        		spawnRam = false;
	        	}
	        	if (score >= 250 && spawnToast) {
	        		spawnAnimal(wall1, toast);
	        		spawnToast = false;
	      
	        	}
	        	if (score >= 350 && spawnCat) {
	        		spawnAnimal(wall1, cat);
	        		spawnCat = false;
	        	}
	        }
	        else if (wall3.isScrolledRight()) {
		     
		        	wall3.reset(wall2.getX() - WALL_GAP - 22);
		        	shroomProbability = rand.nextInt(SHROOMMAX);
	        	if(shroomProbability >= SHROOMNUMBER && SHROOM) {
	        		shroom(wall3);
	        	}
	        	if (LEVEL == 4 && (backBG.LEVEL == 4 || frontBG.LEVEL == 4)) {
	        		wall3.bars();
	        	}
	        	if (laser.isActive()) {
	        		wallCount++;
	        	}
	        	if (score >= 75 && spawnPenguin) {
	        		spawnAnimal(wall1, penguin);
	        		spawnPenguin = false;
	        	}
	        	if (score >= 150 && spawnRam) {
	        		spawnAnimal(wall1, ram);
	        		spawnRam = false;
	        	}
	        	if (score >= 250 && spawnToast) {
	        		spawnAnimal(wall1, toast);
	        		spawnToast = false;
	      
	        	}
	        	if (score >= 350 && spawnCat) {
	        		spawnAnimal(wall1, cat);
	        		spawnCat = false;
	        	}
	        }
	}
	
	public void fruitRight() {
		//Creates a new fruit at least one wall behind the last fruit, with a maximum of 4 behind
	        if (fruit1.isScrolledRight()) {
	        	reset1 = (rand.nextInt(FRUIT_GAP) + 2 * FRUIT_SPACE * -1);
	        	fruit1.reset(fruit3.getX() + reset1);
				callCombo1 = true;
	
	        }
	        else if (fruit2.isScrolledRight()) {
	        	reset2 = (rand.nextInt(FRUIT_GAP) + 2) * FRUIT_SPACE * -1;
	        	fruit2.reset(fruit1.getX() + reset2);
				callCombo2 = true;
	        	
				
	
	        }
	        else if (fruit3.isScrolledRight()) {
	        	reset3 = (rand.nextInt(FRUIT_GAP) + 2) * FRUIT_SPACE * -1;
	        	fruit3.reset(fruit2.getX() + reset3);
				callCombo3 = true;
				
	        }
	}
	
	public void grassRight() {
		 if (frontGrass.isScrolledRight()) {
	        	frontGrass.reset(backGrass.getX() - 136);
	        }
	        else if (backGrass.isScrolledRight()) {
	        	backGrass.reset(frontGrass.getX() - 136);
	        }
	        
	       if(frontDirt.isScrolledRight()) {
	        	frontDirt.reset(backDirt.getX() - 136);
	        }
	        else if (backDirt.isScrolledRight()) {
	        	backDirt.reset(frontDirt.getX() - 136);
	        }
	        
	       if(shroom.isScrolledRight()) {
	        	SHROOM = true;
	        }
	}
	
	public void spawnAnimal(Wall wall, Animals animal) {
		wallY = (int) wall.getY();
		wallX = (int) wall.getX();
		wallHeight = wall.getHeight();
		if (groundHeight - wallHeight - wallY >= wallY) {
			animalHeight = ((groundHeight - wallHeight - wallY) / 2) + wallHeight + wallY;
		}
		else {
			animalHeight = wallY / 2;
		}
		animal.restart(wallX + 4, animalHeight - 6, SCROLL_SPEED);
	}
	
	public void shroom(Wall wall) {
		wallY = (int) wall.getY();
		wallX = (int) wall.getX();
		wallHeight = wall.getHeight();
		if (groundHeight - wallHeight - wallY >= wallY) {
			shroomHeight = ((groundHeight - wallHeight - wallY) / 2) + wallHeight + wallY;
		}
		else {
			shroomHeight = wallY / 2;
		}
		SHROOM = false;
		shroom.restart(wallX + 4, shroomHeight, SCROLL_SPEED);
		
		callCombo4 = true;
	}
	

	
	public void wallCheck() {
		//ensures that none of the fruit will overlap with the bars.
		if (fruit1.wallCheck(wall1) && !wall1.isDestroy()) {
    		fruit1.reset(wall1.getTailX() + 29);
    	}
    	else if (fruit1.wallCheck(wall2) && !wall2.isDestroy()) {
	    	fruit1.reset(wall2.getTailX() + 29);
    	}
    	else if (fruit1.wallCheck(wall3) && !wall3.isDestroy()) {
	    	fruit1.reset(wall3.getTailX() + 29);
	    }
		
		if (fruit2.wallCheck(wall1) && !wall1.isDestroy()) {
    		fruit2.reset(wall1.getTailX() + 29);
    	}
    	else if (fruit2.wallCheck(wall2) && !wall2.isDestroy()) {
	    	fruit2.reset(wall2.getTailX() + 29);
	    }
    	else if (fruit2.wallCheck(wall3) && !wall3.isDestroy()) {
	    	fruit2.reset(wall3.getTailX() + 29);
	    }
		
		if (fruit3.wallCheck(wall1) && !wall1.isDestroy()) {
    		fruit3.reset(wall1.getTailX() + 29);
    	}
    	else if (fruit3.wallCheck(wall2) && !wall2.isDestroy()) {
	    	fruit3.reset(wall2.getTailX() + 29);
	    }
    	else if (fruit3.wallCheck(wall3) && !wall3.isDestroy()) {
	    	fruit3.reset(wall3.getTailX() + 29);
	    }
	}
	
    // The getters for our five instance variables
	
	public Background getFrontBG() {
		return frontBG;
	}
	
	public Background getBackBG() {
		return backBG;
	}
	
    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }
    
    public Laser getLaser() {
    	return laser;
    }
    
    public Dirt getFrontDirt() {
    	return frontDirt;
    }
    
    public Dirt getBackDirt() {
    	return backDirt;
    }

    public Wall getWall1() {
        return wall1;
    }

    public Wall getWall2() {
        return wall2;
    }

    public Wall getWall3() {
        return wall3;
    }
    
    public Fruit getFruit1() {
    	return fruit1;
    }
    
    public Fruit getFruit2() {
    	return fruit2;
    }
    
    public Fruit getFruit3() {
    	return fruit3;
    }
    
    public Shroom getShroom() {
    	return shroom;
    }
    
    public Animals getPenguin() {
    	return penguin;
    }
    
    public Animals getRam() {
    	return ram;
    }
    
    public Animals getToast() {
    	return toast;
    }
    
    public Animals getCat() {
    	return cat;
    }
    
    public void ate1() {
    	fruit1.ate();
    }
    
    public void ate2() {
    	fruit2.ate();
    }
    
    public void ate3() {
    	fruit3.ate();
    }
    
    public void ateShroom() {
    	shroom.ate();
    }
    
    public void atePenguin() {
    	penguin.ate();
    }
    
    public void ateRam() {
    	ram.ate();
    }
    
    public void ateToast() {
    	toast.ate();
    }
    
    public void ateCat() {
    	cat.ate();
    }
   
    
    public void stop() {
    	frontGrass.stop();
    	backGrass.stop();
    	wall1.stop();
    	wall2.stop();
    	wall3.stop();
    	fruit1.stop();
    	fruit2.stop();
    	fruit3.stop();
    	shroom.stop();
    	frontBG.stop();
    	backBG.stop();
    	frontDirt.stop();
    	backDirt.stop();
    	penguin.stop();
    	ram.stop();
    	toast.stop();
    	cat.stop();
    }
 
    
    public boolean collides(BearCopter bear) {
    	return(wall1.collides(bear) || wall2.collides(bear) || wall3.collides(bear));
    }
    
    public boolean collidesFruit1(BearCopter bear) {
    	return(fruit1.collides(bear));
    }
    
    public boolean collidesFruit2(BearCopter bear) {
    	return(fruit2.collides(bear));
    }
    
    public boolean collidesFruit3(BearCopter bear) {
    	return(fruit3.collides(bear));
    	
    }
    
    public boolean collidesShroom(BearCopter bear) {
    	return(shroom.collides(bear));
    }
    
    public boolean laserHit(BearCopter bear) {
    	return(laser.collides(bear));
    }
    
    public boolean collidesPenguin(BearCopter bear) {
    	return(penguin.collides(bear));
    }
    
    public boolean collidesRam(BearCopter bear) {
    	return(ram.collides(bear));
    }
    
    public boolean collidesToast(BearCopter bear) {
    	return(toast.collides(bear));
    }
    
    public boolean collidesCat(BearCopter bear) {
    	return(cat.collides(bear));
    }
    
    public boolean animalCollidesShroom(Shroom shroom) {
    	if (penguin.shroomCollide(shroom) || ram.shroomCollide(shroom) || toast.shroomCollide(shroom) || cat.shroomCollide(shroom)) {
    		return true;
    	}
    	return false;
    }
    
    
    public void restart() {
    	spawnPenguin = true;
    	spawnRam = true;
    	spawnToast = true;
    	spawnCat = true;
    	
    	penguin.restart(0, -100, SCROLL_SPEED);
    	ram.restart(0, -100, SCROLL_SPEED);
    	toast.restart(0, -100, SCROLL_SPEED);
    	cat.restart(0, -100, SCROLL_SPEED);
    	
    	level4 = false;
    	frontBG.level1();
    	backBG.level1();
    	
    	laser.restart();
    	wallCount = 0;
    	isFiring = false;
    	fire = false;
    	
    	SCROLL_SPEED = -49;
    	frontGrass.restart(0, SCROLL_SPEED);
    	backGrass.restart(frontGrass.getTailX(), SCROLL_SPEED);
    	frontDirt.restart(0, SCROLL_SPEED);
    	backDirt.restart(frontDirt.getTailX(), SCROLL_SPEED);
    	frontBG.restart(0, BG_SPEED);
    	backBG.restart(frontBG.getTailX(), BG_SPEED);
    	wall1.restart(210, SCROLL_SPEED);
    	wall2.restart(wall1.getTailX() + WALL_GAP, SCROLL_SPEED);
    	wall3.restart(wall2.getTailX() + WALL_GAP, SCROLL_SPEED);
    	
    	//makes new fruit with the same restrictions as the other ones
    	restart1 = (float) (261 + rand.nextInt(FRUIT_GAP) + 1 * FRUIT_SPACE);
    	fruit1.restart(restart1, SCROLL_SPEED);
    	
    	restart2 = fruit1.getX() + (rand.nextInt(FRUIT_GAP) + 1) * FRUIT_SPACE;
    	fruit2.restart(restart2, SCROLL_SPEED);
    	
    	restart3 = fruit2.getX() + (rand.nextInt(FRUIT_GAP) + 1) * FRUIT_SPACE;
    	fruit3.restart(restart3, SCROLL_SPEED);
    	
    	shroom.restart(0, -50, SCROLL_SPEED);
    	
		callCombo1 = true;
		callCombo2 = true;
		callCombo3 = true;
		callCombo4 = true;
		
		USESCROLLEDLEFT = true;
		
		SHROOMNUMBER = SHROOMNUMBERSAVE;
		
    }
    
    
    public void reverse(boolean high) {
    	if(USESCROLLEDLEFT) {
    		USESCROLLEDLEFT = false;
    		SCROLL_SPEED = 49;
    		shroomChange();
    	}
    	else if (!USESCROLLEDLEFT) {
    		USESCROLLEDLEFT = true;
    		SCROLL_SPEED = -49;
    		shroomRestore();
    	}
    	
    	wall1.reverse(high);
    	wall2.reverse(high);
    	wall3.reverse(high);
    	fruit1.reverse(high);
    	fruit2.reverse(high);
    	fruit3.reverse(high);
    	backGrass.reverse(high);
    	frontGrass.reverse(high);
    	shroom.reverse(high);
    	frontDirt.reverse(high);
    	backDirt.reverse(high);
    	penguin.reverse(high);
    	ram.reverse(high);
    	toast.reverse(high);
    	cat.reverse(high);
    }
    
    public void shroomChange() {
    	SHROOMNUMBER = 100;
    }
    
    public void shroomRestore() {
    	SHROOMNUMBER = SHROOMNUMBERSAVE;
    }

}
