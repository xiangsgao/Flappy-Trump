package com.gao23.trumpbird;

import com.badlogic.gdx.graphics.OrthographicCamera;
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
    private Stage stage;
    private int scores;
    private Label scoreLabel;
    // this sets up a new camera so uhd(stage) does not move as the main camera moves with the bird
    private Viewport viewport;

    public hud (SpriteBatch ab){
        scores = 0;
        viewport = new FitViewport(TrumpBirdMain.WIDTH,TrumpBirdMain.HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,ab);
        //this organizes the shit in our stage
        Table table = new Table();
        // default is the center, this places all the widget at the top
        table.top();
    }



}
