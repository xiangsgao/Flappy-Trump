package com.gao23.trumpbird.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gao23.trumpbird.TrumpBirdMain;

/**
 * Created by GAO on 7/22/2017.
 */

public class menuState extends States {

    // this is the background image for menu state
    private Texture background;

    // this is the play icon for menu state
    private Texture playIcon;


    @Override
    public void dispose() {
        background.dispose();
        playIcon.dispose();
    }

    public menuState(stateManager manager) {
        super(manager);
        background = new Texture("background.jpg");
        playIcon = new Texture("playbtn.png");
    }

    @Override
    public void input() {
         if(Gdx.input.justTouched()){
             manager.set(new playState(manager));
             // now this dispose all the shit when play state comes in
             this.dispose();
         }
    }

    @Override
    public void update(float time) {
          // gotta do this first
          this.input();
    }

    @Override
    public void render(SpriteBatch ab) {
         ab.begin();
         ab.draw(background, 0, 0 , TrumpBirdMain.WIDTH, TrumpBirdMain.HEIGHT);
         ab.draw(playIcon, (TrumpBirdMain.WIDTH/2)-(playIcon.getWidth()/2), TrumpBirdMain.HEIGHT/2);
         ab.end();
    }
}
