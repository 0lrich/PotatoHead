package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Globals { // This global class holds objects that should be accessible from every class
    public static ShapeRenderer globalRender = new ShapeRenderer();
    public static BulletHolder bulletHolder = new BulletHolder();
    public static PlatformHolder platformHolder = new PlatformHolder();
    public static WallHolder wallHolder = new WallHolder();
    public static ObjectHolder objectHolder = new ObjectHolder();
    public static SceneHolder sceneHolder = new SceneHolder();
    public static Player potato;
    public static FirstBoss farmer;
    public static OrthographicCamera camera = new OrthographicCamera();
    public static BitmapFont font = new BitmapFont(Gdx.files.internal("Font/font.fnt"), Gdx.files.internal("Font/font.png"), false);
    public static MainMenuScreen mainMenuScreen = new MainMenuScreen();
    public static Texture cloudTexture = new Texture(Gdx.files.internal("cloudBlock.png"));
    public static Texture roadTexture = new Texture(Gdx.files.internal("roadBlock.png"));
    public static Texture roadStripeTexture = new Texture(Gdx.files.internal("roadStripe.png"));
    public static Texture woodBlockTexture = new Texture(Gdx.files.internal("woodBlock.png"));
    public static Texture wallTexture;
    public static Texture platNoFallTexture;
    public static Texture platFallTexture;



}
