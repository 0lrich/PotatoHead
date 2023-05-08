package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import static com.mygdx.game.Globals.sceneHolder;


public class MainMenuScreen extends ScreenAdapter {
    private Stage stage;
    private Viewport viewport;
    private Table mainTable;
    TextButton button;
    boolean areButtonsEnabled;
    @Override
    public void show() {
        areButtonsEnabled = true;
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
        mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);
        addButton("Play").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(areButtonsEnabled) {
                    sceneHolder.switchScene(1);
                    button.setDisabled(true);
                    areButtonsEnabled = false;
                }
            }
        });
        addButton("Quit").addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(areButtonsEnabled){
                    Gdx.app.exit();
                }

            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    private TextButton addButton(String name) {
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = Globals.font;
        button = new TextButton(name, textButtonStyle);
        mainTable.add(button).width(700).height(60).padBottom(10);
        mainTable.row();
        return button;
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.3f, .3f, .35f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
    public void enableMainMenuButtons(boolean enabled){
        areButtonsEnabled = enabled;
    }
    public boolean getAreButtonsEnabled(){
        return areButtonsEnabled;
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}