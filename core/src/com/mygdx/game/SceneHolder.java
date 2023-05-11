package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.mygdx.game.Globals.*;

public class SceneHolder {
    Player potato;
    public boolean inTutorial;

    int scene;
    Vector2 playerSpawn;
    public SceneHolder(){

        potato = new Player(0,20, 10, 50,36);
        farmerHandRight = new FirstBossHand(350,350,20,350,350 );
        farmerHandLeft = new FirstBossHand(0,350,20,350,350 );
        farmerHead = new FirstBossHead(350,350,30,450,450 );
        playerSpawn = new Vector2();
        music = Gdx.audio.newMusic(Gdx.files.internal("boss.mp3"));
        music.setVolume(10);
        music.setLooping(true);
    }
    public void setScene(int scene){}
    public int getScene() {return scene;}
    public void switchScene(int sceneNumber){
        resetScene();
        scene = sceneNumber;
        switch(scene){
            case 0: // Main menu
                mainMenuScreen.show();
                playerSpawn.set(5000, 5000);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,36);
                farmerHead.init(53500,400,30,350,350);
                farmerHandLeft.init(-3500000,350,20,350,350);
                farmerHandRight.init(350000,350,20,350,350);
                break;
            case 1: // Tutorial level

                music = Gdx.audio.newMusic(Gdx.files.internal("tutorial.mp3"));
                music.setVolume(0.5f);
                music.play();
                wallTexture = cloudTexture;
                platNoFallTexture = cloudTexture;
                platFallTexture = cloudTexture;
                inTutorial = true;
                playerSpawn.set(500, 500);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,36);
                farmerHead.init(53500,400,30,350,350);
                farmerHandLeft.init(-3500000,350,20,350,350);
                farmerHandRight.init(350000,350,20,350,350);
                platformHolder.setPlatformScene(1);
                wallHolder.setWallScene(1);
                break;
            case 2: // Boss level

                music = Gdx.audio.newMusic(Gdx.files.internal("boss.mp3"));
                music.setVolume(0.1f);
                music.play();
                wallTexture = roadTexture;
                platNoFallTexture = roadStripeTexture;
                platFallTexture = woodBlockTexture;
                inTutorial = false;

                playerSpawn.set(600, 3000);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,36);
                farmerHead.init(Gdx.graphics.getWidth()/2 - farmerHead.width/2,400,30,350,350);
                farmerHandLeft.init(0,350,20,350,350 );
                farmerHandRight.init(Gdx.graphics.getWidth()- farmerHandRight.width,350,20,350,350);

                platformHolder.setPlatformScene(2);
                wallHolder.setWallScene(2);
                break;
            case 3: // Test level
                playerSpawn.set(400, 5000);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,36);
                farmerHandLeft.init(-35000,350,20,350,350 );
                farmerHandRight.init(3500,350,20,350,350 );
                farmerHead.init(53500,400,30,350,350 );

                platformHolder.setPlatformScene(3);
                wallHolder.setWallScene(3);
                break;
            case 4: // Test level 2
                playerSpawn.set(400, 350);
                potato.init(playerSpawn.x, playerSpawn.y, 3, 50,36);
                farmerHandLeft.init(-35000,350,20,350,350 );
                farmerHandRight.init(3500,350,20,350,350 );
                farmerHead.init(53500,400,30,350,350 );

                platformHolder.setPlatformScene(4);
                wallHolder.setWallScene(4);
                break;

        }
    }
    public void tempLevel1Portal(){
        if(potato.getPosX() < 0 && potato.getPosY() < -2000){
            switchScene(3);
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
            ScreenUtils.clear(.9f, .9f, 1, 1);
            tempLevel1Portal();
            Globals.font.draw(batch, "RUN WITH 'A' and 'D'", 700, 700);
            Globals.font.draw(batch, "JUMP WITH 'W'", 2000, 700);
            Globals.font.draw(batch, "SHOOT WITH 'SPACE'", 3500, 2200);
            Globals.font.draw(batch, "AIM BY HOLDING 'I' 'J' 'K' 'L'", 1700, 2200);
        } else if (scene == 2){
            ScreenUtils.clear(.6f, .2f, .2f, 1);
            if(!farmerHandLeft.getIsAlive() && !farmerHandRight.getIsAlive() && !farmerHead.getIsAlive()){
                switchScene(3);
            }
        } else if (scene == 3){
            ScreenUtils.clear(0.7f,0.8f,1f,1);
            batch.draw(gradientTexture, -20000, 0, 40000, 9000);
            Globals.font.draw(batch, "GET INTO THE TRUCK AND FREE YOUR POTATO BRETHEREN ->", 100, 100);
        } else if (scene == 4){
            ScreenUtils.clear(1, 1, 1, 1);
            Globals.font.draw(batch, "YOU WIN!", 100, 100);
        }

        farmerHandRight.render(batch);
        farmerHandLeft.render(batch);
        farmerHead.render(batch);
        potato.render(batch);
    }
    public void resetScene(){
        music.stop();
        bulletHolder.removeBullets();
    }
    public Vector2 getPlayerSpawn(){return playerSpawn;}
    public boolean getInTutorial(){return inTutorial;}
}
