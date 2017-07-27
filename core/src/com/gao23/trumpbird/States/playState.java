package com.gao23.trumpbird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.gao23.trumpbird.sprites.trumpBird;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gao23.trumpbird.TrumpBirdMain;
import com.gao23.trumpbird.sprites.tubes;

import java.util.Collections;
import java.util.Random;

/**
 * Created by GAO on 7/23/2017.
 */

public class playState extends States {
    private trumpBird bird;
    private Texture background;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_NUMBER = 4;
    private Array<tubes> tubeArray;
    private float lastPostion;



    public playState(stateManager manager){
        super(manager);
        tubeArray = new Array<tubes>();
        bird = new trumpBird(25, 300);
        // this is to zoom in on only one part of the graphics
        cam.setToOrtho(false, TrumpBirdMain.WIDTH/2, TrumpBirdMain.HEIGHT/2);
        background = new Texture("background.jpg");
        for(int i = 0; i<=this.TUBE_NUMBER;i++){
            if(i == 0){
                tubeArray.add(new tubes(i * this.TUBE_SPACING + tubes.TUBE_WIDTH +200));
            }
            else{
                tubeArray.add(new tubes(i * this.TUBE_SPACING + tubes.TUBE_WIDTH + new Random().nextInt(100) + 150));
            }
        }
        lastPostion = tubeArray.get(tubeArray.size-1).getTopTubPos().x;
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
        cam.position.x = bird.getPostion().x + 80;
        for(tubes tube: tubeArray){
            if(cam.position.x-(cam.viewportWidth/2)>tube.getTopTubPos().x+tube.getTopTube().getWidth()) {
                if(tube.getTopTubPos().x>this.lastPostion){
                    tube.getTopTubPos().x = lastPostion;
                }
                tube.reposition(100 + tubes.TUBE_WIDTH + new Random().nextInt(100) + this.lastPostion);
                this.lastPostion = tube.getTopTubPos().x;

            }
           if(tube.collides(bird.getBounds())){
                manager.set(new playState(manager));
                break;
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch ab) {
        // this adjusts the sprite batch to the zoomed in camera
        ab.setProjectionMatrix(cam.combined);
        ab.begin();
        ab.draw(background, bird.getPostion().x-40, 0, cam.viewportWidth,cam.viewportHeight);
        ab.draw(bird.getTrumpB(), bird.getPostion().x, bird.getPostion().y);
        for(tubes tube: tubeArray) {
            ab.draw(tube.getTopTube(), tube.getTopTubPos().x, tube.getTopTubPos().y);
            ab.draw(tube.getBottomTube(), tube.getBotTubePos().x, tube.getBotTubePos().y);
        }
        ab.end();
    }


    @Override
    public void dispose() {
           for(tubes Tube: tubeArray){
               Tube.dispose();
           }
           bird.disapose();
           background.dispose();
    }
}
