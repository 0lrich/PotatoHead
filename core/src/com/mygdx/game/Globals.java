package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Globals { // This global class holds objects that should be accessible from every class
    public static BulletHolder bulletHolder = new BulletHolder();
    public static PlatformHolder platformHolder = new PlatformHolder();

    public static ObjectHolder objectHolder = new ObjectHolder();
    public static SceneHolder sceneHolder = new SceneHolder();

    public static ShapeRenderer globalRender = new ShapeRenderer();
}
