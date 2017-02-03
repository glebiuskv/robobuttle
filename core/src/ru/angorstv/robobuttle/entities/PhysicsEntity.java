package ru.angorstv.robobuttle.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.angorstv.robobuttle.Const;

import java.util.ArrayList;
import java.util.List;

import static ru.angorstv.robobuttle.Const.*;

/**
 * Created by Андрей on 25.01.2017.
 */
public abstract class PhysicsEntity extends VisualEntity {
	protected Body body;
	protected final float angularDamping = 1.0f;
	protected final float linearDamping = 0.1f;
	protected final float density = 5f;
	protected final float friction = 0.4f; //friction when rubbing against other shapes
	protected final float restitution = 1f;

	/**
	 * Создать физ тело в указанной точке
	 * @param world
	 * @param position в метрах
	 */
	protected void createBody(World world, Vector2 position, float angle) {
		//устанавливаем спрайт относительно центра, а не угла
		sprite.setPosition((position.x * PIXELS_PER_METER) - sprite.getWidth() / 2,
				(position.y * PIXELS_PER_METER) - sprite.getHeight() / 2);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		//позиционирование с учётом масштаба физического мира
		bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) /
						PIXELS_PER_METER,
				(sprite.getY() + sprite.getHeight() / 2) / PIXELS_PER_METER);
		bodyDef.angle = angle;
		bodyDef.angularDamping = angularDamping;
		bodyDef.linearDamping = linearDamping;

		body = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		//границы физического тела с учётом масштаба
		shape.setAsBox(sprite.getWidth() / 2 / PIXELS_PER_METER, sprite.getHeight()
				/ 2 / PIXELS_PER_METER);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density  = density;
		fixtureDef.friction = friction;
		fixtureDef.restitution = restitution;

		body.createFixture(fixtureDef);
		shape.dispose();
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.setPosition((body.getPosition().x * PIXELS_PER_METER) - sprite.getWidth() / 2,
				(body.getPosition().y * PIXELS_PER_METER) - sprite.getHeight() / 2);
		sprite.setRotation(MathUtils.radiansToDegrees * body.getAngle());
		if (SHOW_SPRITE) super.draw(batch);
	}
}
