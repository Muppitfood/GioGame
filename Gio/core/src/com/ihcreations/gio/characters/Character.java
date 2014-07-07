package com.ihcreations.gio.characters;

// Local Imports



import com.ihcreations.gio.characters.CharacterBody;
import com.ihcreations.gio.characters.CharacterState;
import com.ihcreations.gio.utils.GioConstants;
import com.ihcreations.gio.utils.Physics;
import com.ihcreations.gio.utils.Point;



// Libgdx Imports
import com.badlogic.gdx.graphics.Texture;


public class Character {
	// Constants
	public final int JUMP_FRAME_LENGTH = 75;
	
	// Member Variables
	// --------------------
	// Location and Dimensions
	private CharacterBody m_body;
	
	// Movement
	private CharacterState m_state;
	
	// Physics
	private CharacterPhysics m_physics;
	
	// Member functions
	// --------------------
	// Constructors
	public Character() {
		this.m_body = new CharacterBody();
		this.m_state = new CharacterState();
		this.m_physics = new CharacterPhysics(true);
	}
	public Character(Point center, int height, int width, Texture sprite) {
		this.m_body = new CharacterBody(center, height, width, sprite);
		this.m_state = new CharacterState();
		this.m_physics = new CharacterPhysics(true);
	}
	public Character(float x, float y, int height, int width, Texture sprite) {
		this.m_body = new CharacterBody(x, y, height, width, sprite);
		this.m_state = new CharacterState();
		this.m_physics = new CharacterPhysics(true);
	}
	
	// Get/Sets for Member Variables
	public float getHeight() {
		return m_body.m_height;
	}
	public void setHeight(int height) {
		this.m_body.m_height = height;
	}
	
	public float getWidth() {
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
			m_physics.setXVelocity(GioConstants.RUNNING_SPEED);
		} else {
			m_physics.setXVelocity(GioConstants.WALKING_SPEED);
		}
	}
	public void moveLeft() {
		if(this.m_state.isAccelerated() == true) {
			m_physics.setXVelocity(-GioConstants.RUNNING_SPEED);
		} else {
			m_physics.setXVelocity(-GioConstants.WALKING_SPEED);
		}
	}
	
	public void startJump() {
		this.m_physics.setYVelocity(GioConstants.JUMP_SPEED);
	}
	public int getJumpDirection() {
		return this.m_state.getVerticalDirection();
	}
	public void stopHorizontalMovement() {
		this.m_state.setHorizontalDirection(CharacterState.STATIONARY);
		this.m_physics.setXVelocity(0);
	}
	public void taperJump() {
		if(this.m_physics.yVelocity() > GioConstants.TAPER_SPEED && !(this.m_physics.yVelocity() <= 0)) {
			this.m_physics.setYVelocity(5);
		}
	}
	public void stopVerticalMovement() {
		this.m_physics.setYVelocity(0);
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
	}
	
	public void updatePhysics() {
		this.m_body.m_center.x += this.m_physics.xVelocity();
		
		if(this.m_body.m_center.y > GioConstants.FLOOR_HEIGHT) {
			Physics.gravity(this.m_physics);
		}
		
		this.m_body.m_center.y += this.m_physics.yVelocity();
		if(this.m_body.m_center.y < GioConstants.FLOOR_HEIGHT && this.m_physics.yVelocity() <= 0) {
			this.m_body.m_center.y = GioConstants.FLOOR_HEIGHT;
			this.m_physics.setYVelocity(0);
		}
	}
	
	public CharacterState getState() {
		return this.m_state;
	}
	
	public CharacterPhysics getPhysics() {
		return this.m_physics;
	}
}
