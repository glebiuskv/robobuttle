package ru.angorstv.robobuttle.entities;

/**
 * Created by Андрей on 24.01.2017.
 */
public class Engine extends Entity{
	private int power;

	public Engine(int power) {
		this.power = power;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
}
