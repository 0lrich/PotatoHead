package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class PlatformHolder {
    ArrayList<Platform> platforms = new ArrayList<>();
    public PlatformHolder(){}
    public void addPlatform(float x, float y, float height, float width, ShapeRenderer floor, Boolean tangible, Boolean isFallThrough){
        platforms.add(new Platform(x, y, height, width, floor, tangible, isFallThrough));
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
}
