package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.Globals.bulletHolder;
import static com.mygdx.game.Globals.platformHolder;

public class Main extends ApplicationAdapter {
	Player potato;
	@Override
	public void create () { // All the objects in the game are created here
	 potato = new Player(0,20, 10, 50,50,new ShapeRenderer());
	 platformHolder.addPlatform(400,100,20,400,new ShapeRenderer(),true);
	 platformHolder.addPlatform(1000,300,50,400,new ShapeRenderer(),true);
	 platformHolder.addPlatform(0,0,20,2000,new ShapeRenderer(),true);
	}
	@Override
	public void render () {
		ScreenUtils.clear(.5f, .5f, .5f, 1);
		//region UPDATES
		bulletHolder.update();
		potato.update(Gdx.graphics.getDeltaTime());
		//endregion

		//region RENDERS

		potato.render();
		bulletHolder.render();
		platformHolder.render();

		//endregion
	}
	
	@Override
	public void dispose () {
	}
}
