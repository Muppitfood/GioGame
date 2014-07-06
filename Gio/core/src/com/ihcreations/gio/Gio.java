package com.ihcreations.gio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ihcreations.gio.screens.StartScreen;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;

public class Gio extends Game {

	// Gamepad Constants
	public static final int GAMEPAD_LEFT = -1;
	public static final int GAMEPAD_RIGHT = 1;
	public static final int GAMEPAD_UP = 1;
	public static final int GAMEPAD_DOWN = -1;
	
	// Global Game variables
	public SpriteBatch batch;
	public BitmapFont font;
	public Controller gamepad;
	public boolean game_started;
	public ShapeRenderer floor;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		// Libgdx default arial font.
		font = new BitmapFont();
		
		if(Controllers.getControllers().size >= 1) {
			gamepad = Controllers.getControllers().first();
		}
		
		floor = new ShapeRenderer();
		floor.setColor(Color.BLACK);
		
		game_started = false;
		
		this.setScreen(new StartScreen(this));
	}
	
	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
	
}
