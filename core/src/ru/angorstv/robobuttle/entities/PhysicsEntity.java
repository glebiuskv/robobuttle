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
						PIXELS_PER_METER,
				(sprite.getY() + sprite.getHeight() / 2) / PIXELS_PER_METER);

		body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth() / 2 / PIXELS_PER_METER, sprite.getHeight()
				/ 2 / PIXELS_PER_METER);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 100f;
		fixtureDef.restitution = 0.5f;

		body.createFixture(fixtureDef);
		shape.dispose();
	}
	public void draw(SpriteBatch batch){
		//body.setLinearVelocity(10,10);
		body.applyForceToCenter(10,1,true);
		float x = PIXELS_PER_METER * body.getPosition().x - sprite.getRegionWidth() / 2;
		float y = PIXELS_PER_METER * body.getPosition().y - sprite.getRegionHeight() / 2;
		sprite.setPosition(x, y);
		sprite.setRotation(MathUtils.radiansToDegrees*body.getAngle());
		if(!DEBUG_MODE) super.draw(batch);
	}
}
