package com.ihcreations.gio.characters;

public class CharacterState {
	// Constants
	public static final int STATIONARY = -1;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	// Public Member Variable
	public int m_percent_jumped;
	
	// Private Member Variables
	private boolean m_is_jumping;
	private int m_horizontal_direction;
	private int m_vertical_direction;
	private boolean m_is_accelerated;
	
	// Constructors
	public CharacterState() {
		this.m_percent_jumped = 0;
		this.m_is_jumping = false;
		this.m_is_accelerated = false;
		this.m_horizontal_direction = STATIONARY;
		this.m_vertical_direction = STATIONARY;
	}
	
	// Member Functions
	public int getHorizontalDirection() {
		return this.m_horizontal_direction;
	}
	public void setHorizontalDirection(int horizontal_direction) {
		this.m_horizontal_direction = horizontal_direction;
	}
	public int getVerticalDirection() {
		return this.m_vertical_direction;
	}
	public void setVerticalDirection(int vertical_direction) {
		this.m_vertical_direction = vertical_direction;
	}
	public boolean isJumping() {
		return this.m_is_jumping;
	}
	public void setIsJumping(boolean is_jumping) {
		this.m_is_jumping = is_jumping;
	}
	public boolean isAccelerated() {
		return this.m_is_accelerated;
	}
	public void setIsAccelerated(boolean is_accelerated) {
		this.m_is_accelerated = is_accelerated;
	}
}
