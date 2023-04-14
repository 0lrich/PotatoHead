package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class ObjectHolder {
    ArrayList<InGameObj> objects = new ArrayList<>();

    public ObjectHolder(){

    }
    public void addObject(InGameObj obj){
        objects.add(obj);
    }
    public void update() {
        if (objects != null) {
            for (int i = 0; i < objects.size(); i++) {

                objects.get(i).update();
                if(objects.get(i).free==true){
                    objects.remove(i);
                    i--;
                }
            }
        }
    }

    public void removeBullets(){
        if(objects != null) {
            for (int i = objects.size()-1; i >= 0; i--) {
                objects.remove(i);
            }
        }
    }
    public void render(){
        if(objects != null) {
            for (int i = 0; i < objects.size(); i++) {
                objects.get(i).render(new SpriteBatch());
            }
        }
    }

}
