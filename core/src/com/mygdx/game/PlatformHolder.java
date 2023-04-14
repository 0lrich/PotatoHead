package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.lerp;

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
                float baseWidth =  800;
                float topWidth = 20;
                float piramidHeight = 1000;
                float slices = 24;
                for(int i = 0; i < slices; i++) {
                    float width = lerp(baseWidth, topWidth, i/slices);
                    float shift = ((baseWidth - width)/2);
                    addPlatform(200 + shift,0 + (i * 30),20, width, new ShapeRenderer(),true,false);
                }
                break;
            case 1:
                addPlatform(200,200,20,200,new ShapeRenderer(),true, true);
                addPlatform(550,100,20,200,new ShapeRenderer(),true, true);
                addPlatform(1150,100,20,200,new ShapeRenderer(),true, true);
                addPlatform(1500,200,20,200,new ShapeRenderer(),true, true);
                break;
            case 2:
                addPlatform(0,0,400,700,new ShapeRenderer(),true,false);
                addPlatform(900,0,400,200,new ShapeRenderer(),true,false);
                addPlatform(1300,0,400,700,new ShapeRenderer(),true,false);
                break;
            case 3:
                xFormation(200, 40, 100, 50);
                break;
        }
    }
    public void xFormation(int slices, float topWidth, float baseWidth, float height) {
        for(int i = 0; i < slices; i++) {
            addPlatform(i + 315, i+100, 10, 20, new ShapeRenderer(), true, true);
            addPlatform(-i + 515, i+100, 10, 20, new ShapeRenderer(), true, true);
        }
    }
}
