package com.hubbabubbagump.GameObjects;

public class ScrollHandler {
	
	
    private Grass frontGrass, backGrass;
    private Wall wall1, wall2, wall3;
    
    //old speed value -59
    public static final int SCROLL_SPEED = -49;
    public static final int WALL_GAP = 49;

    // Constructor receives a float that tells us where we need to create our 
    // Grass and Wall objects.
    public ScrollHandler(float yPos) {
    	//constructs the first frontGrass and backGrass
    	frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
    	backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11, SCROLL_SPEED);
    	
    	//x position, y position, width, height, scroll speed
    	//constructs first 3 walls
    	wall1 = new Wall(210, 20, 22, 60, SCROLL_SPEED, yPos);
    	wall2 = new Wall(wall1.getTailX() + WALL_GAP, 40 ,22, 70, SCROLL_SPEED, yPos);
    	wall3 = new Wall(wall2.getTailX() + WALL_GAP, 20, 22, 60, SCROLL_SPEED, yPos);
    }
    
    public void update(float delta) {
        frontGrass.update(delta);
        backGrass.update(delta);
        wall1.update(delta);
        wall2.update(delta);
        wall3.update(delta);
        
        //wall 1 retrieves wall 2, wall 2 retrieves wall 3, etc.
        //Same for grass.
        if (wall1.isScrolledLeft()) {
        	wall1.reset(wall3.getTailX() + WALL_GAP);
        }
        else if (wall2.isScrolledLeft()) {
        	wall2.reset(wall1.getTailX() + WALL_GAP);
        }
        else if (wall3.isScrolledLeft()) {
        	wall3.reset(wall2.getTailX() + WALL_GAP);
        }
        
        if (frontGrass.isScrolledLeft()) {
        	frontGrass.reset(backGrass.getTailX());
        }
        else if (backGrass.isScrolledLeft()) backGrass.reset(frontGrass.getTailX());
    }

    // The getters for our five instance variables
    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
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
    
    public void stop() {
    	frontGrass.stop();
    	backGrass.stop();
    	wall1.stop();
    	wall2.stop();
    	wall3.stop();
    }
    
    public boolean collides(BearCopter bear) {
    	return(wall1.collides(bear) || wall2.collides(bear) || wall3.collides(bear));
    }

}
