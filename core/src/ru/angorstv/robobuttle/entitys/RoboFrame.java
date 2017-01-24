package ru.angorstv.robobuttle.entitys;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Андрей on 24.01.2017.
 */
public class RoboFrame extends Entity{
	private Engine engine;
	private Battery battery;
	private Sprite sprite;
	private Body body;
	private float direct;
	private Array<Module> modules = new Array<>();

	void runModules (){
		modules.forEach(module ->{
			module.run();
		});
	}

}
