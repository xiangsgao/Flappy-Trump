package com.gao23.trumpbird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by GAO on 8/1/2017.
 */

public class hud {
    // stage is an empty box
    public Stage stage;
    private int scores;
    // Label is a widget good for drawing text
    private Label scoreLabel;
    // this sets up a new camera so uhd(stage) does not move as the main camera moves with the bird
    private Viewport viewport;

    public hud (SpriteBatch ab){
        scores = 0;
        // sets up the camera size
        viewport = new FitViewport(TrumpBirdMain.WIDTH,TrumpBirdMain.HEIGHT,new OrthographicCamera());
        //stage takes in the viewport and configures its size
        stage = new Stage(viewport,ab);
        //this organizes the shit in our stage os widget isn't all over the place
        Table table = new Table();
        // default is the center, this places all the widget at the top... top center now
        table.top();
        table.setFillParent(true);
        scoreLabel = new Label(Integer.toString(scores), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        table.add(scoreLabel).expandX();
        // adds everything in the table to the stage so everything is neat and organized
        stage.addActor(table);

    }



}
