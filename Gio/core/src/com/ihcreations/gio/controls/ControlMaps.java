package com.ihcreations.gio.controls;

import com.ihcreations.gio.characters.Character;

public class ControlMaps {
	private Character m_gioman;
	private boolean m_left_is_pressed;
	private boolean m_right_is_pressed;
	
	public ControlMaps(Character gioman) {
		this.m_gioman = gioman;
		this.m_left_is_pressed = false;
		this.m_right_is_pressed = false;
	}
	
	public void jump() {
		if(!this.m_gioman.isMovingVertical()) {
			this.m_gioman.startJump();
		}
	}
	
	public void stopJump() {
		if(m_gioman.getState().isJumping()) {
			m_gioman.getState().setIsJumping(false);
		}
	}
	
	public void keyDownRight() {
		m_right_is_pressed = true;
		m_gioman.startMovingRight();
	}
	public void keyDownLeft() {
		m_left_is_pressed = true;
		m_gioman.startMovingLeft();
	}
	public void keyUpRight() {
		m_right_is_pressed = false;
		if(m_left_is_pressed) {
			m_gioman.startMovingLeft();
		} else {
			m_gioman.stopHorizontalMovement();
		}
	}
	public void keyUpLeft() {
		m_left_is_pressed = false;
		if(m_right_is_pressed) {
			m_gioman.startMovingRight();
		} else {
			m_gioman.stopHorizontalMovement();
		}
	}
	public void speedUp() {
		m_gioman.accelerate();
	}
	public void slowDown() {
		m_gioman.stopAcceleration();
	}
	
	public void leftControlStickHorizontal(float value) {
		if(value > .25) { 
			m_gioman.startMovingRight();
		} else if (value < -.25) {
			m_gioman.startMovingLeft();
		} else {
			m_gioman.stopHorizontalMovement();
		}
	}
	
	public void axisTriggers(float value) {
		if(value >= -.25 && value <= .25) {
			this.slowDown();
		} else {
			this.speedUp();;
		}
	}
}
