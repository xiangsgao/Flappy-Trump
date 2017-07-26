package com.gao23.trumpbird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.gao23.trumpbird.sprites.trumpBird;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gao23.trumpbird.TrumpBirdMain;
import com.gao23.trumpbird.sprites.tubes;
/**
 * Created by GAO on 7/23/2017.
 */

public class playState extends States {
    private trumpBird bird;
    private Texture background;
    private tubes tubes;

    public playState(stateManager manager){
        super(manager);
        tubes = new tubes(100);
        bird = new trumpBird(25, 300);
        // this is to zoom in on only one part of the graphics
        cam.setToOrtho(false, TrumpBirdMain.WIDTH/2, TrumpBirdMain.HEIGHT/2);
        background = new Texture("background.jpg");
    }

    @Override
    protected void input() {
         if(Gdx.input.justTouched()){
             bird.jump();
         }
    }

    @Override
    public void update(float time) {
        this.input();
        bird.update(time);

    }

    @Override
    public void render(SpriteBatch ab) {
        // this adjusts the sprite batch to the zoomed in camera
        ab.setProjectionMatrix(cam.combined);
        ab.begin();
        ab.draw(background,0,0, cam.viewportWidth,cam.viewportHeight);
        ab.draw(bird.getTrumpB(), bird.getPostion().x, bird.getPostion().y);
        ab.draw(tubes.getTopTube(),tubes.getTopTubPos().x,tubes.getTopTubPos().y);
        ab.draw(tubes.getBottomTube(),tubes.getBotTubePos().x,tubes.getBotTubePos().y);
        ab.end();
    }

    @Override
    public void dispose() {

    }
}
