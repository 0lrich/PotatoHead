package com.mygdx.game;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.lerp;

public class WallHolder {

    ArrayList<Wall> walls = new ArrayList<>();
    int wallScene = 0;
    public WallHolder(){
        setWallScene(0);
    }
    public void addWall(float x, float y, float height, float width){
        walls.add(new Wall(x, y, height, width));
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
    public void render(){
        for(int i = 0; i < walls.size(); i++){
            walls.get(i).render();
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
                addWall(300, 300, 400, 30);
                addWall(100, 100, 40, 900);
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:
                break;
        }
    }
}
