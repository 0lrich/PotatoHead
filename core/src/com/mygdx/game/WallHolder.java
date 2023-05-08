package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.lerp;
import static com.mygdx.game.Globals.platformHolder;

public class WallHolder {

    ArrayList<Wall> walls = new ArrayList<>();
    int wallScene = 0;
    public WallHolder(){
        setWallScene(0);
    }
    public void addWall(float x, float y, float height, float width){
        walls.add(new Wall(x, y, height, width));
        platformHolder.addPlatform(x, y + 1, height, width, true, false);
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
            case 0:
                break;
            case 1:
                addWall(-20,0,2000,20); // Wall on left
                addWall(0,0,400,2000); // Spawn wall
                addWall(2000,0,500,200); // Force-to-jump wall
                addWall(4000,0,600,900);//
                addWall(4800, 600, 265, 100);//
                addWall(5100,1480,500,100);//
                addWall(5000,1480,50,100);//
                break;
            case 2:

                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
}
