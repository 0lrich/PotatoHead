package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.FirstBoss;
import com.mygdx.game.Player;

import static com.mygdx.game.Globals.*;

public class SceneHolder {
    int scene;
    public SceneHolder(){
        potato = new Player(0,20, 10, 50,50);
        farmer = new FirstBoss(500,500,20,450,450);
    }
    public void setScene(int scene){}
    public int getScene() {return scene;}
    public void switchScene(int sceneNumber){
        resetScene();
        scene = sceneNumber;
        switch(scene){
            case 0:
                potato.init(400,400, 10, 50,50);
                farmer.init(5000,500,20,450,450);
                platformHolder.setPlatformScene(0);
                wallHolder.setWallScene(0);
                break;
            case 1:
                potato.init(600,200, 10, 50,50);
                farmer.init(500,500,20,450,450);
                platformHolder.setPlatformScene(1);
                wallHolder.setWallScene(1);
                break;
            case 2:
                potato.init(400,500, 10, 50,50);
                farmer.init(5000,500,20,450,450);
                platformHolder.setPlatformScene(2);
                wallHolder.setWallScene(2);
                break;
            case 3:
                potato.init(400,500, 10, 50,50);
                farmer.init(5000,500,20,450,450);
                platformHolder.setPlatformScene(3);
                wallHolder.setWallScene(3);
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
        batch.begin();
        if(scene == 0){
            ScreenUtils.clear(.5f, .5f, .5f, 1);
        } else if (scene == 1){
            ScreenUtils.clear(.5f, 1, .5f, 1);
        } else if (scene == 2){
            ScreenUtils.clear(1f, .5f, .5f, 1);
        } else{
            ScreenUtils.clear(.5f, .5f, 1, 1);
        }

        farmer.render(batch);
        batch.end();
        potato.render();
    }
    public void resetScene(){
        bulletHolder.removeBullets();
    }
}
