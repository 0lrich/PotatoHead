package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.FirstBoss;
import com.mygdx.game.Player;

import static com.mygdx.game.Globals.*;

public class SceneHolder {
    FirstBossHead farmerHead;
    FirstBossHand farmerHandRight;
    FirstBossHand farmerHandLeft;
    Player potato;

    int scene;
    Vector2 playerSpawn;
    MainMenuScreen mainMenu;
    public SceneHolder(){

        potato = new Player(0,20, 10, 50,50);
        farmerHandRight = new FirstBossHand(350,350,20,350,350 );
        farmerHandLeft = new FirstBossHand(0,350,20,350,350 );
        farmerHead = new FirstBossHead(350,350,30,450,450 );
        playerSpawn = new Vector2();

    }
    public void setScene(int scene){}
    public int getScene() {return scene;}
    public void switchScene(int sceneNumber){
        resetScene();
        scene = sceneNumber;
        switch(scene){
            case 0: // Main Menu
                potato.init(5000,200, 10, 50,50);
                farmer.init(5000,500,20,450,450);
                mainMenuScreen.show();
                break;
            case 1: // Tutorial
                playerSpawn.set(400, 400);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,50);
                farmerHead.init(53500,400,30,350,350);
                farmerHandLeft.init(-3500000,350,20,350,350);
                farmerHandRight.init(350000,350,20,350,350);
            case 2: // Boss
                playerSpawn.set(600, 200);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,50);
                farmerHead.init(Gdx.graphics.getWidth()/2 - farmerHead.width/2,400,30,350,350);
                farmerHandLeft.init(0,350,20,350,350 );
                farmerHandRight.init(Gdx.graphics.getWidth()- farmerHandRight.width,350,20,350,350);


        }
    }
    public void update(){
        potato.update(Gdx.graphics.getDeltaTime());
        if(scene == 2) {
            farmerHandRight.update(potato);
            farmerHandLeft.update(potato);
            farmerHead.update(potato);
        }
        if(scene != 0){
            potato.update(Gdx.graphics.getDeltaTime());
        }
    }
    public void render(SpriteBatch batch) {
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

        farmerHandRight.render(batch);
        farmerHandLeft.render(batch);
        farmerHead.render(batch);
        potato.render(batch);
    }
    public void resetScene(){
        bulletHolder.removeBullets();
    }
    public Vector2 getPlayerSpawn(){return playerSpawn;}
}
