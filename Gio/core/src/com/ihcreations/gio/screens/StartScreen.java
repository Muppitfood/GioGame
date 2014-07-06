package com.ihcreations.gio.screens;

// Local imports
import com.ihcreations.gio.Gio;
import com.ihcreations.gio.controllers.Xbox360Pad;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;

public class StartScreen implements Screen, ControllerListener, InputProcessor {

	final Gio m_game;
	
	OrthographicCamera camera;
	
	public StartScreen(final Gio game) {
		this.m_game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 768);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		m_game.batch.begin();
		m_game.font.draw(m_game.batch, "Welcome to Gio!", 100, 150);
		m_game.font.draw(m_game.batch, "Press start or enter to begin.", 100, 100);
		m_game.batch.end();
		
		if(m_game.gamepad != null) {
			m_game.gamepad.addListener(this);
		}
		
		Gdx.input.setInputProcessor(this);
		
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
	
	// Input handling
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
		if((buttonCode == Xbox360Pad.BUTTON_A || buttonCode == Xbox360Pad.BUTTON_START) && !m_game.game_started)
		{
			m_game.setScreen(new MenuScreen(m_game));
		}
		return false;
	}
	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
		// TODO Auto-generated method stub
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
	@Override
	public boolean keyDown(int keycode) {
		if((keycode == Keys.ENTER || keycode == Keys.SPACE) && !m_game.game_started) {
			m_game.setScreen(new MenuScreen(m_game));
		}
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
	
	

}
