package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class PlatformHolder {
    ArrayList<Platform> platforms = new ArrayList<>();
    int platformScene = 0;
    public PlatformHolder(){
        setPlatformScene(0);
    }
    public void addPlatform(float x, float y, float height, float width, ShapeRenderer floor, Boolean tangible, Boolean isFallThrough){
        platforms.add(new Platform(x, y, height, width, floor, tangible, isFallThrough));
    }
    public void removePlatform(int platform){
        platforms.remove(platform);
    }
    public void removeAllPlatforms(){
        for(int i = platforms.size() - 1; i > 0 ; i--){
            platforms.remove(i);
        }
    }
    public ArrayList<Platform> getPlatforms(){
        return platforms;
    }
    public Platform getPlatform(int platform){
        return platforms.get(platform);
    }
    public void render(){
        for(int i = 0; i < platforms.size(); i++){
            platforms.get(i).render();
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
            case 0:
                addPlatform(400,100,20,400,new ShapeRenderer(),true, true);
                addPlatform(1000,300,50,400,new ShapeRenderer(),true,true);
                addPlatform(0,0,20,2000,new ShapeRenderer(),true,false);
                break;
            case 1:
                addPlatform(400,100,20,400,new ShapeRenderer(),true, true);
                addPlatform(400,0,20,400,new ShapeRenderer(),true, true);
                addPlatform(0,0,20,2000,new ShapeRenderer(),true,false);
                break;
            case 2:
                addPlatform(0,0,50,2000,new ShapeRenderer(),true,false);
                break;
        }
    }
}
