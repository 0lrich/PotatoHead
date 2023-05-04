package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.Globals.*;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Vector2 playerCameraPos = new Vector2(512,300);
	@Override
	public void create () { // All the objects in the game are created here

		batch = new SpriteBatch();
		camera.setToOrtho(false, 1024*3, 600*3);
		sceneHolder.switchScene(0);
	}
	@Override
	public void render () {
		//ScreenUtils.clear(.5f, .5f, .5f, 1);
		//region UPDATES
		sceneHolder.update();
		bulletHolder.update();
		//potato.update(Gdx.graphics.getDeltaTime());
		//farmer.update(potato);
		//endregion



		//region RENDERS
		camera.position.set(playerCameraPos,camera.position.z);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		//camera.unproject(playerCameraPos);

		batch.begin();
		sceneHolder.render(batch);
		platformHolder.render(batch);
		wallHolder.render(batch);
		bulletHolder.render(batch);
		batch.end();

		playerCameraPos.lerp(new Vector2(potato.getPosX(), potato.getPosY()), 0.1F);
		//endregion
	}
	
	@Override
	public void dispose () {
	}
}
