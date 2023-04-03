package com.mygdx.game;

public class SceneHolder {
    int scene;
    public SceneHolder(){}
    public void setScene(int scene){}
    public int getScene() {return scene;}
    public void update(){
        resetScene();
        switch(scene){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
    public void resetScene(){}
}
