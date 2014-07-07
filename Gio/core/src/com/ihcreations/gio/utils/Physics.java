package com.ihcreations.gio.utils;

import com.ihcreations.gio.characters.CharacterPhysics;

public class Physics {
	public static final float GRAVITY = -0.25f;
	
	public static void gravity(CharacterPhysics character) {
		character.addToYVelocity(GRAVITY);
	}
}
