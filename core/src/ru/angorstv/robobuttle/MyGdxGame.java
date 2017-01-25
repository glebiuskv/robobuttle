package ru.angorstv.robobuttle;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import ru.angorstv.robobuttle.entities.Entity;
import ru.angorstv.robobuttle.entities.RoboFrame;
import ru.angorstv.robobuttle.entities.VisualEntity;

public class MyGdxGame extends ApplicationAdapter implements Const, InputProcessor {
	private SpriteBatch batch;
	private OrthographicCamera camera;

	private Array<VisualEntity> entities;



	World world;
	Box2DDebugRenderer debugRenderer;

	
	@Override
	public void create () {

		// Создаём графику мира
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT * (h / w));

		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();

		batch = new SpriteBatch();


		// Создаём физику мира
		Box2D.init();
		world = new World(new Vector2(0,0), true);
		debugRenderer = new Box2DDebugRenderer();
		world.step(1/60f, 6, 2);

		// Создаём обитателей мира
		entities = new Array<>();
		entities.add(new RoboFrame("tank.png", world));

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		entities.forEach(entity -> {
			entity.draw(batch);
		});
		batch.end();
		debugRenderer.render(world, camera.combined);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}


	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
