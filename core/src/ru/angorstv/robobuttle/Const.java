package ru.angorstv.robobuttle;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Андрей on 25.01.2017.
 */
public interface Const {
	float PIXELS_TO_METERS = 100f;
	float VIEWPORT_WIDTH = 500f;
	float VIEWPORT_HEIGHT = 500f;
	float SPRITE_SIZE = 48f;
	boolean DEBUG_MODE = true;

	static Vector2 translateFromBodyToSprite (Vector2 bodyVector){
		return new Vector2( bodyVector.x-SPRITE_SIZE/2, bodyVector.y-SPRITE_SIZE/2);
	}
}
