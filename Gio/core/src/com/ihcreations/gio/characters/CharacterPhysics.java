package com.ihcreations.gio.characters;

public class CharacterPhysics {
	private double x_velocity;
	private double y_velocity;
	private boolean is_solid;
	
	public CharacterPhysics() {
		this.x_velocity = 0;
		this.y_velocity = 0;
		this.is_solid = true;
	}
	
	public CharacterPhysics(boolean is_solid) {
		this.x_velocity = 0;
		this.y_velocity = 0;
		this.is_solid = is_solid;
	}
	
	public void addToXVelocity(double x_velocity_addition) {
		this.x_velocity += x_velocity_addition;
	}
	
	public void addToYVelocity(double y_velocity_addition) {
		this.y_velocity += y_velocity_addition;
	}
	
	public boolean isSolid() {
		return this.is_solid;
	}
	
	public void setIsSolid(boolean is_solid) {
		this.is_solid = is_solid;
	}
	
	public void setXVelocity(double x) {
		this.x_velocity = x;
	}
	
	public void setYVelocity(double y) {
		this.y_velocity = y;
	}
	
	public void setXYVelocity(double x, double y) {
		this.x_velocity = x;
		this.y_velocity = y;
	}
	
	public double xVelocity() {
		return this.x_velocity;
	}
	
	public double yVelocity() {
		return this.y_velocity;
	}
}
