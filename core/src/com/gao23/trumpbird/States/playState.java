package com.gao23.trumpbird.States;

import com.gao23.trumpbird.sprites.trumpBird;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gao23.trumpbird.TrumpBirdMain;

/**
 * Created by GAO on 7/23/2017.
 */

public class playState extends States {
    private trumpBird bird;

    public playState(stateManager manager){
        super(manager);
        bird = new trumpBird(50, 300);
        // this is to zoom in on only one part of the graphics
        cam.setToOrtho(false, TrumpBirdMain.WIDTH/2, TrumpBirdMain.HEIGHT/2);
    }

    @Override
    protected void input() {

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
        ab.draw(bird.getTrumpB(), bird.getPostion().x, bird.getPostion().y);
        ab.end();
    }

    @Override
    public void dispose() {

    }
}
