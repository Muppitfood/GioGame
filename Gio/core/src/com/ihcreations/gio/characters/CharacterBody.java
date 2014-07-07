package com.ihcreations.gio.characters;

// Java Imports
import com.ihcreations.gio.utils.Point;

// Libgdx Imports
import com.badlogic.gdx.graphics.Texture;

public class CharacterBody {
	// Public Member Variables
	public Point m_center;
	public float m_height;
	public float m_width;
	
	// Private Member Variables
	private Texture m_sprite;
	
	public CharacterBody() {
		this.m_height = 0;
		this.m_width = 0;
		this.m_center = null;
		this.m_sprite = null;
	}
	
	public CharacterBody(Point center, float height, float width, Texture sprite) {
		this.m_height = height;
		this.m_width = width;
		this.m_center = center;
		this.m_sprite = sprite;
	}
	
	public CharacterBody(float x, float y, float height, float width, Texture sprite) {
		this.m_height = height;
		this.m_width = width;
		this.m_center = new Point(x, y);
		this.m_sprite = sprite;
	}
	
	public void setSprite(Texture sprite) {
		this.m_sprite = sprite;
	}
	public Texture getSprite() {
		return this.m_sprite;
	}
	
}
