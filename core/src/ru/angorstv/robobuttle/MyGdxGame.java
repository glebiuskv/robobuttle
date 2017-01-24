package ru.angorstv.robobuttle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private OrthographicCamera camera;

	Texture img;
	Actor act;

	World world;
	Box2DDebugRenderer debugRenderer;
	private float accumulator = 0;
	
	@Override
	public void create () {

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(300, 300 * (h / w));

		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		batch = new SpriteBatch();

		img = new Texture("badlogic.jpg");

		// Создаём физический мир
		Box2D.init();
		world = new World(new Vector2(0,0), true);
		debugRenderer = new Box2DDebugRenderer();
		world.step(1/60f, 6, 2);
	}

	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		debugRenderer.render(world, camera.combined);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}


}
