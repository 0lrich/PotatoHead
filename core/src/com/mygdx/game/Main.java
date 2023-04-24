package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.Globals.*;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;


	@Override
	public void create () { // All the objects in the game are created here
		batch = new SpriteBatch();
		sceneHolder.switchScene(0);


	}
	@Override
	public void render () {
		//ScreenUtils.clear(.5f, .5f, .5f, 1);
		//region UPDATES
		sceneHolder.update();
		bulletHolder.update();
		//endregion

		//region RENDERS

		sceneHolder.render(batch);
		platformHolder.render();
		wallHolder.render();
		bulletHolder.render();
		//endregion
	}
	
	@Override
	public void dispose () {
	}
}
