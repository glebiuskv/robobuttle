package ru.angorstv.robobuttle.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.angorstv.robobuttle.Const;
import ru.angorstv.robobuttle.MyGdxGame;

public class DesktopLauncher implements Const{
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.height = 600;
		config.width = 800;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
