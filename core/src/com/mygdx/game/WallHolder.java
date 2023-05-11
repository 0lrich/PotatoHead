package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import static com.mygdx.game.Globals.*;

public class WallHolder {

    ArrayList<Wall> walls = new ArrayList<>();
    int wallScene = 0;
    public WallHolder(){
        setWallScene(0);
    }
    public void addWall(float x, float y, float height, float width){
        walls.add(new Wall(x, y, height, width));
        //platformHolder.addPlatform(x, y + 1, height, width, true, false, nothingTexture);
        platformHolder.addPlatform(x, y + 1, height, width, true, false, wallTexture);
    }
    public void addWall(float x, float y, float height, float width, Texture texture){
        walls.add(new Wall(x, y, height, width, texture));
        //platformHolder.addPlatform(x, y + 1, height, width, true, false, nothingTexture);
        platformHolder.addPlatform(x, y + 1, height, width, true, false, texture);
    }
    public void removeWall(int Wall){
        walls.remove(Wall);
    }
    public void removeAllWalls(){
        for(int i = walls.size() - 1; i >= 0 ; i--){
            walls.remove(i);
        }
    }
    public ArrayList<Wall> getWalls(){
        return walls;
    }
    public Wall getWall(int Wall){
        return walls.get(Wall);
    }
    public void render(SpriteBatch batch){
        for(int i = 0; i < walls.size(); i++){
            walls.get(i).render(batch);
        }
    }
    public void setWallScene(int wallScene){
        this.wallScene = wallScene;
        updatewallScene();
    }
    public int getwallscene(){
        return wallScene;
    }
    public void updatewallScene(){
        removeAllWalls();
        switch(wallScene){
            case 0: // Main menu
                break;
            case 1: // Tutorial
                addWall(-20,0,2000,20); // Wall on left
                addWall(0,0,400,2000); // Spawn wall
                addWall(2000,0,500,200); // Force-to-jump wall
                addWall(3990,0,600,900);//
                addWall(4800, 600, 265, 100);//
                addWall(5100,1480,500,100);//
                addWall(5000,1480,50,100);//
                break;
            case 2: // Boss level
                addWall(-2000,-300, 300, 1500);
                break;
            case 3: // Test level 1
                addWall(-2000,-3000, 3000, 999999, roadTexture);
                stairs(3000, 0);
                //addWall(3800,100, 100, 2000, truckBaseTexture);
                addWall(5799, 125, 964, 500, truckHeadTexture);

                break;
            case 4: // Test level 2
                break;
        }
    }
    public void stairs(float x, float y){
        for(int i = 0; i < 15; i++){
            addWall(x + (50 * i),y + (50 * i), 50, 50);
        }
    }
}
