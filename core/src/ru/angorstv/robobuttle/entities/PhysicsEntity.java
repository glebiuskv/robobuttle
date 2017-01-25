package ru.angorstv.robobuttle.entities;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Андрей on 25.01.2017.
 */
public abstract class PhysicsEntity extends VisualEntity{
	protected Body body;

	protected void createBody (World world){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) /
						PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight() / 2) / PIXELS_TO_METERS);

		body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / PIXELS_TO_METERS, sprite.getHeight()
				/ 2 / PIXELS_TO_METERS);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 0.1f;
		fixtureDef.restitution = 0.5f;

		body.createFixture(fixtureDef);
		shape.dispose();
	}
}
