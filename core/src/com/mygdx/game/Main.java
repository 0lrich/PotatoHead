package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	Player potato;
	Platform platformOne;
	@Override
	public void create () {
	 potato = new Player(0,0, 10, 50,50,new ShapeRenderer());
	 platformOne = new Platform(200,400,150,400,new ShapeRenderer(),false, false, true, 0, 0);

	}


	@Override
	public void render () {
		ScreenUtils.clear(.5f, .5f, .5f, 1);
		//region UPDATES

		platformOne.update(potato);
		potato.update(platformOne);


		//endregion


		//region RENDERS


		potato.render();
		platformOne.render();

		//endregion
	}
	
	@Override
	public void dispose () {

	}
}
