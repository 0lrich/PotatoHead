package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Platform;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.lerp;
import static com.mygdx.game.Globals.cliffTexture;
import static com.mygdx.game.Globals.defaultTexture;

public class PlatformHolder {
    ArrayList<Platform> platforms = new ArrayList<>();
    int platformScene = 0;
    public PlatformHolder(){
        setPlatformScene(0);
    }
    public void addPlatform(float x, float y, float height, float width, Boolean tangible, Boolean isFallThrough){
        platforms.add(new Platform(x, y, height, width, tangible, isFallThrough));
    }
    public void addPlatform(float x, float y, float height, float width, Boolean tangible, Boolean isFallThrough, Texture texture){
        platforms.add(new Platform(x, y, height, width, tangible, isFallThrough, texture));
    }
    public void removePlatform(int platform){
        platforms.remove(platform);
    }
    public void removeAllPlatforms(){
        for(int i = platforms.size() - 1; i >= 0 ; i--){
            platforms.remove(i);
        }
    }
    public ArrayList<Platform> getPlatforms(){
        return platforms;
    }
    public Platform getPlatform(int platform){
        return platforms.get(platform);
    }
    public void render(SpriteBatch batch){
        for(int i = 0; i < platforms.size(); i++){
            platforms.get(i).render(batch);
        }
    }
    public void setPlatformScene(int platformScene){
        this.platformScene = platformScene;
        updatePlatformScene();
    }
    public int getPlatformScene(){
        return platformScene;
    }
    public void updatePlatformScene(){
        removeAllPlatforms();
        switch(platformScene){
            case 0: // Main menu
                break;
            case 1: // Tutorial level
                addPlatform(2400,510,40,200, true, false); //
                addPlatform(2800,510,40,200, true, false); //
                addPlatform(3400,510,40,200, true, false); //
                addPlatform(4250, 1000, 40, 200, true, false);//
                addPlatform(3800,1250,40,210,true,false);//
                addPlatform(4300,1480,40,200,true,false);//
                addPlatform(0,1920,80,4000,true,false);//
                addPlatform(4500,1900,40,200,true,false);//
                addPlatform(4900,1750,40,200,true,false);//
                break;
            case 2: // Boss level
                addPlatform(200,200,20,200,true, true);
                addPlatform(550,100,20,200,true, true);
                addPlatform(1150,100,20,200,true, true);
                addPlatform(1500,200,20,200,true, true);
                addPlatform(0,-100,40,2000,true,false);
                break;
            case 3: // Test level 1
                addPlatform(-2300,-500,500,900,true, true, cliffTexture);
                break;
            case 4: // Test level 2
                pyramid(20, 40, 100, 50);
                break;
            case 5:
                float baseWidth =  800;
                float topWidth = 20;
                float pyramidHeight = 1000;
                int slices = 24;
                for(int i = slices; i >= 0; i--) {
                    float width = lerp(topWidth, baseWidth, (float)i/(float)(slices));
                    float shift = ((baseWidth - width)/2);
                    addPlatform(200 + shift,(slices * 30)-i*30,20, width,true,false);
                }
                break;
        }
    }
    public void pyramid(int slices, float topWidth, float baseWidth, float height) {
        for(int i = 0; i < slices; i++) {
            addPlatform(0, 11*i + 100, 10, 500, true, true);
        }
    }
}
