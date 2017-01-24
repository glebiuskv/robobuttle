package ru.angorstv.robobuttle.entitys;

/**
 * Created by Андрей on 24.01.2017.
 */
public abstract class Entity {

	public final static float PIXELS_TO_METERS = 100f;
	private int weight;
	private int health;
	private int cost;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
