package com.hubbabubbagump.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture texture;
	public static Texture wallMid;
	public static Texture wallEnd;
	public static Texture barMid;
	public static Texture barEnd;
	public static Texture bg1;
	public static Texture bg2;
	public static Texture bg12;
	public static Texture bg22;
	public static Texture bg13;
	public static Texture bg23;
	public static Texture bgEnd;
	public static Texture startButton;
	public static Texture scoreButton;
	public static Texture menuButton;
	public static Texture startClicked;
	public static Texture scoreClicked;
	public static Texture menuClicked;
	public static Texture gameOverScreen;
	public static Texture startText;
	public static Texture fruit;
	public static Texture scoreSheet;
	public static Texture shrooms;
	public static Texture rainbowA;
	public static Texture rainbowB;
	public static Texture rainbowC;
	public static Texture splashScreen;
	public static Texture grassArea;
	public static Texture dirtArea;
	public static Texture laser;
	public static Texture laserplosion;
	public static Texture bigLaser;
	public static Texture burntPipe;
	public static Texture bearcopter;
	public static Texture gunPiece;
	public static Texture gunrar;
	public static Texture e1;
	public static Texture e2;
	public static Texture e3;
	public static Texture e4;
	public static Texture miniship;
	public static Texture c1, c2, c3; //cave
	public static Texture ci1, ci2, ci3; //dark city
	public static Texture i1, i2, i3; //ice
	public static Texture cats;
	public static Texture rams;
	public static Texture toasts;
	public static Texture penguins;
	public static Texture volume, volumes;
	public static Texture paused;
	public static Texture charSelect, charSelectDown;
	public static Texture border;
	public static Texture iced, iceg;
	public static Texture grassdeux;
	public static Texture darkgrass;
	public static Texture cages;
	public static Texture storyPic;
	
	public static TextureRegion backgroundLight;
	public static TextureRegion backgroundLight2;
	public static TextureRegion backgroundLight3;
	public static TextureRegion backgroundDark;
	public static TextureRegion backgroundDark2;
	public static TextureRegion backgroundDark3;
	public static TextureRegion backgroundDungeon;
	public static TextureRegion wallTop;
	public static TextureRegion wallBottom;
	public static TextureRegion barTop;
	public static TextureRegion barBottom;
	public static TextureRegion bar;
	public static TextureRegion grass;
	public static TextureRegion gameOver;
	public static TextureRegion pauseText;
	public static TextureRegion apple;
	public static TextureRegion orange;
	public static TextureRegion banana;
	public static TextureRegion highScores;
	public static TextureRegion redShroom;
	public static TextureRegion greenShroom;
	public static TextureRegion rainbow1;
	public static TextureRegion rainbow2;
	public static TextureRegion rainbow3;
	public static TextureRegion splash;
	public static TextureRegion dirt;
	public static TextureRegion start;
	public static TextureRegion score;
	public static TextureRegion menu;
	public static TextureRegion startDown;
	public static TextureRegion scoreDown;
	public static TextureRegion menuDown;
	public static TextureRegion beam;
	public static TextureRegion beamplosion;
	public static TextureRegion burntWall;
	public static TextureRegion bearCopter;
	public static TextureRegion gunFrag;
	public static TextureRegion gun;
	public static TextureRegion explosion1;
	public static TextureRegion explosion2;
	public static TextureRegion explosion3;
	public static TextureRegion explosion4;
	public static TextureRegion peeler;
	public static TextureRegion bigBeam;
	public static TextureRegion cave1, cave2, cave3;
	public static TextureRegion city1, city2, city3;
	public static TextureRegion ice1, ice2, ice3;
	public static TextureRegion cat, catdown;
	public static TextureRegion ram, ramdown;
	public static TextureRegion toast, toastdown;
	public static TextureRegion penguin, penguindown;
	public static TextureRegion volumeON, volumeOFF;
	public static TextureRegion pause;
	public static TextureRegion charButton, charButtonDown;
	public static TextureRegion charBorder;
	public static TextureRegion iceground, icedirt;
	public static TextureRegion grass2;
	public static TextureRegion grassdark;
	public static TextureRegion cage;
	public static TextureRegion story;
	
	public static Animation rainbowAnimation;
	public static Animation bearBlink;
	public static Animation downBlink;
	public static Animation explosion;
	
	public static TextureRegion bear, bearDown, bearBlank;
	
	public static TextureRegion brick;
	
	public static Sound up;
	public static Sound dead;
	public static Sound eat;
	public static Sound pew;
	
	public static Music BGM;
	public static Music trippy;
	public static Music sad;
	
	public static BitmapFont font;
	public static BitmapFont fontSmall;
	
	public static Preferences prefs;
	
	//Pretty much self explanatory
	public static void load() {
		//loads music/sounds
		up = Gdx.audio.newSound(Gdx.files.internal("data/clapreverse.wav")); //sounds when bear goes up
		dead = Gdx.audio.newSound(Gdx.files.internal("data/failfare.mp3")); //sound when bear dies
		BGM = Gdx.audio.newMusic(Gdx.files.internal("data/booberrypixies.mp3")); //BGM
		eat = Gdx.audio.newSound(Gdx.files.internal("data/fruit.wav")); //sound of eating fruit
		trippy = Gdx.audio.newMusic(Gdx.files.internal("data/sonicattack.wav")); //eatin' shrooms
		pew = Gdx.audio.newSound(Gdx.files.internal("data/laser.wav")); //pewpewpewpewpew
		sad = Gdx.audio.newMusic(Gdx.files.internal("data/sadbgm.wav"));
		
		//Retrieves Images.png from bear-a-copter-android/assets/data
		storyPic = new Texture(Gdx.files.internal("data/story.png"));
		storyPic.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		cages = new Texture(Gdx.files.internal("data/cage.png"));
		cages.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		darkgrass = new Texture(Gdx.files.internal("data/grassdark.png"));
		darkgrass.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		grassdeux = new Texture(Gdx.files.internal("data/grass2.png"));
		grassdeux.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		iced = new Texture(Gdx.files.internal("data/icedirt.png"));
		iced.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		iceg = new Texture(Gdx.files.internal("data/iceground.png"));
		iceg.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		border = new Texture(Gdx.files.internal("data/charBorder.png"));
		border.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		charSelect = new Texture(Gdx.files.internal("data/charselect.png"));
		charSelect.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		charSelectDown = new Texture(Gdx.files.internal("data/charselectclicked.png"));
		charSelectDown.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		paused = new Texture(Gdx.files.internal("data/paused.png"));
		paused.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		c1 = new Texture(Gdx.files.internal("data/cave1.png"));
		c1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		c2 = new Texture(Gdx.files.internal("data/cave2.png"));
		c2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		c3 = new Texture(Gdx.files.internal("data/cave3.png"));
		c3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		ci1 = new Texture(Gdx.files.internal("data/city1.png"));
		ci1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		ci2 = new Texture(Gdx.files.internal("data/city2.png"));
		ci2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		ci3 = new Texture(Gdx.files.internal("data/city3.png"));
		ci3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		i1 = new Texture(Gdx.files.internal("data/ice1.png"));
		i1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		i2 = new Texture(Gdx.files.internal("data/ice2.png"));
		i2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		i3 = new Texture(Gdx.files.internal("data/ice3.png"));
		i3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		cats = new Texture(Gdx.files.internal("data/cats.png"));
		cats.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		rams = new Texture(Gdx.files.internal("data/rams.png"));
		rams.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		penguins = new Texture(Gdx.files.internal("data/penguins.png"));
		penguins.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		toasts = new Texture(Gdx.files.internal("data/toasts.png"));
		toasts.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		volume = new Texture(Gdx.files.internal("data/volumeON.png"));
		volume.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		volumes = new Texture(Gdx.files.internal("data/volumeOFF.png"));
		volumes.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		menuButton = new Texture(Gdx.files.internal("data/menubutton.png"));
		menuButton.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		startClicked = new Texture(Gdx.files.internal("data/startclicked.png"));
		startClicked.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		scoreClicked = new Texture(Gdx.files.internal("data/scoreclicked.png"));
		scoreClicked.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		menuClicked = new Texture(Gdx.files.internal("data/menuclicked.png"));
		menuClicked.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		wallMid = new Texture(Gdx.files.internal("data/pipemid.png"));
		wallMid.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		wallEnd = new Texture(Gdx.files.internal("data/pipeend.png"));
		wallEnd.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		barMid = new Texture(Gdx.files.internal("data/barsmid.png"));
		barMid.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		barEnd = new Texture(Gdx.files.internal("data/barsend.png"));
		barEnd.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		burntPipe= new Texture(Gdx.files.internal("data/burntpipe.png"));
		burntPipe.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		startButton = new Texture(Gdx.files.internal("data/startCircle.png"));
		startButton.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		scoreButton = new Texture(Gdx.files.internal("data/scoreButton.png"));
		scoreButton.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		startText = new Texture(Gdx.files.internal("data/startText.png"));
		startText.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		gameOverScreen = new Texture(Gdx.files.internal("data/GameOver.png"));
		gameOverScreen.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		scoreSheet = new Texture(Gdx.files.internal("data/scorePage.png"));
		scoreSheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		fruit = new Texture(Gdx.files.internal("data/fruit.png"));
		fruit.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		shrooms = new Texture(Gdx.files.internal("data/shrooms.png"));
		shrooms.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg1 = new Texture(Gdx.files.internal("data/backgroundlight.png"));
		bg1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg12 = new Texture(Gdx.files.internal("data/backgroundlight2.png"));
		bg12.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg13 = new Texture(Gdx.files.internal("data/backgroundlight3.png"));
		bg13.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg2 = new Texture(Gdx.files.internal("data/backgrounddark.png"));
		bg2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg22 = new Texture(Gdx.files.internal("data/backgrounddark2.png"));
		bg22.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg23 = new Texture(Gdx.files.internal("data/backgrounddark3.png"));
		bg23.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bgEnd = new Texture(Gdx.files.internal("data/backgroundlight4.png"));
		bgEnd.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		rainbowA = new Texture(Gdx.files.internal("data/rainbow1.png"));
		rainbowA.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		rainbowB = new Texture(Gdx.files.internal("data/rainbow2.png"));
		rainbowB.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		rainbowC = new Texture(Gdx.files.internal("data/rainbow3.png"));
		rainbowC.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		splashScreen = new Texture(Gdx.files.internal("data/splash.png"));
		splashScreen.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		grassArea = new Texture(Gdx.files.internal("data/grass.png"));
		grassArea.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		dirtArea = new Texture(Gdx.files.internal("data/dirt.png"));
		dirtArea.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		laser = new Texture(Gdx.files.internal("data/beam.png"));
		laser.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		laserplosion = new Texture(Gdx.files.internal("data/beamplosion.png"));
		laserplosion.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bearcopter = new Texture(Gdx.files.internal("data/bearcopter.png"));
		bearcopter.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		gunPiece = new Texture(Gdx.files.internal("data/gunpiece.png"));
		gunPiece.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		gunrar = new Texture(Gdx.files.internal("data/gun.png"));
		gunrar.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		e1 = new Texture(Gdx.files.internal("data/explosion1.png"));
		e1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		e2 = new Texture(Gdx.files.internal("data/explosion2.png"));
		e2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		e3 = new Texture(Gdx.files.internal("data/explosion3.png"));
		e3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		e4 = new Texture(Gdx.files.internal("data/explosion4.png"));
		e4.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		miniship = new Texture(Gdx.files.internal("data/peeler.png"));
		miniship.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bigLaser =  new Texture(Gdx.files.internal("data/laser.png"));
		bigLaser.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		story = new TextureRegion(storyPic, 0, 0, 500, 750);
		story.flip(false, true);
		
		cage = new TextureRegion(cages, 0, 0, 15, 14);
		cage.flip(false, true);
		
		grassdark = new TextureRegion(darkgrass, 0, 0, 136, 11);
		grassdark.flip(false, true);
		
		grass2 = new TextureRegion(grassdeux, 0, 0, 136, 11);
		grass2.flip(false, true);
		
		icedirt = new TextureRegion(iced, 0, 0, 136, 50);
		icedirt.flip(false, true);
		
		iceground = new TextureRegion(iceg, 0, 0, 136, 11);
		iceground.flip(false, true);
		
		charBorder = new TextureRegion(border, 0, 0, 23, 23);
		charBorder.flip(false, true);
		
		charButton = new TextureRegion(charSelect, 0, 0, 21, 21);
		charButton.flip(false, true);
		
		charButtonDown = new TextureRegion(charSelectDown, 0, 0, 21, 21);
		charButtonDown.flip(false, true);
		
		pause = new TextureRegion(paused, 0, 0, 11, 11);
		pause.flip(false, true);
		
		volumeON = new TextureRegion(volume, 0, 0, 15, 15);
		volumeON.flip(false, true);
		
		volumeOFF = new TextureRegion(volumes, 0, 0, 15, 15);
		volumeOFF.flip(false, true);
		
		cave1 = new TextureRegion(c1, 0, 0, 136, 200);
		cave1.flip(false, true);
		
		cave2 = new TextureRegion(c2, 0, 0, 136, 200);
		cave2.flip(false, true);
		
		cave3 = new TextureRegion(c3, 0, 0, 136, 200);
		cave3.flip(false, true);
		
		city1 = new TextureRegion(ci1, 0, 0, 136, 200);
		city1.flip(false, true);
		
		city2 = new TextureRegion(ci2, 0, 0, 136, 200);
		city2.flip(false, true);
		
		city3 = new TextureRegion(ci3, 0, 0, 136, 200);
		city3.flip(false, true);
		
		ice1 = new TextureRegion(i1, 0, 0, 136, 200);
		ice1.flip(false, true);
		
		ice2 = new TextureRegion(i2, 0, 0, 136, 200);
		ice2.flip(false, true);
		
		ice3 = new TextureRegion(i3, 0, 0, 136, 200);
		ice3.flip(false, true);
		
		cat = new TextureRegion(cats, 0, 0, 18, 16);
		cat.flip(false, true);
		
		catdown = new TextureRegion(cats, 18, 0, 18, 16);
		catdown.flip(false, true);
		
		ram = new TextureRegion(rams, 0, 0, 18, 16);
		ram.flip(false, true);
		
		ramdown = new TextureRegion(rams, 18, 0, 18, 16);
		ramdown.flip(false, true);
		
		penguin = new TextureRegion(penguins, 0, 0, 18, 16);
		penguin.flip(false, true);
		
		penguindown = new TextureRegion(penguins, 18, 0, 18, 16);
		penguindown.flip(false, true);
		
		toast = new TextureRegion(toasts, 0, 0, 18, 16);
		toast.flip(false, true);
		
		toastdown = new TextureRegion(toasts, 18, 0, 18, 16);
		toastdown.flip(false, true);
		
		bigBeam = new TextureRegion(bigLaser, 0, 0, 3, 3);
		bigBeam.flip(false, true);
		
		explosion1 = new TextureRegion(e1, 0, 0, 11, 11);
		explosion1.flip(false, true);
		
		explosion2 = new TextureRegion(e2, 0, 0, 11, 11);
		explosion2.flip(false, true);
		
		explosion3 = new TextureRegion(e3, 0, 0, 13, 13);
		explosion3.flip(false, true);
		
		explosion4 = new TextureRegion(e4, 0, 0, 13, 13);
		explosion4.flip(false, true);
		
		peeler = new TextureRegion(miniship, 0, 0, 11, 11);
		peeler.flip(false, true);
		
		gun = new TextureRegion(gunrar, 0, 0, 12, 7);
		gun.flip(false, true);
		
		gunFrag = new TextureRegion(gunPiece, 0, 0, 10, 10);
		gunFrag.flip(false, true);
		
		bearCopter = new TextureRegion(bearcopter, 0, 0, 125, 12);
		bearCopter.flip(false, true);
		
		beam = new TextureRegion(laser, 0, 0, 2, 6);
		beam.flip(false, true);
		
		beamplosion = new TextureRegion(laserplosion, 0, 0, 8, 5);
		beamplosion.flip(false, true);
		
		dirt = new TextureRegion(dirtArea, 0, 0, 136, 50);
		dirt.flip(false, true);
		
		splash = new TextureRegion(splashScreen, 0, 0, 136, 64);
		splash.flip(false, true);
		
		rainbow1 = new TextureRegion(rainbowA, 0, 0, 136, 200);
		rainbow1.flip(false, true);
		
		rainbow2 = new TextureRegion(rainbowB, 0, 0, 136, 200);
		rainbow2.flip(false, true);
		
		rainbow3 = new TextureRegion(rainbowC, 0, 0, 136, 200);
		rainbow3.flip(false, true);
		
		redShroom = new TextureRegion(shrooms, 0, 0, 10, 10);
		redShroom.flip(false, true);
		
		greenShroom = new TextureRegion(shrooms, 10, 0, 10, 10);
		greenShroom.flip(false, true);
		
		start = new TextureRegion(startButton, 0, 0, 21, 21);
		start.flip(false, true);
		
		highScores = new TextureRegion(scoreSheet, 0, 0, 100, 80);
		highScores.flip(false, true);
		
		score = new TextureRegion(scoreButton, 0, 0, 21, 21);
		score.flip(false, true);
		
		menu = new TextureRegion(menuButton, 0, 0, 21, 21);
		menu.flip(false, true);
		
		startDown = new TextureRegion(startClicked, 0, 0, 21, 21);
		startDown.flip(false, true);
		
		scoreDown = new TextureRegion(scoreClicked, 0, 0, 21, 21);
		scoreDown.flip(false, true);
		
		menuDown = new TextureRegion(menuClicked, 0, 0, 21, 21);
		menuDown.flip(false, true);
		
		pauseText = new TextureRegion(startText, 0, 0, 61, 10);
		pauseText.flip(false, true);
		
		gameOver = new TextureRegion(gameOverScreen, 0, 0, 100, 80);
		gameOver.flip(false, true);
		
		apple = new TextureRegion(fruit, 10, 0, 10, 9);
		apple.flip(false, true);
		banana = new TextureRegion(fruit, 0, 0, 10, 10);
		banana.flip(false, true);
		orange = new TextureRegion(fruit, 20, 0, 8, 8);
		orange.flip(false, true);
		
		//Creates a new texture using image starting at (0,0) 
		//with 136 width and 43 height.
		backgroundLight = new TextureRegion(bg1, 0, 0, 136, 200);
		backgroundLight.flip(false, true);
		
		backgroundLight2 = new TextureRegion(bg12, 0, 0, 136, 200);
		backgroundLight2.flip(false, true);
		
		backgroundLight3 = new TextureRegion(bg13, 0, 0, 136, 200);
		backgroundLight3.flip(false, true);
		
		backgroundDark = new TextureRegion( bg2, 0, 0, 136, 200);
		backgroundDark.flip(false, true);
		
		backgroundDark2 = new TextureRegion(bg22, 0, 0, 136, 200);
		backgroundDark2.flip(false, true);
		
		backgroundDark3 = new TextureRegion(bg23, 0, 0, 136, 200);
		backgroundDark3.flip(false, true);
		
		backgroundDungeon = new TextureRegion(bgEnd, 0, 0, 136, 200);
		backgroundDungeon.flip(false, true);
				
		grass = new TextureRegion(grassArea, 0, 0, 136, 11);
		grass.flip(false, true);
		
		bearDown = new TextureRegion(texture, 136, 16, 18, 16);
		bearDown.flip(false, true);
		
		bear = new TextureRegion(texture, 136, 0, 18, 16);
		bear.flip(false, true);
		
		bearBlank = new TextureRegion(texture, 220, 0, 18, 16);
		bearBlank.flip(false, true);
		
		brick = new TextureRegion(wallMid, 0, 0, 22, 5);
		brick.flip(false, true);
		
		wallTop = new TextureRegion(wallEnd, 0, 0, 22, 5);
		wallTop.flip(false, true);
		
		wallBottom = new TextureRegion(wallEnd, 0, 0, 22, 5);
		wallBottom.flip(false, false);
		
		bar = new TextureRegion(barMid, 0, 0, 22, 5);
		bar.flip(false, true);
		
		barTop = new TextureRegion(barEnd, 0, 0, 22, 5);
		barTop.flip(false, true);
		
		barBottom = new TextureRegion(barEnd, 0, 0, 22, 5);
		barBottom.flip(false, false);
		
		burntWall = new TextureRegion(burntPipe, 0, 0, 22, 5);
		burntWall.flip(false, true);
		
		font = new BitmapFont(Gdx.files.internal("data/bitfont.fnt"));
		font.setScale(.5f, -.5f);
		fontSmall = new BitmapFont(Gdx.files.internal("data/bitfont.fnt"));
		fontSmall.setScale(.30f, -.30f);
		
		TextureRegion[] rainbows = {rainbow1, rainbow2, rainbow3};
		rainbowAnimation = new Animation(0.06f, rainbows);
		rainbowAnimation.setPlayMode(Animation.PlayMode.LOOP);
		
		TextureRegion[] bears = {bear, bearBlank};
		bearBlink = new Animation(0.06f, bears);
		bearBlink.setPlayMode(Animation.PlayMode.LOOP);
		
		TextureRegion[] explosions = {explosion1, explosion2, explosion3, explosion4};
		explosion = new Animation(0.25f, explosions);
		explosion.setPlayMode(Animation.PlayMode.NORMAL);
		
		TextureRegion[] bearDowns = {bearDown, bearBlank};
		downBlink = new Animation(0.06f, bearDowns);
		downBlink.setPlayMode(Animation.PlayMode.LOOP);
		
		prefs = Gdx.app.getPreferences("BearCopter");
		
		//if there isnt a file called highScore, secondHighScore, etc,
		//creates one with in initial value of 0
		if(!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
		if(!prefs.contains("secondHighScore")) {
			prefs.putInteger("secondHighScore", 0);
		}
		if(!prefs.contains("thirdHighScore")) {
			prefs.putInteger("thirdHighScore", 0);
		}
		if(!prefs.contains("currentAvatar")) {
			prefs.putInteger("currentAvatar", 0);
		}
		if(!prefs.contains("catUnlocked")) {
			prefs.putBoolean("catUnlocked", false);
		}
		if(!prefs.contains("ramUnlocked")) {
			prefs.putBoolean("ramUnlocked", false);
		}
		if(!prefs.contains("toastUnlocked")) {
			prefs.putBoolean("toastUnlocked", false);
		}
		if(!prefs.contains("penguinUnlocked")) {
			prefs.putBoolean("toastUnlocked", false);
		}
		
	}
	
	
	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}
	
	public static void setSecondHighScore(int val) {
		prefs.putInteger("secondHighScore", val);
		prefs.flush();
	}
	
	public static void setThirdHighScore(int val) {
		prefs.putInteger("thirdHighScore", val);
		prefs.flush();
	}
	
	public static void setCurrentAvatar(int val) {
		prefs.putInteger("currentAvatar", val);
		prefs.flush();
		/*
		 * bear = 0
		 * penguin = 1
		 * ram = 2
		 * toast = 3
		 * cat = 4
		 */
	}
	
	public static void unlockCat() {
		prefs.putBoolean("catUnlocked", true);
		prefs.flush();
	}
	
	public static void unlockRam() {
		prefs.putBoolean("ramUnlocked", true);
		prefs.flush();
	}

	public static void unlockToast() {
		prefs.putBoolean("toastUnlocked", true);
		prefs.flush();
	}
	
	public static void unlockPenguin() {
		prefs.putBoolean("penguinUnlocked", true);
		prefs.flush();
	}
	
	public static int getCurrentAvatar() {
		return prefs.getInteger("currentAvatar");
	}
	
	public static boolean catUnlocked() {
		return prefs.getBoolean("catUnlocked");
	}
	
	public static boolean ramUnlocked() {
		return prefs.getBoolean("ramUnlocked");
	}
	
	public static boolean toastUnlocked() {
		return prefs.getBoolean("toastUnlocked");
	}
	
	public static boolean penguinUnlocked() {
		return prefs.getBoolean("penguinUnlocked");
	}
	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
	
	public static int getSecondHighScore() {
		return prefs.getInteger("secondHighScore");
	}
	
	public static int getThirdHighScore() {
		return prefs.getInteger("thirdHighScore");
	}
	
	public static void dispose() {
		texture.dispose();
		startButton.dispose();
		fruit.dispose();
		gameOverScreen.dispose();
		scoreButton.dispose();
		startText.dispose();
		scoreSheet.dispose();
		menuButton.dispose();
		startClicked.dispose();
		scoreClicked.dispose();
		menuClicked.dispose();
		shrooms.dispose();
		rainbowA.dispose();
		rainbowB.dispose();
		rainbowC.dispose();
		wallMid.dispose();
		wallEnd.dispose();
		bg1.dispose();
		bg12.dispose();
		bg13.dispose();
		bg2.dispose();
		bg22.dispose();
		bg23.dispose();
		bgEnd.dispose();
		splashScreen.dispose();
		grassArea.dispose();
		dirtArea.dispose();
		laser.dispose();
		laserplosion.dispose();
		burntPipe.dispose();
		bearcopter.dispose();
		barMid.dispose();
		barEnd.dispose();
		gunPiece.dispose();
		gunrar.dispose();
		e1.dispose();
		e2.dispose();
		e3.dispose();
		e4.dispose();
		miniship.dispose();
		bigLaser.dispose();
		c1.dispose();
		c2.dispose();
		c3.dispose();
		ci1.dispose();
		ci2.dispose();
		ci3.dispose();
		i1.dispose();
		i2.dispose();
		i3.dispose();
		cats.dispose();
		penguins.dispose();
		rams.dispose();
		toasts.dispose();
		paused.dispose();
		volume.dispose();
		volumes.dispose();
		charSelect.dispose();
		charSelectDown.dispose();
		border.dispose();
		iced.dispose();
		iceg.dispose();

		up.dispose();
		dead.dispose();
		BGM.dispose();
		eat.dispose();
		trippy.dispose();
		pew.dispose();
		sad.dispose();
		
		font.dispose();
		fontSmall.dispose();
	}

}
