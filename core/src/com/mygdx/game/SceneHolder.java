package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.Globals.bulletHolder;
import static com.mygdx.game.Globals.platformHolder;

public class SceneHolder {
    FirstBossHead farmerHead;
    FirstBossHand farmerHandRight;
    Player potato;
    int scene;
    Vector2 playerSpawn;
    public SceneHolder(){
        
        potato = new Player(0,20, 10, 50,50,new ShapeRenderer());
        farmerHandRight = new FirstBossHand(500,500,20,450,450,new ShapeRenderer());
        farmerHead = new FirstBossHead(500,500,30,600,600,new ShapeRenderer());
        playerSpawn = new Vector2();
    }
    public void setScene(int scene){}
    public int getScene() {return scene;}
    public void switchScene(int sceneNumber){
        resetScene();
        scene = sceneNumber;
        switch(scene){
            case 0:
                playerSpawn.set(200, 200);

                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,50,new ShapeRenderer());
                farmerHead.init(55000,400,30,600,600,new ShapeRenderer());

                farmerHandRight.init(5000,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(0);
                break;
            case 1:
                playerSpawn.set(600, 200);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,50,new ShapeRenderer());
                farmerHead.init(550,400,30,600,600,new ShapeRenderer());
                farmerHandRight.init(Gdx.graphics.getWidth()- farmerHandRight.width,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(1);
                break;
            case 2:
                playerSpawn.set(400, 500);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,50,new ShapeRenderer());
                farmerHandRight.init(5000,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(2);
                break;
            case 3:
                playerSpawn.set(400, 500);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,50,new ShapeRenderer());
                farmerHandRight.init(5000,500,20,450,450,new ShapeRenderer());
                platformHolder.setPlatformScene(3);
                break;
        }
    }
    public void update(){
        potato.update(Gdx.graphics.getDeltaTime());
        if(scene == 1){
            farmerHandRight.update(potato);
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

        farmerHandRight.render(batch);
        farmerHead.render(batch);
        batch.end();
        potato.render();
    }
    public void resetScene(){
        bulletHolder.removeBullets();
    }
    public Vector2 getPlayerSpawn(){return playerSpawn;}
}
