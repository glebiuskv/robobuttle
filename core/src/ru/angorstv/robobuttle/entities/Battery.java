package ru.angorstv.robobuttle.entities;

/**
 * Created by Андрей on 24.01.2017.
 */
public class Battery extends Entity{
	private int capacity;

	public Battery(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
