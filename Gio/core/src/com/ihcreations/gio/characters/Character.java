package com.ihcreations.gio.characters;

// Local Imports



import com.ihcreations.gio.characters.CharacterBody;
import com.ihcreations.gio.characters.CharacterState;

// Java Imports
import java.awt.Point;




// Libgdx Imports
import com.badlogic.gdx.graphics.Texture;


public class Character {
	// Constants
	public final int JUMP_FRAME_LENGTH = 30;
	
	// Member Variables
	// --------------------
	// Location and Dimensions
	private CharacterBody m_body;
	
	// Movement
	private CharacterState m_state;
	
	// Member functions
	// --------------------
	// Constructors
	public Character() {
		this.m_body = new CharacterBody();
		this.m_state = new CharacterState();
	}
	public Character(Point center, int height, int width, Texture sprite) {
		this.m_body = new CharacterBody(center, height, width, sprite);
		this.m_state = new CharacterState();
	}
	public Character(int x, int y, int height, int width, Texture sprite) {
		this.m_body = new CharacterBody(x, y, height, width, sprite);
		this.m_state = new CharacterState();
	}
	
	// Get/Sets for Member Variables
	public int getHeight() {
		return m_body.m_height;
	}
	public void setHeight(int height) {
		this.m_body.m_height = height;
	}
	
	public int getWidth() {
		return this.m_body.m_width;
	}
	public void setWidth(int width) {
		this.m_body.m_width = width;
	}
	
	public Point getCenter() {
		return this.m_body.m_center;
	}
	public void setM_center(Point center) {
		this.m_body.m_center = center;
	}
	
	public Texture getSprite() {
		return this.m_body.getSprite();
	}
	public void setSprite(Texture sprite) {
		this.m_body.setSprite(sprite);
	}
	
	public void startMovingRight() {
		this.m_state.setHorizontalDirection(CharacterState.RIGHT);
	}
	public void startMovingLeft() {
		this.m_state.setHorizontalDirection(CharacterState.LEFT);
	}
	
	public void moveRight() {
		if(this.m_state.isAccelerated() == true) {
			this.m_body.m_center.x += 6;
		} else {
			this.m_body.m_center.x += 3;
		}
	}
	public void moveLeft() {
		if(this.m_state.isAccelerated() == true) {
			this.m_body.m_center.x -= 6;
		} else {
			this.m_body.m_center.x -= 3;
		}
	}
	public void moveUp() {
		this.m_body.m_center.y += 5;
		this.m_state.m_percent_jumped++;
		if(this.m_state.m_percent_jumped >= JUMP_FRAME_LENGTH) {
			m_state.setVerticalDirection(CharacterState.DOWN);
			m_state.setIsJumping(false);
		}
	}
	public void moveDown() {
		this.m_body.m_center.y -= 5;
		this.m_state.m_percent_jumped--;
		if(this.m_state.m_percent_jumped <= 0) {
			m_state.setVerticalDirection(CharacterState.STATIONARY);
		}
	}
	
	public void startJump() {
		this.m_state.setIsJumping(true);
		this.m_state.setVerticalDirection(CharacterState.UP);
	}
	public int getJumpDirection() {
		return this.m_state.getVerticalDirection();
	}
	public void stopHorizontalMovement() {
		this.m_state.setHorizontalDirection(CharacterState.STATIONARY);
	}
	public void stopVerticalMovement() {
		this.m_state.setVerticalDirection(CharacterState.STATIONARY);
		this.m_state.setIsJumping(false);
	}
	public boolean isMovingHorizontal() {
		return this.m_state.getHorizontalDirection() != CharacterState.STATIONARY;
	}
	public boolean isMovingVertical() {
		return this.m_state.getVerticalDirection() != CharacterState.STATIONARY;
	}
	public void accelerate() {
		this.m_state.setIsAccelerated(true);
	}
	public void stopAcceleration() {
		this.m_state.setIsAccelerated(false);
	}
	public void updateMovement() {
		if(this.isMovingHorizontal()) {
			switch(this.m_state.getHorizontalDirection()) {
				case CharacterState.LEFT:
					this.moveLeft();
					break;
				case CharacterState.RIGHT:
					this.moveRight();
					break;
				case CharacterState.STATIONARY:
					this.stopHorizontalMovement();
					break;
				default:
					System.out.println("ERROR: No case reached in moving horizontal.");
					break;
			}
		}
		if(this.isMovingVertical()) {
			if(this.m_state.isJumping()) {
				this.moveUp();
			} else {
				this.moveDown();
			}
		}
	}
	public CharacterState getState() {
		return this.m_state;
	}
}
