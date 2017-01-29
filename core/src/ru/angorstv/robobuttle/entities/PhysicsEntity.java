package ru.angorstv.robobuttle.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.angorstv.robobuttle.Const;

import static ru.angorstv.robobuttle.Const.*;

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
		fixtureDef.density = 100f;
		fixtureDef.restitution = 0.5f;

		body.createFixture(fixtureDef);
		shape.dispose();
	}
	public void draw(SpriteBatch batch){
		//body.setLinearVelocity(10,10);
		body.applyForceToCenter(100,10,true);
		Vector2 vect = translateFromBodyToSprite(body.getPosition());
		sprite.setPosition(vect.x, vect.y);
		sprite.setRotation(MathUtils.radiansToDegrees*body.getAngle());
		if(!DEBUG_MODE) super.draw(batch);
	}
}
