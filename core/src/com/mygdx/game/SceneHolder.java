package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.mygdx.game.Globals.bulletHolder;
import static com.mygdx.game.Globals.platformHolder;

public class SceneHolder {
    FirstBoss farmer;
    Player potato;
    int scene;
    public SceneHolder(){
        potato = new Player(0,20, 10, 50,50,new ShapeRenderer());
        farmer = new FirstBoss(500,500,20,450,450,new ShapeRenderer());
    }
    public void setScene(int scene){}
    public int getScene() {return scene;}
    public void switchScene(int sceneNumber){
        resetScene();
        scene = sceneNumber;
        switch(scene){
            case 0:
                potato.init(200,200, 10, 50,50,new ShapeRenderer());
                farmer.init(5000,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(0);
                break;
            case 1:
                potato.init(600,200, 10, 50,50,new ShapeRenderer());
                farmer.init(500,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(1);
                break;
            case 2:
                potato.init(400,500, 10, 50,50,new ShapeRenderer());
                farmer.init(5000,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(2);
                break;
        }
    }
    public void update(){
        potato.update(Gdx.graphics.getDeltaTime());
        if(scene == 1){
            farmer.update();
        }
    }
    public void render(SpriteBatch batch) {
        potato.render();
        batch.begin();
        if(scene == 1){
            farmer.render(batch);
        }
        batch.end();
    }
    public void resetScene(){
        bulletHolder.removeBullets();
    }
}
