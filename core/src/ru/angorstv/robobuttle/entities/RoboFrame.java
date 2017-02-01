package ru.angorstv.robobuttle.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Андрей on 24.01.2017.
 */
public class RoboFrame extends PhysicsEntity {
	private Engine engine;
	private Battery battery;

	private float direct;

	private Array<Module> modules = new Array<>();

	public RoboFrame(String spriteName, World world) {
		createSprite(spriteName);
		createBody(world);
	}

	private void runModules() {
		modules.forEach(module -> {
			module.run();
		});
	}

	public void draw (SpriteBatch batch){
		//body.applyForceToCenter(1, 1, true);
		super.draw(batch);
	}
}
