package ru.angorstv.robobuttle.entities;

import ru.angorstv.robobuttle.Const;

/**
 * Created by Андрей on 24.01.2017.
 */
public abstract class Entity implements Const {

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
