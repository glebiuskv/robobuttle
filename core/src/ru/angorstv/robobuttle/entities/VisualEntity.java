package ru.angorstv.robobuttle.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Андрей on 25.01.2017.
 */
public abstract class VisualEntity extends Entity {
	protected Sprite sprite;

	public void draw(SpriteBatch batch){
		sprite.draw(batch);
	}

	protected void createSprite(String spriteName){
		sprite = new Sprite(new Texture(spriteName));
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
	}

}
