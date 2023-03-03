package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	Player potato;
	@Override
	public void create () {
	 potato = new Player(0,0, 10, 50,50,new ShapeRenderer());


	}


	@Override
	public void render () {
		ScreenUtils.clear(.5f, .5f, .5f, 1);
		//region UPDATES


		potato.update();


		//endregion


		//region RENDERS


		potato.render();


		//endregion
	}
	
	@Override
	public void dispose () {

	}
}
