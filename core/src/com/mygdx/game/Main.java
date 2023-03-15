package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.Globals.bulletHolder;

public class Main extends ApplicationAdapter {
	Player potato;
	Platform platformOne;
	Platform platformTwo;

	Platform platformThree;
	@Override
	public void create () {
	 potato = new Player(0,0, 10, 50,50,new ShapeRenderer());
	 platformOne = new Platform(400,100,20,400,new ShapeRenderer(),true,0, 0);

	 platformTwo = new Platform(1000,300,50,400,new ShapeRenderer(),true,0,0);
		platformThree = new Platform(0,-10,20,2000,new ShapeRenderer(),true,0, 0);

	}


	@Override
	public void render () {
		ScreenUtils.clear(.5f, .5f, .5f, 1);
		//region UPDATES
		platformTwo.update(potato);
		platformOne.update(potato);
		platformThree.update(potato);
		potato.update(platformOne, Gdx.graphics.getDeltaTime());
		potato.update(platformTwo, Gdx.graphics.getDeltaTime());
		bulletHolder.update();

		//endregion


		//region RENDERS

		platformTwo.render();
		potato.render();
		platformOne.render();
		platformThree.render();
		bulletHolder.render();

		//endregion
	}
	
	@Override
	public void dispose () {

	}
}
