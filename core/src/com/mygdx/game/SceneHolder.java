package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.Globals.*;

public class SceneHolder {
    FirstBoss farmer;
    Player potato;
    int scene;
    MainMenuScreen mainMenu;
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
                System.out.println("THE CODE FOR THE MAIN MENU SCENE IS RUNNING");
                potato.init(5000,200, 10, 50,50,new ShapeRenderer());
                farmer.init(5000,500,20,450,450,new ShapeRenderer());
                mainMenuScreen.show();
                break;
            case 1:
                potato.init(200,200, 10, 50,50,new ShapeRenderer());
                farmer.init(5000,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(0);
                break;
            case 2:
                potato.init(600,200, 10, 50,50,new ShapeRenderer());
                farmer.init(500,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(1);
                break;
            case 3:
                potato.init(400,500, 10, 50,50,new ShapeRenderer());
                farmer.init(5000,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(2);
                break;
            case 4:
                potato.init(400,500, 10, 50,50,new ShapeRenderer());
                farmer.init(5000,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(3);
                break;


        }
    }
    public void update(){
        if(scene == 2){
            farmer.update();
        }
        if(scene != 0){
            potato.update(Gdx.graphics.getDeltaTime());
        }
    }
    public void render(SpriteBatch batch) {
        batch.begin();
        if(scene == 0){
            ScreenUtils.clear(0, 0, 0, 1);
        } else if(scene == 1){
            ScreenUtils.clear(.5f, .5f, .5f, 1);
        } else if (scene == 2){
            ScreenUtils.clear(.5f, 1, .5f, 1);
        } else if (scene == 3){
            ScreenUtils.clear(1f, .5f, .5f, 1);
        } else if (scene == 4){
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
