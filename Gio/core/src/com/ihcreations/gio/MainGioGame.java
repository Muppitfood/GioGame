package com.ihcreations.gio;

// Local imports
import com.ihcreations.gio.characters.Character;
import com.ihcreations.gio.controllers.Xbox360Pad;

// Java imports
import java.awt.Point;

// Libgdx imports
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.FPSLogger;

public class MainGioGame extends ApplicationAdapter implements ControllerListener, InputProcessor {
	// Gamepad Constants
	final int GAMEPAD_LEFT = -1;
	final int GAMEPAD_RIGHT = 1;
	final int GAMEPAD_UP = 1;
	final int GAMEPAD_DOWN = -1;
	
	// Global game variables
	SpriteBatch batch;
	Character mario;
	Controller gamepad;
	FPSLogger fps_logger;
	
	// When game is first started
	@Override
	public void create () {
		// Initialize images
		batch = new SpriteBatch();
		Texture mario_image = new Texture(Gdx.files.internal("meatboy.jpg"));
		
		// Initialize Character
		mario = new Character(new Point(100, 100), 30, 30, mario_image);
		
		// Initialize Logger
		fps_logger = new FPSLogger();
		
		// Select first controller in array
		// TODO: Select Xbox 360 controller from array
		if(Controllers.getControllers().size >= 1) {
			gamepad = Controllers.getControllers().first();
			gamepad.addListener(this);
		}
		
		Gdx.input.setInputProcessor(this);
	}

	// Called each frame
	@Override
	public void render () {
		// Clear Screen and write background color
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Draw Mario
		batch.begin();
		batch.draw(mario.getSprite(), mario.getCenter().x, mario.getCenter().y, mario.getWidth(), mario.getHeight());
		batch.end();
		
		// Move Mario
		mario.updateMovement();
		
		// Log Framerate
		fps_logger.log();
	}

	// Controller Listener
	// ----------------------
	// Used
	// When a controller is connected
	@Override
	public void connected(Controller controller) {
		// TODO Auto-generated method stub
	}
	// When a controller is disconnected
	@Override
	public void disconnected(Controller controller) {
		// TODO Auto-generated method stub
	}
	// When a button is pressed down
	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		if (buttonCode == Xbox360Pad.BUTTON_A && !mario.isMovingVertical()) {
			mario.startJump();
		}
		return false;
	}
	// When a button is let up
	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		if (buttonCode == Xbox360Pad.BUTTON_A && mario.getState().isJumping()) {
			mario.getState().setIsJumping(false);;
		}
		return false;
	}
	// When a controller axis is moved
	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		// Moving with Control stick
		if (axisCode == Xbox360Pad.AXIS_LEFT_X) {
			if(value > .25) {
				mario.startMovingRight();
			} 
			if (value < -.25) {
				mario.startMovingLeft();
			} 
			if (value >= -.25 && value <= .25) {
				mario.stopHorizontalMovement();
			}
		}
		
		// Speeding up with right or left triggers
		if (axisCode == Xbox360Pad.AXIS_RIGHT_TRIGGER || axisCode == Xbox360Pad.AXIS_LEFT_TRIGGER) {
			if(value >= -.25 && value <= .25) {
				mario.stopAcceleration();
			} else if(value < 1 && value > .25 || value > -1 && value < -.25) {
				mario.accelerate();
			}
		}
		
		return false;
	}
	
	// Unused
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

	
	// Input Processor
	// ----------------------
	// Used
	// On key press down
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.D) {
			mario.startMovingRight();
		} else if (keycode == Input.Keys.LEFT || keycode == Input.Keys.A) {
			mario.startMovingLeft();
		} else if (keycode == Input.Keys.SHIFT_LEFT) {
			mario.accelerate();
		} else if (keycode == Input.Keys.SPACE) {
			mario.startJump();
		}
		return false;
	}
	// On key press up
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.RIGHT ||
		   keycode == Input.Keys.D ||
		   keycode == Input.Keys.LEFT ||
		   keycode == Input.Keys.A) {
			mario.stopHorizontalMovement();;
		} else if (keycode == Input.Keys.SHIFT_LEFT && mario.getState().isJumping()) {
			mario.stopAcceleration();
		} else if (keycode == Input.Keys.SPACE && mario.getState().isJumping()) {
			mario.getState().setIsJumping(false);
		}
		return false;
	}
	
	// Unused 
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
}
