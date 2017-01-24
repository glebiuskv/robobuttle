package ru.angorstv.robobuttle.entitys;

/**
 * Created by Angor on 24.01.2017.
 */
public abstract class Module extends Entity {
    private int volume;

    public abstract void run();

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
