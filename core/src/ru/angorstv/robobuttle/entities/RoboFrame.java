package ru.angorstv.robobuttle.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.*;

/**
 * Created by Андрей on 24.01.2017.
 */
public class RoboFrame extends PhysicsEntity {
	private Engine engine;
	private Battery battery;

	private final float maxSpeed = 3f;
	private final float maxAngularVelocity = 0.7f;

	private float direct;

	private Vector2 target;

	private Array<Module> modules = new Array<>();
	public List<Wheel> wheels;
	private float wheelAngle = 1f;
	private float power = 1;

	public RoboFrame(String spriteName, World world, float x, float y) {
		createSprite(spriteName);
		createBody(world, new Vector2(x, y), 0);
		target = body.getPosition();

		//initialize wheels
		this.wheels = new ArrayList<Wheel>();
		this.wheels.add(new Wheel(world, this, -0.2f, 0.15f, 0.07f, 0.15f, true, true)); //top left
		this.wheels.add(new Wheel(world, this, 0.2f, 0.15f, 0.07f, 0.15f, true, true)); //top right
		//this.wheels.add(new Wheel(world, this, -0.2f, -0.15f, 0.07f, 0.15f, false,  false)); //back left
		//this.wheels.add(new Wheel(world, this, 0.2f, -0.15f, 0.07f, 0.15f, false, false)); //back right
	}

	public void setTarget(Vector2 target) {
		this.target = target;
	}

	public List<Wheel> getPoweredWheels() {
		List<Wheel> poweredWheels = new ArrayList<Wheel>();
		for (Wheel wheel : this.wheels) {
			if (wheel.powered)
				poweredWheels.add(wheel);
		}
		return poweredWheels;
	}

	public List<Wheel> getRevolvingWheels() {
		List<Wheel> revolvingWheels = new ArrayList<Wheel>();
		for (Wheel wheel : this.wheels) {
			if (wheel.revolving)
				revolvingWheels.add(wheel);
		}
		return revolvingWheels;
	}

	private void runModules() {
		modules.forEach(module -> {
			module.run();
		});
	}

	public void draw(SpriteBatch batch) {
		move();
		super.draw(batch);
	}

	private void move() {

		testMoveWORotation();

	}

	private void testWheels() {
		//1. KILL SIDEWAYS VELOCITY
		for (Wheel wheel : wheels) {
			wheel.killSidewaysVelocity();
		}

		//update revolving wheels
		for (Wheel wheel : this.getRevolvingWheels()) {
			wheel.setAngle(this.wheelAngle);
		}

		//3. APPLY FORCE TO WHEELS
		Vector2 baseVector = new Vector2(0, 0.1f); //vector pointing in the direction force will be applied to a wheel ; relative to the wheel.

		//multiply by engine power, which gives us a force vector relative to the wheel
		Vector2 forceVector = new Vector2(this.power * baseVector.x, this.power * baseVector.y);

		//apply force to each wheel
		for (Wheel wheel : this.getPoweredWheels()) {
			Vector2 position = wheel.body.getWorldCenter();
			wheel.body.applyForce(wheel.body.getWorldVector(new Vector2(forceVector.x, forceVector.y)), position, true);
		}
	}

	private void testMoveWORotation() {

		//локальное напрвление на прямо
		Vector2 dir = new Vector2(0, 1);

		//вектор на цель из центра масс
		Vector2 toTarget = new Vector2(target);
		toTarget.sub(body.getPosition());
		//пересчитывает этот вектор для локальных координат тела
		Vector2 localToTarget = body.getLocalVector(toTarget);

		// угол между направлением body и направлением на цель
		// положительный по часовой стрелке от +Y
		// и отрицательный против часовой от +Y
		float angle = localToTarget.angle(dir);

		if (angle == 0) {
			body.setAngularVelocity(0);
		} else {
			if (angle > 0) {
				rotate(-1);
			} else {
				rotate(1);
			}
		}

		// газ и тормоз в зависимости от растояни от точки назначения
		if(target.dst(body.getPosition())>sprite.getHeight()/PIXELS_PER_METER){
			if (getSpeed() < maxSpeed){
				body.applyForceToCenter(body.getWorldVector(new Vector2(0,0.3f)), true);
			}
		}else{
			if (getSpeed() > 0){
				body.applyForceToCenter(body.getWorldVector(new Vector2(0,-0.6f)), true);
			}
		}
		killSideAwaysVelocity();

	}

	/**
	 * повернуть тело
	 * @param dir -1 по часовой, 1 против
	 */
	private void rotate (int dir){
		if (body.getAngularVelocity() < maxAngularVelocity){
			body.applyTorque(0.1f * dir, true);
		}
	}

	/**
	 * получить скорость по направлению движения
	 * под вопросом ???
	 * @return
	 */
	private float getSpeed (){
		return body.getLocalVector(body.getLinearVelocity()).y;
	}

	private float getSideAwaysVelocity () {
		return body.getLocalVector(body.getLinearVelocity()).x;
	}

	private void killSideAwaysVelocity (){
		Vector2 v = body.getLocalVector(body.getLinearVelocity());
		v.x = 0;
		body.setLinearVelocity(body.getWorldVector(v));
	}

}
