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
    public SceneHolder(){

        potato = new Player(0,20, 10, 50,24);
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
            case 0:
                mainMenuScreen.show();
                playerSpawn.set(5000, 5000);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,24);
                farmerHead.init(53500,400,30,350,350);
                farmerHandLeft.init(-3500000,350,20,350,350);
                farmerHandRight.init(350000,350,20,350,350);
                break;
            case 1:

                playerSpawn.set(500, 500);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,24);
                farmerHead.init(53500,400,30,350,350);
                farmerHandLeft.init(-3500000,350,20,350,350);
                farmerHandRight.init(350000,350,20,350,350);
                platformHolder.setPlatformScene(1);
                wallHolder.setWallScene(1);
                break;
            case 2:

                playerSpawn.set(600, 200);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,24);
                farmerHead.init(Gdx.graphics.getWidth()/2 - farmerHead.width/2,400,30,350,350);
                farmerHandLeft.init(0,350,20,350,350 );
                farmerHandRight.init(Gdx.graphics.getWidth()- farmerHandRight.width,350,20,350,350);

                platformHolder.setPlatformScene(2);
                wallHolder.setWallScene(2);
                break;
            case 3:

                playerSpawn.set(400, 500);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,24);
                farmerHandLeft.init(-35000,350,20,350,350 );
                farmerHandRight.init(3500,350,20,350,350 );
                farmerHead.init(53500,400,30,350,350 );

                platformHolder.setPlatformScene(3);
                wallHolder.setWallScene(3);
                break;
            case 4:
                playerSpawn.set(400, 350);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,24);
                farmerHandLeft.init(-35000,350,20,350,350 );
                farmerHandRight.init(3500,350,20,350,350 );
                farmerHead.init(53500,400,30,350,350 );

                platformHolder.setPlatformScene(4);
                wallHolder.setWallScene(4);
                break;

        }
    }
    public void update(){
        if(scene != 0) {
            potato.update(Gdx.graphics.getDeltaTime());
        }
        if(scene == 2){
            farmerHandRight.update(potato);
            farmerHandLeft.update(potato);
            farmerHead.update(potato);
        }
    }
    public void render(SpriteBatch batch) {
        if(scene == 0){
            ScreenUtils.clear(1, 1, 1, 1);
        } else if (scene == 1){
            ScreenUtils.clear(.5f, 1, .5f, 1);
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
