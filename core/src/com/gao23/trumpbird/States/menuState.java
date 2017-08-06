package com.gao23.trumpbird.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gao23.trumpbird.TrumpBirdMain;

/**
 * Created by GAO on 7/22/2017.
 */

public class menuState extends States {

    // This is the background image for menu state
    private Texture background;

    // This is better than maunally drawing the textures
    private Stage stage;


    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }

    public menuState(final stateManager manager) {
        super(manager);
        background = new Texture("background.jpg");
        Texture playIcon = new Texture("playbtn.png");
        ImageButton playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(playIcon)));
        stage = new Stage(new ScreenViewport(), manager.getAb());
        playButton.setPosition(TrumpBirdMain.WIDTH/2-(playIcon.getWidth()/2),TrumpBirdMain.HEIGHT/2-(playIcon.getHeight()/2));
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
               menuState.this.manager.set(new playState(manager));
            }
        });
        stage.addActor(playButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void input() {
        return;
    }

    @Override
    public void update(float time) {
          this.input();
    }

    @Override
    public void render(SpriteBatch ab) {
         ab.begin();
         ab.draw(background, 0, 0 , TrumpBirdMain.WIDTH, TrumpBirdMain.HEIGHT);
         ab.end();
         stage.draw();
    }
}
