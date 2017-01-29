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
    private CameraHelper camera;
    private float deltaTime;

    private Array<VisualEntity> entities;


    World world;
    Box2DDebugRenderer debugRenderer;


    @Override
    public void create() {

        // Создаём графику мира
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new CameraHelper((int)VIRTUAL_WIDTH, (int)VIRTUAL_HEIGHT);
        camera.update(camera.getViewportWidth() /2, camera.getViewportHeight() /2);

        batch = new SpriteBatch();


        // Создаём физику мира
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.setDrawAABBs(true);
        debugRenderer.setDrawBodies(true);
        debugRenderer.setDrawContacts(true);
        debugRenderer.setDrawInactiveBodies(true);
        debugRenderer.setDrawJoints(true);
        debugRenderer.setDrawVelocities(true);

        // Создаём обитателей мира
        entities = new Array<>();
        entities.add(new RoboFrame("tank.png", world));

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        camera.update(camera.getViewportWidth() /2, camera.getViewportHeight() /2);
        batch.setProjectionMatrix(camera.getCombined());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        for(VisualEntity ve:entities){
            ve.draw(batch);
        }
        batch.end();
        deltaTime = Gdx.graphics.getDeltaTime();
        world.step(deltaTime, 6, 2);
        //System.out.println("delta="+ deltaTime);
        if (DEBUG_MODE) debugRenderer.render(world, camera.getCombined());
    }

    @Override
    public void dispose() {
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
