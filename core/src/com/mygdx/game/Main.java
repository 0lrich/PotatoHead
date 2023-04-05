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
	SpriteBatch batch;
	FirstBoss farmer;
	@Override
	public void create () { // All the objects in the game are created here
		batch = new SpriteBatch();
	 potato = new Player(0,20, 10, 50,50,new ShapeRenderer());

	 farmer = new FirstBoss(500,500,20,450,450,new ShapeRenderer());
	 platformHolder.addPlatform(400,100,20,400,new ShapeRenderer(),true, true);
	 platformHolder.addPlatform(1000,300,50,400,new ShapeRenderer(),true,true);
	 platformHolder.addPlatform(0,0,20,2000,new ShapeRenderer(),true,false);
	 /*
	 for(int i = 0; i < 20; i++){
		 platformHolder.addPlatform(0,0 + i*20,1,4000,new ShapeRenderer(),true, true);
	 }
	 */

	}
	@Override
	public void render () {
		ScreenUtils.clear(.5f, .5f, .5f, 1);
		//region UPDATES
		bulletHolder.update();
		potato.update(Gdx.graphics.getDeltaTime());
		farmer.update(potato);
		//endregion

		//region RENDERS

		platformHolder.render();

		potato.render();
		batch.begin();
		farmer.render(batch);
		batch.end();
		bulletHolder.render();



		//endregion
	}
	
	@Override
	public void dispose () {
	}
}
