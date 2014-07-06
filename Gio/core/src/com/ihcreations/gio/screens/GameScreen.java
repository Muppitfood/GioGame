package com.ihcreations.gio.screens;

import com.ihcreations.gio.Gio;
import com.ihcreations.gio.characters.Character;
import com.ihcreations.gio.characters.CharacterState;
import com.ihcreations.gio.controls.Xbox360Pad;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;

public class GameScreen implements Screen, ControllerListener, InputProcessor {
	// Game Constants
	final Gio m_game;
	
	// Global game variables
	Character gioman;
	FPSLogger fps_logger;
	

	// When game screen is first started
	public GameScreen(final Gio game) {
		// Set game
		this.m_game = game;
		
		// Initialize Character
		gioman = new Character(new Point(100, 100), 30, 30, new Texture(Gdx.files.internal("meatboy.jpg")));
		
		// Initialize Logger
		fps_logger = new FPSLogger();
		
		// Select first controller in array
		if(m_game.gamepad != null) {
			// Add Gamepad listener to this screen
			m_game.gamepad.addListener(this);
		}
		
		
		// Add Input listener to this screen
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void render(float delta) {
		// Clear Screen and write background color
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Draw Gioman
		m_game.batch.begin();
		m_game.batch.draw(
				gioman.getSprite(), 
				gioman.getCenter().x, 
				gioman.getCenter().y, 
				gioman.getWidth(), 
				gioman.getHeight());
		m_game.batch.end();
		
		m_game.floor.begin(ShapeType.Line);
		m_game.floor.line(0, 100, Gdx.graphics.getWidth(), 100);
		m_game.floor.end();
		
		// Move Gioman
		gioman.updateMovement();
		
		// Log Framerate
		fps_logger.log();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
			case Input.Keys.RIGHT:
			case Input.Keys.D:
				gioman.startMovingRight();
				break;
			case Input.Keys.LEFT:
			case Input.Keys.A:
				gioman.startMovingLeft();
				break;
			case Input.Keys.SHIFT_LEFT:
				gioman.accelerate();
				break;
			case Input.Keys.SPACE:
				if(!gioman.isMovingVertical())
					gioman.startJump();
				break;
			default:
				System.out.println("Unimplemented keycode");
				break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
			case Input.Keys.RIGHT:
			case Input.Keys.D:
				if(gioman.getState().getHorizontalDirection() == CharacterState.RIGHT)
					gioman.stopHorizontalMovement();
				break;
			case Input.Keys.LEFT:
			case Input.Keys.A:
				if(gioman.getState().getHorizontalDirection() == CharacterState.LEFT)
					gioman.stopHorizontalMovement();
				break;
			case Input.Keys.SHIFT_LEFT:
				gioman.stopAcceleration();
				break;
			case Input.Keys.SPACE:
				if(gioman.getState().isJumping())
					gioman.getState().setIsJumping(false);
				break;
			default:
				System.out.println("Unimplemented keycode release.");
				break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		if (buttonCode == Xbox360Pad.BUTTON_A && !gioman.isMovingVertical()) {
			gioman.startJump();
		}
		return false;
	}

	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		if (buttonCode == Xbox360Pad.BUTTON_A && gioman.getState().isJumping()) {
			gioman.getState().setIsJumping(false);
		}
		return false;
	}

	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		// Moving with Control stick
		if (axisCode == Xbox360Pad.AXIS_LEFT_X) {
			if(value > .25) {
				gioman.startMovingRight();
			} else if (value < -.25) {
				gioman.startMovingLeft();
			} else {
				gioman.stopHorizontalMovement();
			}
		}
		if (axisCode == Xbox360Pad.AXIS_RIGHT_TRIGGER || axisCode == Xbox360Pad.AXIS_LEFT_TRIGGER) {
			if(value >= -.25 && value <= .25) {
				gioman.stopAcceleration();
			} else {
				gioman.accelerate();
			}
		}
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povCode,
			PovDirection value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode,
			boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode,
			boolean value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accelerometerMoved(Controller controller,
			int accelerometerCode, Vector3 value) {
		// TODO Auto-generated method stub
		return false;
	}

}
