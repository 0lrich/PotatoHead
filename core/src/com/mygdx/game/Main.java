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
	 for(int i = 0; i < 20; i++){
		 platformHolder.addPlatform(0,0 + i*20,1,4000,new ShapeRenderer(),true, true);
	 }
	}
	@Override
	public void render () {
		ScreenUtils.clear(.5f, .5f, .5f, 1);
		//region UPDATES
		bulletHolder.update();
		potato.update(Gdx.graphics.getDeltaTime());
		//endregion

		//region RENDERS
		platformHolder.render();
		potato.render();
		bulletHolder.render();

		//endregion
	}
	
	@Override
	public void dispose () {
	}
}
