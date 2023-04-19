package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SceneHolder;

public class MainMenuScreen implements Screen {
    private Stage stage;
    private Viewport viewport;
    private AssetManager assetManager;

    public MainMenuScreen() {

    }
    @Override
    public void show () {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);

        Table mainTable = new Table();
        mainTable.setFillParent(true);

        stage.addActor(mainTable);

        addButton("Play");
        addButton("Options");
        addButton("Credits");
        addButton("Quit");

        Gdx.input.setInputProcessor(stage);
    }

    private TextButton(String name) {
        TextButton button = new TextButton(name, new Skin());
    }

    @Override
    public void render (float delta){
        Gdx.gl.glClearColor(.1f, .1f, .15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        stage.draw();
    }

    @Override
    public void resize (int width, int height){
        viewport.update(width, height);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////



    @Override
    public void pause () {
    }
    @Override
    public void resume () {
    }
    @Override
    public void hide () {
    }
    @Override
    public void dispose () {
    }
}
