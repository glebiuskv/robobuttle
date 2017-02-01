package ru.angorstv.robobuttle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.angorstv.robobuttle.Const;
import ru.angorstv.robobuttle.MyGdxGame;

public class DesktopLauncher implements Const{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.height = (int) Const.VIRTUAL_HEIGHT;
		config.width = (int) Const.VIRTUAL_WIDTH;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
